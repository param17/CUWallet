package com.cuwallet.commons.constant;
	
import java.util.HashMap;
import java.util.Map;

import com.cuwallet.commons.dto.IConfiguration;
import com.cuwallet.commons.dto.MailConfiguration;


public enum ConfigurationType {

	MAIL_CONFIG(1,MailConfiguration.class);


	private int configType;

	private Class<? extends IConfiguration> configClass;

	private ConfigurationType(int configType,
			Class<? extends IConfiguration> configClass) {
		this.configType = configType;
		this.configClass = configClass;
	}

	public int getConfigType() {
		return configType;
	}

	public Class<? extends IConfiguration> getConfigClass() {
		return configClass;
	}

	private static Map<Integer, ConfigurationType> idToValueMap = new HashMap<Integer, ConfigurationType>();

	private static Map<String, ConfigurationType> keyToValueMap = new HashMap<String, ConfigurationType>();

	static {
		for (ConfigurationType configurationType : ConfigurationType.values()) {
			idToValueMap.put(configurationType.getConfigType(),
					configurationType);

		}
		for (ConfigurationType configurationType : ConfigurationType.values()) {
			keyToValueMap.put(configurationType.name(), configurationType);
		}
	}

	public static ConfigurationType fromId(int id) {
		ConfigurationType configurationType = idToValueMap.get(id);
		if (configurationType == null)
			throw new RuntimeException("Invalid ConfigurationType id:" + id);
		return configurationType;
	}

	public static ConfigurationType fromKey(String key) {
		ConfigurationType configurationType = keyToValueMap.get(key);
		if (configurationType == null)
			throw new RuntimeException("Invalid ConfigurationType key:" + key);
		return configurationType;
	}
}
