package com.cuwallet.repository.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.cuwallet.commons.dto.BankAmountInformation;
import com.cuwallet.commons.dto.UserBankInformation;
import com.cuwallet.repository.dao.IPaymentDao;

@Repository("paymentDaoImpl")
public class PaymentDaoImpl implements IPaymentDao {

	@Resource(name = "paymentCassandraDao")
	private IPaymentDao paymentDao;

	@Override
	public boolean addBankDetails(UserBankInformation userBankInformation) {
		return paymentDao.addBankDetails(userBankInformation);
	}

	@Override
	public boolean addDummyBankDetails(BankAmountInformation request) {
		return paymentDao.addDummyBankDetails(request);
	}
	
}
