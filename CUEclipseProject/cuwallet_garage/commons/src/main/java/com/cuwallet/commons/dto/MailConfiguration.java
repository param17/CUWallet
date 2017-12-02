package com.cuwallet.commons.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Data to be provided for mail settings")
public class MailConfiguration implements IConfiguration {
	private static final long serialVersionUID = 6357006218950606151L;

	
	@ApiModelProperty
	private EmailConfiguration emailConfiguration;
	
	
	public EmailConfiguration getEmailConfiguration() {
		return emailConfiguration;
	}

	public void setEmailConfiguration(EmailConfiguration emailConfiguration) {
		this.emailConfiguration = emailConfiguration;
	}

	public void mergeMailConfigration(MailConfiguration emailConfig) {

		
		if (this.emailConfiguration != null
				&& emailConfig.emailConfiguration != null) {
			this.emailConfiguration
			.mergeMailProperties(emailConfig.emailConfiguration);
			this.emailConfiguration.mergeAdditionalProperties(emailConfig.emailConfiguration);
		}
		else{
			this.emailConfiguration = (emailConfig.emailConfiguration != null) ? emailConfig.emailConfiguration
					: this.emailConfiguration;
		}
	}	
}
