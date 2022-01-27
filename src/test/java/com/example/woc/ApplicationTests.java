package com.example.woc;

import com.example.woc.entity.Account;
import com.example.woc.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
public class ApplicationTests {


   @Test
    public String contextLoads()  {
      return "d";
    }




}