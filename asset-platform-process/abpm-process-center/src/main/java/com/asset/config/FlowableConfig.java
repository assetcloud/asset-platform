package com.asset.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author lichao
 */
@Configuration
public class FlowableConfig {

	@Autowired
	private FlowableDataSourceConfig flowableDataSourceConfig;
	@Autowired
	private CommonDataSourceConfig commonDataSourceConfig;

	@Bean(name = "flowableDataSource")
	@Primary
	public DataSource flowableDataSource() {
		DruidDataSource ds = new DruidDataSource();
		ds.setUrl(flowableDataSourceConfig.getUrl());
		ds.setUsername(flowableDataSourceConfig.getUsername());
		ds.setPassword(flowableDataSourceConfig.getPassword());
		ds.setDriverClassName(flowableDataSourceConfig.getDriverClassName());

		ds.setInitialSize(commonDataSourceConfig.getInitialSize());
		ds.setMinIdle(commonDataSourceConfig.getMinIdle());
		ds.setMaxActive(commonDataSourceConfig.getMaxActive());
		ds.setTestOnBorrow(commonDataSourceConfig.isTestOnReturn());
		ds.setTestOnReturn(commonDataSourceConfig.isTestOnReturn());
		ds.setMinEvictableIdleTimeMillis(commonDataSourceConfig.getMinEvictableIdleTimeMillis());
		return ds;
	}

	@Bean
	public DataSourceTransactionManager flowableDataSourceTransactionManager(@Qualifier("flowableDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "flowableSqlSessionFactory")
	@Primary
	public SqlSessionFactory flowableSqlSessionFactory(@Qualifier("flowableDataSource") DataSource dataSource)
			throws Exception {
//		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();

		MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);

		//读取xml配置文件
		Resource resource = new PathMatchingResourcePatternResolver()
				.getResource("classpath:mybatis/mybatis-config.xml");
		sessionFactoryBean.setConfigLocation(resource);
		//读取mapper.xml文件
		Resource[] mapperLocations = new PathMatchingResourcePatternResolver()
				.getResources("classpath:mybatis/mapper/*.xml");
		sessionFactoryBean.setMapperLocations(mapperLocations);

		//注意返回的是SqlSessionFactory
		return sessionFactoryBean.getObject();
	}

	@Bean(name = "flowableSqlSessionTemplate")
	public SqlSessionTemplate flowableSqlSessionTemplate(@Qualifier("flowableSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
		return sqlSessionTemplate;
	}
}
