package com.cuwallet.repository.dao.impl;

import java.util.Iterator;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cuwallet.commons.dto.BankAmountInformation;
import com.cuwallet.commons.dto.UserBankInformation;
import com.cuwallet.repository.dao.CassandraClient;
import com.cuwallet.repository.dao.IPaymentDao;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.querybuilder.Update;
import com.datastax.driver.core.querybuilder.Update.Assignments;

@Repository("paymentCassandraDao")
public class PaymentCassandraDao implements IPaymentDao{	
	
	
	private static final String CUWALLET_KEYSPACE = "cuwallet_platform";

	private static final String PAYMENT_GATEWAY_COLUMNFAMILY = "payment_gateway";

	private static final String USER_BANK_INFO_COLUMNFAMILY = "user_bank_info";

	@Autowired
	private CassandraClient cassandraClient;
	
	@Override
	public boolean addBankDetails(UserBankInformation userBankInformation) {
		boolean status = checkBankDetails(userBankInformation);
		BankAmountInformation bankAmountInformation = createBankInformation(userBankInformation);
		boolean validateStatus = checkDummyBankInfoExist(bankAmountInformation);
		if(status == true) {
			return false;
		}
		if(validateStatus == false) {
			return false;
		}
		String uuid = userBankInformation.getEmailId() + "-" + userBankInformation.getPhoneNumber();
		UUID objectId = UUID.nameUUIDFromBytes(uuid.getBytes());
		Update update = QueryBuilder.update(CUWALLET_KEYSPACE, USER_BANK_INFO_COLUMNFAMILY);
		update.where().and(QueryBuilder.eq("email_id", userBankInformation.getEmailId()));
		Assignments assignments = update.with();
		assignments.and(QueryBuilder.set("object_id", objectId));
		assignments.and(QueryBuilder.set("bank_account_type", userBankInformation.getBankAccountType()));
		assignments.and(QueryBuilder.set("account_no", userBankInformation.getAccountNo()));
		assignments.and(QueryBuilder.set("bank_name", userBankInformation.getBankName()));
		assignments.and(QueryBuilder.set("cvv_number", userBankInformation.getCvvNumber()));
		assignments.and(QueryBuilder.set("expiry_date", userBankInformation.getExpiryDate()));
		assignments.and(QueryBuilder.set("routing_number", userBankInformation.getRoutingNumber()));
		assignments.and(QueryBuilder.set("phone_no", userBankInformation.getPhoneNumber()));
		cassandraClient.getSession().execute(update);	
		return true;
	}

	private BankAmountInformation createBankInformation(UserBankInformation userBankInformation) {
		BankAmountInformation bankAmountInformation = new BankAmountInformation();
		bankAmountInformation.setAccountNo(userBankInformation.getAccountNo());
		bankAmountInformation.setBankAccountType(userBankInformation.getBankAccountType());
		bankAmountInformation.setBankName(userBankInformation.getBankName());
		bankAmountInformation.setCvvNumber(userBankInformation.getCvvNumber());
		bankAmountInformation.setExpiryDate(userBankInformation.getExpiryDate());
		bankAmountInformation.setRoutingNumber(userBankInformation.getRoutingNumber());
		return bankAmountInformation;
	}

	private boolean checkBankDetails(UserBankInformation userBankInformation) {
		Select select = QueryBuilder.select().all().from(CUWALLET_KEYSPACE, USER_BANK_INFO_COLUMNFAMILY);
		select.where().and(QueryBuilder.eq("email_id", userBankInformation.getEmailId()));
		ResultSet result = cassandraClient.getSession().execute(select);
		Iterator<Row> iterator = result.iterator();
		if (iterator.hasNext()) {
			return true;
		}
		return false;
		
	}

	@Override
	public boolean addDummyBankDetails(BankAmountInformation request) {
		boolean status = checkDummyBankInfoExist(request);
		if(status ==  false) {
			Update update = QueryBuilder.update(CUWALLET_KEYSPACE, PAYMENT_GATEWAY_COLUMNFAMILY);
			update.where().and(QueryBuilder.eq("bank_account_type", request.getBankAccountType()))
			.and(QueryBuilder.eq("bank_name", request.getBankName()))
			.and(QueryBuilder.eq("account_no", request.getAccountNo()))
			.and(QueryBuilder.eq("rounting_number", request.getRoutingNumber()))
			.and(QueryBuilder.eq("expiry_date", request.getExpiryDate()))
			.and(QueryBuilder.eq("cvv_number", request.getCvvNumber()));
			Assignments assignments = update.with();
			assignments.and(QueryBuilder.set("money", request.getBalance()));
			cassandraClient.getSession().execute(update);	
			return true;
		}
		
		return false;
	}

	private boolean checkDummyBankInfoExist(BankAmountInformation request) {
		Select select = QueryBuilder.select().all().from(CUWALLET_KEYSPACE, PAYMENT_GATEWAY_COLUMNFAMILY);
		select.where().and(QueryBuilder.eq("bank_account_type", request.getBankAccountType()))
				.and(QueryBuilder.eq("bank_name", request.getBankName()))
				.and(QueryBuilder.eq("account_no", request.getAccountNo()))
				.and(QueryBuilder.eq("rounting_number", request.getRoutingNumber()))
				.and(QueryBuilder.eq("expiry_date", request.getExpiryDate()))
				.and(QueryBuilder.eq("cvv_number", request.getCvvNumber()));
		ResultSet result = cassandraClient.getSession().execute(select);
		Iterator<Row> iterator = result.iterator();
		if (iterator.hasNext()) {
			return true;
		}
		return false;
	}

}
