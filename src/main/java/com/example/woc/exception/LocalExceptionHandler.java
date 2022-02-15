package com.example.woc.exception;

import com.example.woc.response.GlobalResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yumo
 * @date 2022/2/14
 */
@RestControllerAdvice
public class LocalExceptionHandler {
    @ExceptionHandler(value = LocalException.class)
    public <T> GlobalResponse<T> localException(LocalException e) {
        if (e == null) {
            return GlobalResponse.fail();
        } else {
            return GlobalResponse.fail(e.getErrorEnums());
        }
    }
}
