package com.cuwallet.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cuwallet.commons.constant.ConfigurationType;
import com.cuwallet.commons.dto.IConfiguration;
import com.cuwallet.commons.dto.MailConfiguration;
import com.cuwallet.commons.exceptions.ReviewException;
import com.cuwallet.repository.dao.IConfigurationDao;
import com.cuwallet.service.util.IConfigurationService;

@Service
public class ConfigurationServiceImpl implements IConfigurationService {
	
	@Resource(name = "configurationDaoImpl")
	private IConfigurationDao configurationDao;

	@Override
	public void setConfiguration(ConfigurationType configurationType, IConfiguration configuration)
			throws ReviewException {
		configurationDao.updateConfigurationSettings(configurationType, configuration);
		
	}

	@Override
	public <T extends IConfiguration> T getConfiguration(ConfigurationType configurationType) throws ReviewException {
		T configuration = configurationDao.getConfigurationSettings(configurationType);
		return configuration;
	}

	@Override
	public <T extends IConfiguration> T getDefaultConfiguration(ConfigurationType configurationType)
			throws ReviewException {
		T configuration = configurationDao.getDefaultConfigurations(configurationType);
		return configuration;
	}

	@Override
	public MailConfiguration getMailConfiguration() {
		ConfigurationType configurationType = ConfigurationType.MAIL_CONFIG;
		MailConfiguration configuration = configurationDao.getConfigurationSettings(configurationType);
		return configuration;
	}


}
