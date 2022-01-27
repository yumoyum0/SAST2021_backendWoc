package com.example.woc.controller;

import com.example.woc.entity.Account;
import com.example.woc.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author: B21031520冯一轩
 * @create: 2022-01-23
 **/
@Controller
@RequestMapping("/user")
public class UserController {



    @Autowired
    private UserServiceImpl userService;
    //登录界面
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }


    /**
     * 完成注册功能
     * 选做：对密码进行加密处理
     */
    @RequestMapping("/register")
    public String register(Account account,Model model) {

        Account account1=userService.queryByName(account.getUsername());
        //当输入的用户不存在
        try {
            if (account1 == null) {
                boolean b = userService.addUser(account);
                if (b) {
                    model.addAttribute("data","注册成功，请登录！");
                    return "/login";
                } else {
                    model.addAttribute("data","注册失败，请重试！");
                    return "register";
                }
            } else {
                model.addAttribute("data","该用户名已存在，请重新输入！");
                return "register";
            }
        }catch (Exception e)
        {
            e.getStackTrace();
            return e.getMessage();
        }

    }

    /**
     * 完成登录功能
     */
    @RequestMapping("/login")
    public String login(Account account, Model model) {

        Account account1=userService.queryByName(account.getUsername());
        //  如果查询的用户存在
        try {
            if (account1 != null) {
                if (account1.getPassword().equals(account.getPassword())) {
                    return "success";
                } else {
                    model.addAttribute("data","密码错误，请重试");
                    return "login";
                }
            } else {
                model.addAttribute("data","该用户不存在，请先注册！");
                return "register";
            }
        }catch (Exception e)
        {
            e.getStackTrace();
            return e.getMessage();
        }

    }
}