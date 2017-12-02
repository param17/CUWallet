package com.cuwallet.repository.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.cuwallet.commons.constant.ConfigurationType;
import com.cuwallet.commons.dto.IConfiguration;
import com.cuwallet.repository.dao.IConfigurationDao;

@Repository("configurationDaoImpl")
public class ConfigurationDaoImpl implements IConfigurationDao {

	@Resource(name = "configurationCassandraDao")
	private IConfigurationDao configurationDao;

	@Override
	public void updateConfigurationSettings(ConfigurationType configurationType, IConfiguration configuration) {
		configurationDao.updateConfigurationSettings(configurationType, configuration);

	}

	@Override
	public <T extends IConfiguration> T getConfigurationSettings(ConfigurationType configurationType) {
		T configuration = configurationDao.getConfigurationSettings(configurationType);
		return configuration;
	}

	@Override
	public <T extends IConfiguration> T getDefaultConfigurations(ConfigurationType configurationType) {
		T configuration = configurationDao.getDefaultConfigurations(configurationType);
		return configuration;
	}

}
