package com.cuwallet.commons.dto;

import com.cuwallet.commons.service.ServiceResponse;

public class WalletMoneyResponse extends ServiceResponse {

	private static final long serialVersionUID = 5102582952652319757L;
	
	private MoneyWallet moneyWallet;

	public MoneyWallet getMoneyWallet() {
		return moneyWallet;
	}

	public void setMoneyWallet(MoneyWallet moneyWallet) {
		this.moneyWallet = moneyWallet;
	}
	
}
