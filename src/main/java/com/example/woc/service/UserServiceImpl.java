package com.example.woc.service;

import com.example.woc.entity.Account;
import com.example.woc.entity.Role;
import com.example.woc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yumo
 * @date 2022/2/14
 */
@Service
public class UserServiceImpl implements UserService {


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
    public Account queryByEmail(String email) {
        return userMapper.queryByEmail(email);
    }

    @Override
    public boolean deleteAccountByName(String username) {
        return userMapper.deleteAccountByName(username) > 0;
    }

    @Override
    public boolean updateAccount(Account user) {
        return userMapper.updateAccount(user) > 0;
    }

    @Override
    public void addAccount(Account account)  {
        userMapper.addAccount(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.queryByName(username);
    }
    @Override
    public void deleteAll(){userMapper.deleteAll();}
    /**
     * 根据用户名查询权限
     * @param username
     * @return
     */
    public List<String> getRoleByUsername(String username)
    {
        Account account=userMapper.queryByName(username);
        List<String> roles=null;
        for (Role e: account.getRoles())
        {
            roles.add(e.getRoleName());
        }
        return roles;
    }

}