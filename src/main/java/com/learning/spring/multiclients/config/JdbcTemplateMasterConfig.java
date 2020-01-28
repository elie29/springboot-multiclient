package com.learning.spring.multiclients.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class JdbcTemplateMasterConfig {

   @Bean
   @Primary // required so that the database initializer feature uses this one
   @ConfigurationProperties("spring.datasource.master")
   public DataSourceProperties masterDataSourceProperties() {
      return new DataSourceProperties();
   }

   @Bean
   public DataSource masterDataSource() {
      DataSourceProperties properties = masterDataSourceProperties();
      log.info("Master database URL {}", properties.getUrl());
      return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
   }

   @Bean
   @Autowired // argument name should fit method name otherwise use named bean and qualifier
   public JdbcTemplate jdbcMasterTemplate(DataSource masterDataSource) {
      return new JdbcTemplate(masterDataSource);
   }
}

