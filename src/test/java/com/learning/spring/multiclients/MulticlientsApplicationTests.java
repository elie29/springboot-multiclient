package com.learning.spring.multiclients;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MulticlientsApplicationTests {

   @Autowired
   private JdbcTemplate jdbcMasterTemplate;

   @Autowired
   private JdbcTemplate jdbcClientTemplate;

   @Test
   public void testMasterJdbc() {
      String sql = "select count(*) FROM organisation";
      int result = jdbcMasterTemplate.queryForObject(sql, Integer.class);
      assertThat(result, greaterThan(1));
   }

   @Test
   public void testClientJdbc() {
      String sql = "select count(*) FROM employee";
      int result = jdbcClientTemplate.queryForObject(sql, Integer.class);
      assertThat(result, greaterThan(2));
   }

   @Test
   public void testMasterJdbcFetch() {
      String sql = "select * FROM employee";
      List<Map<String, Object>> result1 = jdbcClientTemplate.queryForList(sql);
      log.info("Client {}", result1);
      List<Map<String, Object>> result2 = jdbcMasterTemplate.queryForList(sql);
      log.info("Master {}", result2);
      assertThat(result1, hasSize(greaterThan(2)));
   }

}
