package com.cuwallet.service.util;

import com.cuwallet.commons.constant.ConfigurationType;
import com.cuwallet.commons.dto.IConfiguration;
import com.cuwallet.commons.dto.MailConfiguration;
import com.cuwallet.commons.exceptions.ReviewException;

/*
 * Created by ajay on 4/2/16.
 */

public interface IConfigurationService {
    void setConfiguration(ConfigurationType configurationType,
			IConfiguration configuration) throws ReviewException;

	<T extends IConfiguration> T getConfiguration(
			ConfigurationType configurationType) throws ReviewException;

	<T extends IConfiguration> T getDefaultConfiguration(
			ConfigurationType configurationType) throws ReviewException;
	
	MailConfiguration  getMailConfiguration();

}
