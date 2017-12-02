package com.cuwallet.repository.dao.impl;

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

	private static final String CUWALLET_KEYSPACE = "cuwallet_platform";

	@Autowired
	private CassandraClient cassandraClient;

	@Override
	public void setNewUser(NewUser newUser) {
		String uuid = newUser.getEmaiId() + "-" + newUser.getPhoneNo();
		UUID object_id = UUID.nameUUIDFromBytes(uuid.getBytes());
		Update update = QueryBuilder.update(CUWALLET_KEYSPACE, USER_COLUMNFAMILY);
		update.where().and(QueryBuilder.eq("email_id", newUser.getEmaiId()));
		Assignments assignments = update.with();
		assignments.and(QueryBuilder.set("object_id", object_id));
		assignments.and(QueryBuilder.set("phone_no", newUser.getPhoneNo()));
		assignments.and(QueryBuilder.set("first_name", newUser.getFirstName()));
		assignments.and(QueryBuilder.set("last_name", newUser.getLastName()));
		cassandraClient.getSession().execute(update);
	}

	@Override
	public boolean hasUser(String userId) {
		Select select = QueryBuilder.select().all().from(CUWALLET_KEYSPACE, USER_COLUMNFAMILY);
		select.where().and(QueryBuilder.eq("email_id", userId));
		ResultSet result = cassandraClient.getSession().execute(select);
		Iterator<Row> iterator = result.iterator();
		if (iterator.hasNext()) {
			return true;
		}
		return false;
	}

	@Override
	public UserInformation getUserInfo(String userId) {

		Select select = QueryBuilder.select().all().from(CUWALLET_KEYSPACE, USER_COLUMNFAMILY);
		select.where().and(QueryBuilder.eq("email_id", userId));

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
			user.setEmailId(userId);
			user.setPhoneNo(row.getString("phone_no"));
		}

		return user;
	}

	@Override
	public boolean validUser(String userId) {
		Select select = QueryBuilder.select().all().from(CUWALLET_KEYSPACE, USER_COLUMNFAMILY);
		select.where().and(QueryBuilder.eq("user_id", userId));
		ResultSet result = cassandraClient.getSession().execute(select);
		Iterator<Row> iterator = result.iterator();
		if (iterator.hasNext()) {
			return true;
		}
		return false;
	}

	
}
