package com.cuwallet.repository.dao.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cuwallet.commons.dto.NewUser;
import com.cuwallet.commons.dto.UserInformation;
import com.cuwallet.repository.dao.CassandraClient;
import com.cuwallet.repository.dao.IUserDao;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.querybuilder.Update;
import com.datastax.driver.core.querybuilder.Update.Assignments;

@Repository("userCassandraDao")
public class UserCassandraDao implements IUserDao {

	/*private final static Logger LOGGER = LoggerFactory.getLogger(UserCassandraDao.class);*/

	private static final String USER_COLUMNFAMILY = "user_info";

	private static final String REVIEW_KEYSPACE = "review_platform";

	@Autowired
	private CassandraClient cassandraClient;

	@Override
	public void setNewUser(NewUser newUser) {
		UUID object_id = UUID.randomUUID();
		String password= null;
		try {
			 password = newUser.getPassword();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			password = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error while updating the new user info");
		}
		Update update = QueryBuilder.update(REVIEW_KEYSPACE, USER_COLUMNFAMILY);
		update.where().and(QueryBuilder.eq("user_id", newUser.getUserId()));
		Assignments assignments = update.with();
		assignments.and(QueryBuilder.set("password", password));
		assignments.and(QueryBuilder.set("object_id", object_id));
		assignments.and(QueryBuilder.set("age", newUser.getAge()));
		assignments.and(QueryBuilder.set("first_name", newUser.getFirstName()));
		assignments.and(QueryBuilder.set("last_name", newUser.getLastName()));
		assignments.and(QueryBuilder.set("height", newUser.getHeight()));
		assignments.and(QueryBuilder.set("weight", newUser.getWeight()));
		cassandraClient.getSession().execute(update);
		
	}

	@Override
	public boolean hasUser(String userId) {
		Select select = QueryBuilder.select().all().from(REVIEW_KEYSPACE, USER_COLUMNFAMILY);
		select.where().and(QueryBuilder.eq("user_id", userId));
		ResultSet result = cassandraClient.getSession().execute(select);
		Iterator<Row> iterator = result.iterator();
		if (iterator.hasNext()) {
			return true;
		}
		return false;
	}

	@Override
	public UserInformation getUserInfo(String userId) {

		Select select = QueryBuilder.select().all().from(REVIEW_KEYSPACE, USER_COLUMNFAMILY);
		select.where().and(QueryBuilder.eq("user_id", userId));

		ResultSet result = cassandraClient.getSession().execute(select);
		Iterator<Row> iterator = result.iterator();
		UserInformation user = null;
		while (iterator.hasNext()) {
			user = new UserInformation();
			Row row = iterator.next();
			String name = "";
			String firstName = row.getString("first_name");
			String lastName = row.getString("last_name");
			if (null != lastName) {
				name = firstName + " " + lastName;
			} else {
				name = firstName;
			}
			user.setName(name);
			user.setAge(row.getInt("age"));
			user.setHeight(row.getInt("height"));
			user.setWeight(row.getInt("weight"));
		}

		return user;
	}

	@Override
	public boolean validUser(String userId) {
		Select select = QueryBuilder.select().all().from(REVIEW_KEYSPACE, USER_COLUMNFAMILY);
		select.where().and(QueryBuilder.eq("user_id", userId));
		ResultSet result = cassandraClient.getSession().execute(select);
		Iterator<Row> iterator = result.iterator();
		if (iterator.hasNext()) {
			return true;
		}
		return false;
	}

	
}
