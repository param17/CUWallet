package com.cuwallet.commons.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
	
@ApiModel
public class UserInformation{
	
	@ApiModelProperty
	private String name;
	
	@ApiModelProperty(value = "current age", required = true)
	private int age;
	
	@ApiModelProperty
	private int weight;
	
	@ApiModelProperty
	private int height;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
