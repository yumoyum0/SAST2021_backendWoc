package com.example.woc.exception;

import com.example.woc.enums.ErrorEnums;
import lombok.Data;
import lombok.Getter;

/**
 * @author yumo
 * @date 2022/2/14
 */
@Getter
public class LocalException extends Exception{
    ErrorEnums errorEnums;
    public LocalException(String msg) {
        super(msg);
    }
    public LocalException(ErrorEnums errorEnums)
    {
        super(errorEnums.getErrMsg());
        this.errorEnums=errorEnums;
    }
}
