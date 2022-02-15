package com.example.woc.controller;

import com.example.woc.entity.Account;
import com.example.woc.entity.Role;
import com.example.woc.enums.ErrorEnums;
import com.example.woc.exception.LocalException;
import com.example.woc.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yumo
 * @date 2022/2/14
 */
@Controller
@RequestMapping("/user")
public class UserController {



    @Autowired
    private UserServiceImpl userService;

    /**
     * 首页
     */
    @RequestMapping("/")
    public String index() { return "redirect:/user/login"; }


    /**
     * 完成注册功能
     */

    @RequestMapping("/register")
    public String register(Account account,Model model) throws LocalException {

        //当输入的用户不存在
        if (userService.queryByName(account.getUsername()) == null) {
            userService.addAccount(account);
            model.addAttribute("data","注册成功，请登录！");
            return "login";
        } else {
            model.addAttribute("data","该用户已存在，请重新输入！");
                return "register";
        }
    }

    /**
     * 完成登录功能
     */
    @RequestMapping("/login")
    public void login(Account account, Model model) throws LocalException {

        Account account1=userService.queryByName(account.getUsername());
        //  如果查询的用户存在

        if (account1 != null) {
            if (account1.getPassword().equals(account.getPassword())) {
//                    return "success";
            } else {
                model.addAttribute("data","密码错误，请重试");
//                    return "login";
                throw new LocalException(ErrorEnums.LOGIN_ERROR);
            }
        } else {
            model.addAttribute("data","该用户不存在，请先注册！");
//                return "register";
            throw new LocalException(ErrorEnums.USER_NOT_EXIST_ERROR);
        }
    }
    /**
     * 获取当前登录用户名
     * @return
     */
    public String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.isAuthenticated()){
            return null;
        }
        Object principal = authentication.getPrincipal();
        String username = null;
        if (principal instanceof org.springframework.security.core.userdetails.UserDetails) {
            username =
                    ((Account)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    /**
     * 获取当前用户的权限集合
     * @return
     */
    public List<String> getRole(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.isAuthenticated()){
            return null;
        }
        Object principal = authentication.getPrincipal();
        List<String> roles=new ArrayList<String>();
        if (principal instanceof UserDetails){
            for (Role e:((Account)principal).getRoles()) {
                roles.add(e.getRoleName());
            }
        }
        return roles;
    }

}