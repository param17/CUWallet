package com.cuwallet.commons.dto;

import com.cuwallet.commons.service.ServiceResponse;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserInfoResponse extends ServiceResponse {


	private static final long serialVersionUID = -2652583272603095905L;

	@ApiModelProperty(value = "user Information")
	private UserInformation user;

	public UserInformation getUser() {
		return user;
	}

	public void setUser(UserInformation user) {
		this.user = user;
	}

}
