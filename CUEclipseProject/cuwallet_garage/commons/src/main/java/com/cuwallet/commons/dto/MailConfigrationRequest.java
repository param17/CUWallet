package com.cuwallet.commons.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.cuwallet.commons.service.ServiceRequest;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class MailConfigrationRequest extends ServiceRequest
{
	private static final long serialVersionUID = -6144175311904205279L;
	@ApiModelProperty(required = true)
	@NotNull
	@Valid
	private MailConfiguration mailConfiguration;

	public MailConfiguration getMailConfiguration() {
		return mailConfiguration;
	}

	public void setMailConfiguration(MailConfiguration mailConfigration) {
		this.mailConfiguration = mailConfigration;
	}
	
	
}
