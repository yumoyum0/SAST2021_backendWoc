package com.example.woc.config;

import com.example.woc.filter.JwtLoginFilter;
import com.example.woc.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author yumo
 * @date 2022/2/14
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableConfigurationProperties(JwtProperties.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    private final UserService userService;
    private final JwtProperties jwtProperties;



    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证用户的来源
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //数据库中
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/super_admin/**").hasRole("SUPER_ADMIN")
                .and()
                .formLogin()
                .and()
                .addFilter(new JwtLoginFilter(super.authenticationManager(),jwtProperties))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }


}
