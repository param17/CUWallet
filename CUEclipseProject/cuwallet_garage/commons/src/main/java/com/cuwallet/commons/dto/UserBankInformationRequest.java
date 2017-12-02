package com.cuwallet.commons.dto;

import com.cuwallet.commons.service.ServiceRequest;

public class UserBankInformationRequest extends ServiceRequest {

	private static final long serialVersionUID = -8275681849524337665L;
	
	private UserBankInformation userBankInformation;

	public UserBankInformation getUserBankInformation() {
		return userBankInformation;
	}

	public void setUserBankInformation(UserBankInformation userBankInformation) {
		this.userBankInformation = userBankInformation;
	}
	
}
