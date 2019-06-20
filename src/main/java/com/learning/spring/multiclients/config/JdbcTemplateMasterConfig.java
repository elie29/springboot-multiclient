package com.learning.spring.multiclients.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
/**
 * Manual JDBCTemplate configuration:
 * https://docs.spring.io/spring-boot/docs/current/reference/html/howto-data-access.html
 */
public class JdbcTemplateMasterConfig
{

   @Bean
   @Primary // required to be called first
   @ConfigurationProperties("spring.datasource.master")
   public DataSourceProperties dataSourceMasterProperties()
   {
      return new DataSourceProperties();
   }

   @Bean
   public DataSource dataSourceMaster()
   {
      // DataSourceProperties is taking care of the url/jdbcUrl translation, so we use url
      return dataSourceMasterProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
   }

   @Bean
   @Autowired
   public JdbcTemplate jdbcMasterTemplate(DataSource dataSourceMaster)
   {
      return new JdbcTemplate(dataSourceMaster);
   }
}

