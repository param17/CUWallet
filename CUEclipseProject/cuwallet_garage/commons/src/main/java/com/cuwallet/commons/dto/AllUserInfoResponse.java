package com.cuwallet.commons.dto;

import java.util.List;

import com.cuwallet.commons.service.ServiceResponse;

public class AllUserInfoResponse extends ServiceResponse {

	private static final long serialVersionUID = 7969872392446928266L;

	private List<UserInformation> userInformation;

	public List<UserInformation> getUserInformation() {
		return userInformation;
	}

	public void setUserInformation(List<UserInformation> userInformation) {
		this.userInformation = userInformation;
	}
	
}
