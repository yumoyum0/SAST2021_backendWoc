package com.example.woc.mapper;

import com.example.woc.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yumo
 * @date 2022/2/14
 */
@Mapper
@Repository
public interface UserMapper {


    List<Account> queryAll();
    Account queryByName(@Param("username") String username);
    Account queryById(@Param("id") Integer id);
    Account queryByEmail(@Param("email") String email);

    int deleteAccountByName(@Param("username") String username);
    // 修改用户
    int updateAccount( Account account);

    // 新增用户
    void addAccount(Account account);

    void deleteAll();

}