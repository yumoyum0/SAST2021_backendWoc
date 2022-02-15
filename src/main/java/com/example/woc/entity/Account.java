package com.example.woc.entity;

import com.example.woc.enums.RoleEnums;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.thymeleaf.expression.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * @author yumo
 * @date 2022/2/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Integer status =1;
    private Integer role;
    private List<Role> roles= new ArrayList<>();

    {
        roles.add(new Role("USER"));
    }
    public void setRole(List<Role> alist)
    {
        List<String> list=getRolesName(alist);
        List<Integer> integerList=new ArrayList<Integer>();
        for (String e:list)
        {
            integerList.add(new RoleEnums().getCode(e));
        }
        role=Collections.max(integerList);
    }

    public List<String> getRolesName(List<Role> roleList)
    {
        List<String> stringList = new ArrayList<String>();
        for(Role e:roleList)
        {
            stringList.add(e.getRoleName());
        }
        return stringList;
    }
    public void addRole(String rolename)
    {
        roles.add(new Role(rolename));
    }
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() { //指示用户的帐户是否已过期
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {   //指示用户是否被锁定或解锁
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {  //指示用户的凭据（密码）是否已过期
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {    //指示用户是否被启用或禁用
        return status==1;
    }
}