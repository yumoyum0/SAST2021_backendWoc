package com.example.woc.controller;

import com.example.woc.entity.Account;
import com.example.woc.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author: B21031520冯一轩
 * @create: 2022-01-15 04:19
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {

    //请仿照 User 补充 Admin 的三层架构并完成接口

    @Autowired
    UserServiceImpl userService;

    /**
     * 获取当前的账户总数
     *
     */
    @GetMapping("/getAmount")
    public Integer getAmountOfAccounts() {

        List<Account> accounts = userService.queryAll();
        return accounts.size();
    }

    /**
     * 根据用户名删除账户
     *
     */
    @PutMapping("deleteAccount")
    public void deleteAccount(String username, RedirectAttributes attributes) {

        boolean b = userService.deleteUserByName(username);
        if (b) {
            attributes.addFlashAttribute("message", "删除用户成功");

        } else {
            attributes.addFlashAttribute("message", "删除用户失败");
        }
    }

    /**
     * 编辑账户
     */
    @PutMapping("editAccount")
    public void editAccount(Account account,RedirectAttributes attributes)
    {
        Integer id=account.getId();
        if(id!=null)//更新
        {
            //当输入的用户名不存在
            if (userService.queryByName(account.getUsername()).getId()==null)
            {
                boolean b=userService.updateUser(account);
                if (b) {
                    attributes.addFlashAttribute("message", "更新用户成功");

                } else {
                    attributes.addFlashAttribute("message", "更新用户失败");
                }
            }
            else attributes.addAttribute("message","该用户名已存在");

        }
        else//添加
        {
            //当输入的用户名不存在
            if (userService.queryByName(account.getUsername()).getId()==null)
            {
                boolean b=userService.addUser(account);
                if (b) {
                    attributes.addFlashAttribute("message", "添加用户成功");

                } else {
                    attributes.addFlashAttribute("message", "添加用户失败");
                }
            }
            else
                attributes.addAttribute("message","该用户名已存在");
        }
    }
}