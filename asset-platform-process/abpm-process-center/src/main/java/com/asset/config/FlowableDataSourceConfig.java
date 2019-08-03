package com.asset.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author lichao
 */
@Component
@ConfigurationProperties(prefix="spring.datasource")
@PropertySource(value="classpath:config/database/jdbc-${spring.profiles.active}.properties"
,encoding="UTF-8"
,ignoreResourceNotFound=false
		)
public class FlowableDataSourceConfig extends DataSourceConfigForLC {

	@Override
	public String toString() {
		return super.toString();
	}
}
