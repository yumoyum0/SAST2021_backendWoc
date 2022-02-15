package com.example.woc.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author yumo
 * @date 2022/2/14
 */
@Data
public class Payload<T> {
    private String id;
    private T userInfo;
    private Date expiration;
}
