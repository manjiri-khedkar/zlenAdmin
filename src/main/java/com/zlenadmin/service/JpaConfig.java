package com.zlenadmin.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration

public class JpaConfig {
      
	@Value("${admin.datasource.url}")
    private String ADMIN_DS_URL;
	
	@Value("${admin.datasource.password}")
    private String ADMIN_DS_PASSWORD;
	
	@Value("${admin.datasource.username}")
    private String ADMIN_DS_USERNAME;
	
	@Value("${zlen.datasource.url}")
    private String ZLEN_DS_URL;
	
	@Value("${zlen.datasource.password}")
    private String ZLEN_DS_PASSWORD;
	
	@Value("${zlen.datasource.username}")
    private String ZLEN_DS_USERNAME;
	
	
    @Bean("admin-ds")
    public DataSource getDataSource() 
    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url(ADMIN_DS_URL);
        dataSourceBuilder.username(ADMIN_DS_USERNAME);
        dataSourceBuilder.password(ADMIN_DS_PASSWORD);
        return dataSourceBuilder.build();
    }
    
    @Bean("admin-jdbc")
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(@Qualifier("admin-ds") DataSource source) {
    	return new NamedParameterJdbcTemplate(source);
    }
    
    
      @Primary
	  @Bean("zlen-ds")
	    public DataSource getDataSource1() 
	    {
	        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
	        dataSourceBuilder.driverClassName("org.postgresql.Driver");
	        dataSourceBuilder.url(ZLEN_DS_URL);
	        dataSourceBuilder.username(ZLEN_DS_USERNAME);
	        dataSourceBuilder.password(ZLEN_DS_PASSWORD);
	        return dataSourceBuilder.build();
	    }
      
      @Bean("zlen-jdbc")
      public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate1(@Qualifier("zlen-ds") DataSource source) {
      	return new NamedParameterJdbcTemplate(source);
      }
  
      
}