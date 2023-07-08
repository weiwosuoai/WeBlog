package com.quanxiaoha.weblog.common.exception;

import com.quanxiaoha.weblog.common.Response;
import com.quanxiaoha.weblog.common.enums.ResponseCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author: 犬小哈
 * @url: www.quanxiaoha.com
 * @date: 2023-04-18 8:24
 * @description: 全局异常捕获
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({ AccessDeniedException.class })
    public void throwAccessDeniedException(AccessDeniedException e) throws AccessDeniedException {
        log.info("============= 捕获到 AccessDeniedException");
        throw e;
    }

    /**
     * 业务异常
     * @return
     */
    @ExceptionHandler({ BizException.class })
    @ResponseBody
    public Response<Object> handleException(HttpServletRequest request, BizException e) {
        log.error("{} request error, errorCode: {}, errorMessage: {}", request.getRequestURI(), e.getErrorCode(), e.getErrorMessage());
        return Response.fail(e);
    }

    /**
     * 参数校验异常
     * @return
     */
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @ResponseBody
    public Response<Object> methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        String errorCode = ResponseCodeEnum.PARAM_ERROR.getErrorCode();
        BindingResult result = e.getBindingResult();
        StringBuilder sb = new StringBuilder();

        Optional.ofNullable(result.getFieldErrors()).ifPresent(errors -> {
            errors.forEach(error -> {
                sb.append(error.getField())
                        .append(" ")
                        .append(error.getDefaultMessage())
                        .append(", 当前值: '")
                        .append(error.getRejectedValue())
                        .append("'; ");
            });
        });

        String message = sb.toString();

        log.error("{} request error, errorCode: {}, errorMessage: {}", request.getRequestURI(), errorCode, message);

        return Response.fail(errorCode, message);
    }

    // /**
    //  * 401
    //  * @return
    //  */
    // @ExceptionHandler({ AuthenticationException.class })
    // @ResponseBody
    // @ResponseStatus(HttpStatus.UNAUTHORIZED)
    // public Response<Object> handleException(HttpServletRequest request, AuthenticationException e) {
    //     log.error("{} request error, ", request.getRequestURI(), e);
    //     return Response.fail(ResponseCodeEnum.UNAUTHORIZED);
    // }


    /**
     * 其他异常
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response<Object> handleException(HttpServletRequest request, Exception e) {
        log.error("{} request error, ", request.getRequestURI(), e);
        return Response.fail(ResponseCodeEnum.SYSTEM_ERROR);
    }
}
