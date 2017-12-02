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
	private String userId;
	
	@ApiModelProperty(required = true)
	@NotNull
	@NotEmpty
	private String password;
	
	@ApiModelProperty(required = true)
	@NotNull
	@NotEmpty
	private String firstName;
	
	@ApiModelProperty
	private String lastName;
	
	@ApiModelProperty(value = "current age",required = true)
	private int age;
	
	@ApiModelProperty(required = true)
	private int weight;
	
	@ApiModelProperty(required = true, value = "Height is in CM")
	private int height;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	
}
