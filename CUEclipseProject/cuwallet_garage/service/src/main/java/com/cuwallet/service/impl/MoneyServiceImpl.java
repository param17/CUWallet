package com.cuwallet.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cuwallet.commons.dto.MoneyInformation;
import com.cuwallet.commons.dto.MoneyWallet;
import com.cuwallet.repository.dao.IMoneyDao;
import com.cuwallet.service.util.IMoneyService;

@Service
public class MoneyServiceImpl implements IMoneyService {

	@Resource(name = "moneyDaoImpl")
	private IMoneyDao moneyDao;

	@Override
	public boolean sendMoney(MoneyInformation moneyInformation) {
		return moneyDao.sendMoney(moneyInformation);
	}

	@Override
	public boolean addMoney(String emailId, String phoneNo, double amount) {
		return moneyDao.addMoney(emailId, phoneNo, amount);
	}

	@Override
	public MoneyWallet getWalletMoney(String emailId, String phoneNo) {
		return moneyDao.getWalletMoney(emailId, phoneNo);
	}

}
