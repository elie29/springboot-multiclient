package com.learning.spring.multiclients.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
/**
 * Manual JDBCTemplate configuration:
 * https://docs.spring.io/spring-boot/docs/current/reference/html/howto-data-access.html
 */
public class JdbcTemplateClientConfig
{

   @Bean
   @ConfigurationProperties("spring.datasource.client")
   public DataSource dataSourceClient()
   {
      // HikariDataSource uses jdbc-url and not url so when using
      // DataSourceBuilder we need to provide jdbc-url in application.properties
      return DataSourceBuilder.create().build();
   }

   @Bean
   @Autowired
   public JdbcTemplate jdbcClientTemplate(DataSource dataSourceClient)
   {
      return new JdbcTemplate(dataSourceClient);
   }
}

