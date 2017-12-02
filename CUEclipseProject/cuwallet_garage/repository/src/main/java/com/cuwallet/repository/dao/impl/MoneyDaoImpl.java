package com.cuwallet.repository.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.cuwallet.commons.dto.MoneyInformation;
import com.cuwallet.commons.dto.MoneyWallet;
import com.cuwallet.repository.dao.IMoneyDao;

@Repository("moneyDaoImpl")
public class MoneyDaoImpl implements IMoneyDao{

	@Resource(name = "moneyCassandraDao")
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
