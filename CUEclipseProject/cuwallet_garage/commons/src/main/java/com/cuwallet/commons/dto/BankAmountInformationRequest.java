package com.cuwallet.commons.dto;

import com.cuwallet.commons.service.ServiceRequest;

public class BankAmountInformationRequest extends ServiceRequest{

	private static final long serialVersionUID = -7370248404978522894L;

	private BankAmountInformation bankAmountInformation;

	public BankAmountInformation getBankAmountInformation() {
		return bankAmountInformation;
	}

	public void setBankAmountInformation(BankAmountInformation bankAmountInformation) {
		this.bankAmountInformation = bankAmountInformation;
	}
	
}
