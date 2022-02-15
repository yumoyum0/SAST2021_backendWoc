package com.example.woc.enums;

import lombok.AllArgsConstructor;

/**
 * @author yumo
 * @date 2022/2/14
 */
@AllArgsConstructor
public enum ErrorEnums {
    COMMON_ERROR(1000, "错误"),
    PARAMS_LOSS_ERROR(1001, "参数不足"),
    LOGIN_ERROR(2000, "账号或密码错误"),
    USER_NOT_EXIST_ERROR(2001, "用户不存在"),
    USER_EXIST_ERROR(2002, "用户已存在"),
    AUTHORITY_ERROR(4000, "权限不足");

    private final int errCode;
    private final String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public int getErrCode() {
        return errCode;
    }
}
