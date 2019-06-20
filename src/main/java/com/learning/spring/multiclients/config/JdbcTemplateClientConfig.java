package com.learning.spring.multiclients.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
   public DataSourceProperties dataSourceClientProperties()
   {
      return new DataSourceProperties();
   }

   @Bean
   public HikariDataSource dataSourceClient()
   {
      // DataSourceProperties is taking care of the url/jdbcUrl translation, so we use url
      return dataSourceClientProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
   }

   @Bean
   @Autowired
   public JdbcTemplate jdbcClientTemplate(DataSource dataSourceClient)
   {
      return new JdbcTemplate(dataSourceClient);
   }
}

