package com.example.woc.enums;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class RoleEnums {
    private Map<String,Integer> roleMap=new HashMap<String,Integer>();

    public RoleEnums() {
        Map<String,Integer> roleHashMap=new HashMap<String,Integer>();
        roleHashMap.put("USER",0);
        roleHashMap.put("ADMIN",1);
        roleHashMap.put("SUPER_ADMIN",2);
        roleMap=roleHashMap;
    }
    public Integer getCode(String string)
    {
        return (new RoleEnums()).getRoleMap().get(string);
    }
}
