package com.example.woc.controller;

import com.example.woc.entity.Account;
import com.example.woc.entity.Role;
import com.example.woc.enums.ErrorEnums;
import com.example.woc.exception.LocalException;
import com.example.woc.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yumo
 * @date 2022/2/14
 */
@RestController
@RequestMapping("/admin")
public class AdminController  extends UserController{

    //请仿照 User 补充 Admin 的三层架构并完成接口

    @Autowired
    UserServiceImpl userService;


    /**
     * 获取当前的账户总数
     *
     */
    @RequestMapping("/getAmount")
    public Integer getAmountOfAccounts() throws LocalException {

        if (getRole().contains("ADMIN")){
            List<Account> accounts = userService.queryAll();
            return accounts.size();
        }else throw new LocalException(ErrorEnums.AUTHORITY_ERROR);
    }

    /**
     * 根据用户名删除账户
     * @param username 被删除用户的名字
     */

    @RequestMapping("deleteAccount")
    public void deleteAccount(String username, Model model) throws LocalException {

        List<String> tagetrole=userService.getRoleByUsername(username);
        //当前用户权限真包含被删除用户的权限时
        if ( getRole().containsAll(tagetrole) && !(tagetrole.containsAll(getRole())) ){
            if (userService.queryByName(username) != null) {
                userService.deleteAccountByName(username);
                model.addAttribute("message", "删除用户成功");
            } else {
                model.addAttribute("message", "用户不存在,删除用户失败");
                throw new LocalException(ErrorEnums.USER_NOT_EXIST_ERROR);
            }
        }else {
            throw new LocalException(ErrorEnums.AUTHORITY_ERROR);
        }
    }


    }
