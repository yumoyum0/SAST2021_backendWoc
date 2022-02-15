package com.example.woc.entity;

import com.example.woc.enums.RoleEnums;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author yumo
 * @date 2022/2/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {
    private Integer id ;
    private String roleName;
    private String roleDesc;

    public Role(String roleName) {
        this.roleName=roleName;
        this.id=(new RoleEnums()).getCode(roleName);
    }

    public Role(String roleName,Integer id) {
        this.id = id;
        this.roleName = roleName;
    }

    @JsonIgnore
    @Override
    public String getAuthority() {
        return this.roleName;
    }
}
