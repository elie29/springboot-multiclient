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
   private JdbcTemplate jdbcTemplate;

   @Test
   public void testJdbc()
   {
      String sql = "select count(*) FROM organisation";
      int result = jdbcTemplate.queryForObject(sql, Integer.class);
      assertThat(result, is(7));
   }
}
