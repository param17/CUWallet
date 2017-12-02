package com.cuwallet.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cuwallet.commons.dto.BankAmountInformation;
import com.cuwallet.commons.dto.UserBankInformation;
import com.cuwallet.repository.dao.IPaymentDao;
import com.cuwallet.service.util.IPaymentService;

@Service
public class PaymentService implements IPaymentService {

	@Resource(name = "paymentDaoImpl")
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
