package com.cuwallet.repository.dao;

import com.cuwallet.commons.constant.ConfigurationType;
import com.cuwallet.commons.dto.IConfiguration;

public interface IConfigurationDao {

	void updateConfigurationSettings(ConfigurationType configurationType, IConfiguration configuration);

	<T extends IConfiguration> T getConfigurationSettings(ConfigurationType configurationType);

	<T extends IConfiguration> T getDefaultConfigurations(ConfigurationType configurationType);

}
