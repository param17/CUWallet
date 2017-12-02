package com.cuwallet.service.util;

import com.cuwallet.commons.dto.MoneyInformation;
import com.cuwallet.commons.dto.MoneyWallet;

public interface IMoneyService {

	public boolean sendMoney(MoneyInformation moneyInformation);

	public boolean addMoney(String emailId, String phoneNo, double amount);

	public MoneyWallet getWalletMoney(String emailId, String phoneNo);
}
