package com.cuwallet.commons.dto;


import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "The data to be provided for new user")
public class NewUser{

	@ApiModelProperty(required = true)
	@NotNull
	@NotEmpty
	private String emaiId;
	
	@ApiModelProperty(required = true)
	@NotNull
	@NotEmpty
	private String firstName;
	
	@ApiModelProperty
	private String lastName;
	
	@ApiModelProperty(value = "Phone no",required = true)
	@NotNull
	@NotEmpty
	private String phoneNo;

	public String getEmaiId() {
		return emaiId;
	}

	public void setEmaiId(String emaiId) {
		this.emaiId = emaiId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
}
