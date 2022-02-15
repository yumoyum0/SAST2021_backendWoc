package com.example.woc.service;

import com.example.woc.entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yumo
 * @date 2022/2/14
 */
@Service
public interface UserService extends UserDetailsService {

    List<Account> queryAll();
    Account queryByName(String username);
    Account queryById(Integer id);
    Account queryByEmail(String email);

    boolean deleteAccountByName(String username);
    // 修改用户
    boolean updateAccount(Account account);

    // 新增用户
    void addAccount(Account account) ;

    List<String> getRoleByUsername(String username);

    void deleteAll();
}
