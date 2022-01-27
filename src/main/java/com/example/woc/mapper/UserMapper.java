package com.example.woc.mapper;

import com.example.woc.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 01:22
 **/
@Mapper
@Repository
public interface UserMapper {


    List<Account> queryAll();
    Account queryByName(String username);
    Account queryById(Integer id);

    int deleteUserByName(String username);
    // 修改用户
    int updateUser(Account user);

    // 新增用户
    int addUser(Account user);
}