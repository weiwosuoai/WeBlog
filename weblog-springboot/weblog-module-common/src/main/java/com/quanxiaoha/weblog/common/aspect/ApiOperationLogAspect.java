
package com.quanxiaoha.weblog.common.aspect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


@Aspect
@Component
@Slf4j
public class ApiOperationLogAspect {

    // 换行符
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /** 以所有添加 @WebLog 注解为切点 */
    @Pointcut("@annotation(com.quanxiaoha.weblog.common.aspect.ApiOperationLog)")
    public void apiOperationLog() {
    }

    /**
     * 在切点之前织入
     * @param joinPoint
     * @throws Throwable
     */
    @Before("apiOperationLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取日志注解的描述信息
        String methodDescription = getAspectLogDescription(joinPoint);

        // 打印请求相关参数
        log.info("========== 请求开始: [{}], 入参: {} =================================== ", methodDescription, toJson(joinPoint));
        log.warn("请求的类: {}, 方法: {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    }

    /**
     * 在切点之后织入
     * @throws Throwable
     */
    @After("apiOperationLog()")
    public void doAfter() throws Throwable {
        // nothing
    }

    /**
     * 环绕
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("apiOperationLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 耗时
        long timeConsuming = System.currentTimeMillis() - startTime;
        String methodDescription = getAspectLogDescription(proceedingJoinPoint);

        log.info("========== 请求结束: [{}], 耗时: {}ms, 出参: {} =================================== {}", methodDescription, timeConsuming, new ObjectMapper().writeValueAsString(result), LINE_SEPARATOR);
        return result;
    }

    /**
     * 获取切面注解的描述
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public String getAspectLogDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description.append(method.getAnnotation(ApiOperationLog.class).description());
                    break;
                }
            }
        }
        return description.toString();
    }

    /**
     * 转 json
     * @param joinPoint
     * @return
     */
    private String toJson(JoinPoint joinPoint) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(joinPoint.getArgs());
    }

}
