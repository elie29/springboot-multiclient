package com.learning.spring.multiclients.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
 * @Primary is required so that the database initializer feature uses this one
 */
@Slf4j
public class JdbcTemplateMasterConfig {

   @Bean
   @Primary
   @ConfigurationProperties("spring.datasource.master")
   public DataSourceProperties masterDataSourceProperties() {
      return new DataSourceProperties();
   }

   // Named bean is required in order to choose desired dataSource
   @Bean(name = "masterDataSource")
   @Primary
   public DataSource masterDataSource() {
      DataSourceProperties properties = masterDataSourceProperties();
      log.info("Master database URL {}", properties.getUrl());
      return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
   }

   @Bean
   @Autowired
   public JdbcTemplate jdbcMasterTemplate(@Qualifier("masterDataSource") DataSource masterDataSource) {
      return new JdbcTemplate(masterDataSource);
   }
}

