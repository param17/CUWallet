package com.cuwallet.repository.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cuwallet.commons.dto.MoneyInformation;
import com.cuwallet.commons.dto.MoneyWallet;
import com.cuwallet.commons.dto.UserBankInformation;
import com.cuwallet.commons.exceptions.ErrorCode;
import com.cuwallet.commons.exceptions.ForbiddenException;
import com.cuwallet.repository.dao.CassandraClient;
import com.cuwallet.repository.dao.IMoneyDao;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.querybuilder.Update;
import com.datastax.driver.core.querybuilder.Update.Assignments;

@Repository("moneyCassandraDao")
public class MoneyCassandraDao implements IMoneyDao {

	private static final String TRANSACTION_COLUMNFAMILY = "transaction";

	private static final String CUWALLET_KEYSPACE = "cuwallet_platform";

	private static final String MONEY_WALLET_COLUMNFAMILY = "money_wallet";

	private static final String PAYMENT_GATEWAY_COLUMNFAMILY = "payment_gateway";

	private static final String USER_BANK_INFO_COLUMNFAMILY = "user_bank_info";

	@Autowired
	private CassandraClient cassandraClient;

	@Override
	public boolean sendMoney(MoneyInformation moneyInformation) {
		
		boolean userStatus = validateUser(moneyInformation.getEmailId());
		if(userStatus == false) {
			throw new ForbiddenException(ErrorCode.AUTHENTICATION_ERROR, "user id is not registered for mobile wallets");
		}
		boolean receiverStatus = validateUser(moneyInformation.getReceiverEmailId());
		if(receiverStatus ==  false) {
			throw new ForbiddenException(ErrorCode.AUTHENTICATION_ERROR, "receiver id is not registered for mobile wallets");
		}
		
		double balance = getUserBalance(moneyInformation.getEmailId(), moneyInformation.getPhoneNo());
		if (balance < moneyInformation.getTransactionMoney()) {
			return false;
		}
		
		MoneyWallet moneyWallet = transferMoney(moneyInformation, balance);
		updateBalance(moneyWallet);
		double receiverBalance  = getUserBalance(moneyInformation.getReceiverEmailId(), moneyInformation.getReceiverPhoneNo());
		MoneyWallet receiverWallet = new MoneyWallet();
		receiverWallet.setEmailId(moneyInformation.getReceiverEmailId());
		receiverWallet.setPhoneNo(moneyInformation.getReceiverPhoneNo());
		receiverWallet.setBalance(receiverBalance + moneyInformation.getTransactionMoney());
		updateBalance(receiverWallet);
		return true;
	}

	private boolean validateUser(String emailId) {
		Select select = QueryBuilder.select().all().from(CUWALLET_KEYSPACE, MONEY_WALLET_COLUMNFAMILY);
		select.where().and(QueryBuilder.eq("email_id", emailId));
		ResultSet result = cassandraClient.getSession().execute(select);
		Iterator<Row> iterator = result.iterator();
		if (iterator.hasNext()) {
				return true;
		}
		return false;
	}

	private void updateBalance(MoneyWallet moneyWallet) {
		String uuid = moneyWallet.getEmailId() + "-" + moneyWallet.getPhoneNo();
		UUID object_id = UUID.nameUUIDFromBytes(uuid.getBytes());
		Update update = QueryBuilder.update(CUWALLET_KEYSPACE, MONEY_WALLET_COLUMNFAMILY);
		update.where().and(QueryBuilder.eq("email_id", moneyWallet.getEmailId()));
		Assignments assignments = update.with();
		assignments.and(QueryBuilder.set("object_id", object_id));
		assignments.and(QueryBuilder.set("phone_no", moneyWallet.getPhoneNo()));
		assignments.and(QueryBuilder.set("balance", moneyWallet.getBalance()));
		cassandraClient.getSession().execute(update);

	}

	private MoneyWallet transferMoney(MoneyInformation moneyInformation, double balance) {
		String uuid = moneyInformation.getEmailId() + "-" + moneyInformation.getPhoneNo();
		UUID object_id = UUID.nameUUIDFromBytes(uuid.getBytes());
		Update update = QueryBuilder.update(CUWALLET_KEYSPACE, TRANSACTION_COLUMNFAMILY);
		update.where().and(QueryBuilder.eq("email_id", moneyInformation.getEmailId()));
		Assignments assignments = update.with();
		assignments.and(QueryBuilder.set("object_id", object_id));
		assignments.and(QueryBuilder.set("phone_no", moneyInformation.getPhoneNo()));
		assignments.and(QueryBuilder.set("receiver_email_id", moneyInformation.getReceiverEmailId()));
		assignments.and(QueryBuilder.set("receiver_phone_no", moneyInformation.getReceiverPhoneNo()));
		assignments.and(QueryBuilder.set("transaction_money", moneyInformation.getTransactionMoney()));
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date today = Calendar.getInstance().getTime(); 
		double newBalance = balance - moneyInformation.getTransactionMoney();
		assignments.and(QueryBuilder.set("balance", newBalance));
		assignments.and(QueryBuilder.set("transaction_date", df.format(today)));
		cassandraClient.getSession().execute(update);
		MoneyWallet moneyWallet = new MoneyWallet();
		moneyWallet.setPhoneNo(moneyInformation.getPhoneNo());
		moneyWallet.setEmailId(moneyInformation.getEmailId());
		moneyWallet.setBalance(newBalance);
		return moneyWallet;
	}

	private double getUserBalance(String emailId, String phoneNo) {
		Select select = QueryBuilder.select().all().from(CUWALLET_KEYSPACE, MONEY_WALLET_COLUMNFAMILY);
		select.where().and(QueryBuilder.eq("email_id", emailId));
		ResultSet result = cassandraClient.getSession().execute(select);
		Iterator<Row> iterator = result.iterator();
		double balance = 0.0d;
		if (iterator.hasNext()) {
			Row row = iterator.next();
			balance = row.getDouble("balance");
		}
		return balance;
	}

	@Override
	public boolean addMoney(String emailId, String phoneNo, double amount) {
		boolean userStatus = validateUser(emailId);
		if(userStatus == false) {
			throw new ForbiddenException(ErrorCode.AUTHENTICATION_ERROR, "user id is not registered for mobile wallets");
		}
		UserBankInformation userBankInformation = getUserBankInfo(emailId, phoneNo);
		if (userBankInformation == null) {
			return false;
		}
		boolean status = isAmountAvailable(userBankInformation);
		if (status) {
			boolean flag = checkBankInfoExist(userBankInformation);
			if(flag == false) {
				return false;
			}
			double balance = getAmountFromBank(userBankInformation);
			double remainingBalance = balance - amount;
			updateUserBankAmount(userBankInformation, remainingBalance);
			updateUserWallet(emailId, phoneNo, amount);
			return true;
		} else {
			return false;
		}
	}

	private boolean checkBankInfoExist(UserBankInformation userBankInformation) {
		Select select = QueryBuilder.select().all().from(CUWALLET_KEYSPACE, PAYMENT_GATEWAY_COLUMNFAMILY);
		select.where().and(QueryBuilder.eq("bank_account_type", userBankInformation.getBankAccountType()))
				.and(QueryBuilder.eq("bank_name", userBankInformation.getBankName()))
				.and(QueryBuilder.eq("account_no", userBankInformation.getAccountNo()))
				.and(QueryBuilder.eq("rounting_number", userBankInformation.getRoutingNumber()))
				.and(QueryBuilder.eq("expiry_date", userBankInformation.getExpiryDate()))
				.and(QueryBuilder.eq("cvv_number", userBankInformation.getCvvNumber()));
		ResultSet result = cassandraClient.getSession().execute(select);
		Iterator<Row> iterator = result.iterator();
		if (iterator.hasNext()) {
			return true;
		}
		return false;
	}

	private void updateUserWallet(String emailId, String phoneNo, double amount) {
		double balance = getUserBalance(emailId, phoneNo);
		amount = amount + balance;
		MoneyWallet moneyWallet = new MoneyWallet(emailId,phoneNo,amount);
		updateBalance(moneyWallet);
	}

	private void updateUserBankAmount(UserBankInformation userBankInformation, double remainingBalance) {
		
		Update update = QueryBuilder.update(CUWALLET_KEYSPACE, PAYMENT_GATEWAY_COLUMNFAMILY);
		update.where().and(QueryBuilder.eq("bank_account_type", userBankInformation.getBankAccountType()))
				.and(QueryBuilder.eq("bank_name", userBankInformation.getBankName()))
				.and(QueryBuilder.eq("account_no", userBankInformation.getAccountNo()))
				.and(QueryBuilder.eq("rounting_number", userBankInformation.getRoutingNumber()))
				.and(QueryBuilder.eq("expiry_date", userBankInformation.getExpiryDate()))
				.and(QueryBuilder.eq("cvv_number", userBankInformation.getCvvNumber()));
		Assignments assignments = update.with();
		assignments.and(QueryBuilder.set("money", remainingBalance));		
		cassandraClient.getSession().execute(update);
	}

	private double getAmountFromBank(UserBankInformation userBankInformation) {
		Select select = QueryBuilder.select().all().from(CUWALLET_KEYSPACE, PAYMENT_GATEWAY_COLUMNFAMILY);
		select.where().and(QueryBuilder.eq("bank_account_type", userBankInformation.getBankAccountType()))
				.and(QueryBuilder.eq("bank_name", userBankInformation.getBankName()))
				.and(QueryBuilder.eq("account_no", userBankInformation.getAccountNo()))
				.and(QueryBuilder.eq("rounting_number", userBankInformation.getRoutingNumber()))
				.and(QueryBuilder.eq("expiry_date", userBankInformation.getExpiryDate()))
				.and(QueryBuilder.eq("cvv_number", userBankInformation.getCvvNumber()));
		ResultSet result = cassandraClient.getSession().execute(select);
		Iterator<Row> iterator = result.iterator();
		double balance = 0.0d;
		if (iterator.hasNext()) {
			Row row = iterator.next();
			balance = row.getDouble("money");
		}
		return balance;
	}

	private boolean isAmountAvailable(UserBankInformation userBankInformation) {
		Select select = QueryBuilder.select().all().from(CUWALLET_KEYSPACE, PAYMENT_GATEWAY_COLUMNFAMILY);
		select.where().and(QueryBuilder.eq("bank_account_type", userBankInformation.getBankAccountType()))
				.and(QueryBuilder.eq("bank_name", userBankInformation.getBankName()))
				.and(QueryBuilder.eq("account_no", userBankInformation.getAccountNo()))
				.and(QueryBuilder.eq("rounting_number", userBankInformation.getRoutingNumber()))
				.and(QueryBuilder.eq("expiry_date", userBankInformation.getExpiryDate()))
				.and(QueryBuilder.eq("cvv_number", userBankInformation.getCvvNumber()));
		ResultSet result = cassandraClient.getSession().execute(select);
		Iterator<Row> iterator = result.iterator();
		if (iterator.hasNext()) {
			return true;
		}
		return false;
	}

	private UserBankInformation getUserBankInfo(String emailId, String phoneNo) {
		Select select = QueryBuilder.select().all().from(CUWALLET_KEYSPACE, USER_BANK_INFO_COLUMNFAMILY);
		select.where().and(QueryBuilder.eq("email_id", emailId));
		ResultSet result = cassandraClient.getSession().execute(select);
		UserBankInformation userBankInformation = null;
		Iterator<Row> iterator = result.iterator();
		if (iterator.hasNext()) {
			Row row = iterator.next();
			userBankInformation = new UserBankInformation();
			userBankInformation.setPhoneNumber(phoneNo);
			userBankInformation.setEmailId(emailId);
			userBankInformation.setBankAccountType(row.getString("bank_account_type"));
			userBankInformation.setAccountNo(row.getString("account_no"));
			userBankInformation.setBankName(row.getString("bank_name"));
			userBankInformation.setCvvNumber(row.getString("cvv_number"));
			userBankInformation.setExpiryDate(row.getString("expiry_date"));
			userBankInformation.setRoutingNumber(row.getString("routing_number"));
		}
		return userBankInformation;
	}

	@Override
	public MoneyWallet getWalletMoney(String emailId, String phoneNo) {
		boolean userStatus = validateUser(emailId);
		if(userStatus == false) {
			throw new ForbiddenException(ErrorCode.AUTHENTICATION_ERROR, "user id is not registered for mobile wallets");
		}
		Select select = QueryBuilder.select().all().from(CUWALLET_KEYSPACE, MONEY_WALLET_COLUMNFAMILY);
		select.where().and(QueryBuilder.eq("email_id", emailId));
		ResultSet result = cassandraClient.getSession().execute(select);
		Iterator<Row> iterator = result.iterator();
		MoneyWallet moneyWallet = null;
		if (iterator.hasNext()) {
			Row row = iterator.next();
			moneyWallet = new MoneyWallet();
			moneyWallet.setEmailId(emailId);
			moneyWallet.setPhoneNo(phoneNo);
			moneyWallet.setBalance(row.getDouble("balance"));
		}
		return moneyWallet;
	}

}
