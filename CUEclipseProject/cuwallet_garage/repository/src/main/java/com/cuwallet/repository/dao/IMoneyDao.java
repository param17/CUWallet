package com.cuwallet.repository.dao;

import com.cuwallet.commons.dto.MoneyInformation;
import com.cuwallet.commons.dto.MoneyWallet;

public interface IMoneyDao {

	boolean sendMoney(MoneyInformation moneyInformation);

	boolean addMoney(String emailId, String phoneNo, double amount);

	MoneyWallet getWalletMoney(String emailId, String phoneNo);

}
