package com.learning.spring.multiclients.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@Slf4j
public class JdbcTemplateClientConfig {

   @Bean
   @ConfigurationProperties("spring.datasource.client")
   public DataSourceProperties clientDataSourceProperties() {
      return new DataSourceProperties();
   }

   // Named bean is required
   @Bean(name = "clientDataSource")
   public DataSource clientDataSource() {
      // DataSourceProperties is taking care of the url/jdbcUrl translation, so we use url
      DataSourceProperties properties = clientDataSourceProperties();
      log.info("Client database URL {}", properties.getUrl());
      return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
   }

   @Bean
   @Autowired
   public JdbcTemplate jdbcClientTemplate(@Qualifier("clientDataSource") DataSource clientDataSource) {
      return new JdbcTemplate(clientDataSource);
   }
}
