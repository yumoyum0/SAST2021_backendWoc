package com.example.woc.response;

import com.example.woc.enums.ErrorEnums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author yumo
 * @date 2022/2/14
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GlobalResponse<T> {
    private boolean success;
    private Integer errCode;
    private String errMsg;
    private T data;


    public static <T> GlobalResponse<T> success() {
        return success(null);
    }

    public static <T> GlobalResponse<T> success(T data) {
        return new GlobalResponse(true,null,null,data);
    }

    public static <T> GlobalResponse<T> fail() {
        return fail(ErrorEnums.COMMON_ERROR);
    }

    public static <T> GlobalResponse<T> fail(ErrorEnums errorEnum) {
        return  new GlobalResponse<>(false, errorEnum.getErrCode(), errorEnum.getErrMsg(), null);
    }
}

