package com.example.woc.service;

import com.example.woc.entity.Account;
import com.example.woc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: B21031520冯一轩
 * @create: 2022-01-23
 **/
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;



    @Override
    public List<Account> queryAll() {
        return userMapper.queryAll();
    }

    @Override
    public Account queryByName(String username) {
        return userMapper.queryByName(username);
    }

    @Override
    public Account queryById(Integer id) {
        return userMapper.queryById(id);
    }


    @Override
    public boolean deleteUserByName(String username) {
        return userMapper.deleteUserByName(username) > 0;
    }

    @Override
    public boolean updateUser(Account user) {
        return userMapper.updateUser(user) > 0;
    }

    @Override
    public boolean addUser(Account user) {
        return userMapper.addUser(user) > 0;
    }

}