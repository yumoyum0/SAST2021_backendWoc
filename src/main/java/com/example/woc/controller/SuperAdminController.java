package com.example.woc.controller;

import com.example.woc.entity.Account;
import com.example.woc.entity.Role;
import com.example.woc.enums.ErrorEnums;
import com.example.woc.exception.LocalException;
import com.example.woc.service.UserServiceImpl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author yumo
 * @date 2022/2/14
 */
@RestController
@RequestMapping("/super_admin")
public class SuperAdminController extends AdminController {

    UserServiceImpl userService;
    /**
     * 创建管理员
     */
    @RequestMapping("/newAdmin")
    public void newAdmin(Account account, Model model) throws LocalException {
        //如果该用户不存在
        if (userService.queryByName(account.getUsername()) == null)
        {
            model.addAttribute("message","创建管理员成功");
            account.addRole("ADMIN");
            account.setRole(account.getRoles());
            userService.addAccount(account);
        }
        //如果该用户存在
        else {
            List<String> currentRoles=getRole();
            List<String> targetRoles=account.getRolesName(account.getRoles());
            //目标用户权限是当前用户权限的真子集时，即目标用户权限低于当前用户权限
            if(currentRoles.containsAll(targetRoles) && !(targetRoles.containsAll(currentRoles)))
            {
                model.addAttribute("message","该用户添加管理权限成功");
                account.addRole("ADMIN");
            }
            else throw new LocalException(ErrorEnums.AUTHORITY_ERROR);
        }
    }

    /**
     * 删库跑路
     */
    @RequestMapping("/run")
    public void run() throws LocalException {
        if(getRole().contains("SUPER_ADMIN"))
        {
            userService.deleteAll();
        }
        else throw new LocalException(ErrorEnums.AUTHORITY_ERROR);
    }

}
