package com.cuwallet.commons.dto;

import com.cuwallet.commons.service.ServiceRequest;

public class MoneyInformationRequest extends ServiceRequest {

	private static final long serialVersionUID = -7009715611251043163L;

	private MoneyInformation moneyInformation;

	public MoneyInformation getMoneyInformation() {
		return moneyInformation;
	}

	public void setMoneyInformation(MoneyInformation moneyInformation) {
		this.moneyInformation = moneyInformation;
	}
	
}
