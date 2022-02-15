package com.example.woc.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.woc.config.JwtProperties;
import com.example.woc.entity.Account;
import com.example.woc.entity.Role;
import com.example.woc.enums.ErrorEnums;
import com.example.woc.response.GlobalResponse;
import com.example.woc.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author yumo
 * @date 2022/2/14
 * 认证过滤器
 */
@AllArgsConstructor
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private JwtProperties jwtProperties;
    /**
     * 这个方法是用来去尝试验证用户的，父类中是从POST请求的form表单中获取，但是这里不是，所以需要重写
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Account account = JSONObject.parseObject(request.getInputStream(),Account.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            account.getUsername(),
                            account.getPassword())
            );
        } catch (Exception e) {
            try {
                GlobalResponse.fail(ErrorEnums.LOGIN_ERROR);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            throw new RuntimeException(e);
        }
    }

    /**
     * 成功之后执行的方法，父类中是放入session，不符合要求，所以重写
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Account account = new Account();
        account.setUsername(authResult.getName());
        account.setRoles((List<Role>) authResult.getAuthorities());
        String token = JwtUtils.generateTokenExpireInMinutes(account,jwtProperties.getPrivateKey(),24*60);
        response.addHeader("Authorization", "yumoToken " + token);
        try {
            GlobalResponse.success();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

}
