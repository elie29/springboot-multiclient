package com.learning.spring.multiclients.config;

import org.springframework.beans.factory.annotation.Autowired;
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
public class JdbcTemplateConfig
{

   @Bean
   public DataSource dataSource()
   {
      // HikariDataSource uses jdbc-url and not url so when using
      // DataSourceBuilder we need to provide jdbc-url in application.properties
      return DataSourceBuilder.create().build();
   }

   @Bean
   @Autowired
   public JdbcTemplate jdbcTemplate(DataSource dataSource)
   {
      return new JdbcTemplate(dataSource);
   }
}

