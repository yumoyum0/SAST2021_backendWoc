package com.example.woc.response;
import com.alibaba.fastjson.JSON;
import com.example.woc.enums.ErrorEnums;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author yumo
 * @date 2022/2/14
 */
@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body == null) {
            return GlobalResponse.success();
        }
        if (body instanceof GlobalResponse) {
            return body;
        }
        if (body instanceof String) {
            return JSON.toJSON(GlobalResponse.success(body)).toString();
        }
        if (body instanceof ErrorEnums) {
            return GlobalResponse.fail((ErrorEnums) body);
        }
        return GlobalResponse.success(body);
    }
}
