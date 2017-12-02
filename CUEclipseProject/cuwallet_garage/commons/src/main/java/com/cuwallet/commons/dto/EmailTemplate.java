package com.cuwallet.commons.dto;

import javax.validation.constraints.NotNull;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public abstract class EmailTemplate implements IConfiguration {

	private static final long serialVersionUID = 6623125773262094305L;

	@ApiModelProperty
	private Boolean isTaskEnabled;

	@ApiModelProperty
	private String emailSubject;

	@ApiModelProperty
	private Integer numberOfPreviousOrders;

	@ApiModelProperty
	private Boolean isTestingEnabled;

	@ApiModelProperty
	private String testCustomerEmail;

	@ApiModelProperty
	private String unsubscribeURL;

	@NotNull
	@ApiModelProperty
	private Integer limit;

	public Boolean getIsTaskEnabled() {
		return isTaskEnabled;
	}

	public void setIsTaskEnabled(Boolean isTaskEnabled) {
		this.isTaskEnabled = isTaskEnabled;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}


	public Integer getNumberOfPreviousOrders() {
		return numberOfPreviousOrders;
	}

	public void setNumberOfPreviousOrders(Integer numberOfPreviousOrders) {
		this.numberOfPreviousOrders = numberOfPreviousOrders;
	}

	public Boolean getIsTestingEnabled() {
		return isTestingEnabled;
	}

	public void setIsTestingEnabled(Boolean isTestingEnabled) {
		this.isTestingEnabled = isTestingEnabled;
	}

	public String getTestCustomerEmail() {
		return testCustomerEmail;
	}

	public void setTestCustomerEmail(String testCustomerEmail) {
		this.testCustomerEmail = testCustomerEmail;
	}

	public String getUnsubscribeURL() {
		return unsubscribeURL;
	}

	public void setUnsubscribeURL(String unsubscribeURL) {
		this.unsubscribeURL = unsubscribeURL;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public void mergeMailProperties(EmailTemplate newConfig) {
		this.isTaskEnabled = (newConfig.getIsTaskEnabled() == null) ? this.getIsTaskEnabled()
				: newConfig.getIsTaskEnabled();
		this.numberOfPreviousOrders = (newConfig.getNumberOfPreviousOrders() == null) ? this.getNumberOfPreviousOrders()
				: newConfig.getNumberOfPreviousOrders();
		this.emailSubject = (newConfig.getEmailSubject() == null) ? this.getEmailSubject()
				: newConfig.getEmailSubject();
		this.isTestingEnabled = (newConfig.getIsTestingEnabled() == null) ? this.getIsTestingEnabled()
				: newConfig.getIsTestingEnabled();
		this.testCustomerEmail = (newConfig.getTestCustomerEmail() == null) ? this.getTestCustomerEmail()
				: newConfig.getTestCustomerEmail();
		this.unsubscribeURL = (newConfig.getUnsubscribeURL() == null) ? this.getUnsubscribeURL()
				: newConfig.getUnsubscribeURL();
		this.limit = (newConfig.getLimit() == null) ? this.getLimit() : newConfig.getLimit();
	}


}