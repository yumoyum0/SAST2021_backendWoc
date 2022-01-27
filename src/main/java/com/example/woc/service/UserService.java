package com.example.woc.service;

import com.example.woc.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<Account> queryAll();
    Account queryByName(String username);
    Account queryById(Integer id);

     boolean deleteUserByName(String username);
    // 修改用户
    boolean updateUser(Account user);

    // 新增用户
    boolean addUser(Account user);
}
