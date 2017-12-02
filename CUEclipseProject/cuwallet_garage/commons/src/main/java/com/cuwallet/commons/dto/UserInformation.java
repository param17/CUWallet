package com.cuwallet.commons.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
	
@ApiModel
public class UserInformation{
	
	@ApiModelProperty
	private String emailId;
	
	@ApiModelProperty(value = "current age", required = true)
	private String phoneNo;
	
	@ApiModelProperty
	private String name;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
