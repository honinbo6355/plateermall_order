package com.plateer.config;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = "com.plateer", lazyInitialization = "false")
public class MybatisConfiguration {

	private DataSource dataSource;
	
	public MybatisConfiguration(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Bean
	public SqlSessionFactory getSqlSession() throws Exception {
		//
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);

		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		sessionFactory.setMapperLocations(resolver.getResources("classpath*:/mapper/**/*.xml"));
		sessionFactory.setConfigLocation(resolver.getResource("classpath:/config/mybatis-config.xml"));
		sessionFactory.setTypeAliasesPackage("com.plateer.domain");
		return sessionFactory.getObject();
	}

	// 트랜젝션매니저 빈 생성
	@Bean(name = "transactionManager")
	public DataSourceTransactionManager transactionManager() {
		//
		DataSourceTransactionManager manager = new DataSourceTransactionManager(dataSource);
		return manager;
	}
}
