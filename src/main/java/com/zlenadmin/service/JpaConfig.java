package com.zlenadmin.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration

public class JpaConfig {
      
	
    @Bean("admin-ds")
    public DataSource getDataSource() 
    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://zlenapi-test.ckxmoibfumh8.ap-south-1.rds.amazonaws.com:5432/accountdb");
        dataSourceBuilder.username("zlenadmin");
        dataSourceBuilder.password("zlen-123");
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
	        dataSourceBuilder.url("jdbc:postgresql://zlenapi-test.ckxmoibfumh8.ap-south-1.rds.amazonaws.com:5432/zlendb");
	        dataSourceBuilder.username("zlenadmin");
	        dataSourceBuilder.password("zlen-123");
	        return dataSourceBuilder.build();
	    }
      
      @Bean("zlen-jdbc")
      public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate1(@Qualifier("zlen-ds") DataSource source) {
      	return new NamedParameterJdbcTemplate(source);
      }
  
      
}