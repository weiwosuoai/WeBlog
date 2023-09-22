package com.quanxiaoha.weblog.common.enums;

import com.quanxiaoha.weblog.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: 犬小哈
 * @url: www.quanxiaoha.com
 * @date: 2023-04-18 8:14
 * @description: 响应枚举
 **/
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements BaseExceptionInterface {

    // ----------- 通用异常状态码 -----------
    SYSTEM_ERROR("10000", "出错啦，后台小哥正在努力修复中"),
    PARAM_ERROR("10001", "参数错误"),

    // ----------- 业务异常状态码 -----------
    UNAUTHORIZED("10002", "无访问权限，请先登录！"),
    FORBIDDEN("10003", "演示账号仅支持查询操作！"),
    NO_TOKEN_OR_TOKEN_INVALID("10004", "Token 丢失或 Token 不可用！"),
    LOGIN_FAIL("10005", "登录失败"),
    USERNAME_OR_PWD_ERROR("10006", "用户名或密码错误"),
    UPLOAD_FILE_ERROR("10007", "文件上传失败"),
    DUPLICATE_TAG_ERROR("10008", "提交的部分标签已被创建"),
    DUPLICATE_CATEGORY_ERROR("10009", "该分类已被创建"),
    TOKEN_EXPIRED("10010", "Token 已过期"),

    /**
     * code: 20001 <br/>
     * info: 访客IP归属地查询失败
     */
    AGENT_REGION_SEARCH_ERROR("20001", "访客IP归属地查询失败"),
    ;

    private String errorCode;
    private String errorMessage;

}
