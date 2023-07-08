package com.quanxiaoha.weblog.common;

import com.quanxiaoha.weblog.common.exception.BaseExceptionInterface;
import com.quanxiaoha.weblog.common.exception.BizException;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: 犬小哈
 * @from: 公众号：小哈学Java, 网站：www.quanxiaoha.com
 * @date: 2023-04-16 10:50
 * @version: v1.0.0
 * @description: 响应类
 **/
@Data
public class Response<T> implements Serializable {

    private boolean success = true;
    private String errorCode;
    private String message;
    private T data;

    public static <T> Response<T> fail() {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        return response;
    }

    public static <T> Response<T> fail(String errorMessage) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMessage(errorMessage);
        return response;
    }

    public static <T> Response<T> fail(String errorCode, String errorMessage) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(errorCode);
        response.setMessage(errorMessage);
        return response;
    }

    public static <T> Response<T> fail(BaseExceptionInterface baseExceptionInterface) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(baseExceptionInterface.getErrorCode());
        response.setMessage(baseExceptionInterface.getErrorMessage());
        return response;
    }

    public static <T> Response<T> fail(BizException bizException) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(bizException.getErrorCode());
        response.setMessage(bizException.getErrorMessage());
        return response;
    }

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setData(data);
        return response;
    }

    public static <T> Response<T> success() {
        Response<T> response = new Response<>();
        return response;
    }

}
