package com.learning.spring.multiclients;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MulticlientsApplicationTests
{

   @Autowired
   private JdbcTemplate jdbcMasterTemplate;

   @Autowired
   private JdbcTemplate jdbcClientTemplate;

   @Test
   public void testMasterJdbc()
   {
      String sql = "select count(*) FROM organisation";
      int result = jdbcMasterTemplate.queryForObject(sql, Integer.class);
      assertThat(result, is(7));
   }

   @Test
   public void testClientJdbc()
   {
      String sql = "select count(*) FROM employee";
      int result = jdbcClientTemplate.queryForObject(sql, Integer.class);
      assertThat(result, is(19));
   }
}
