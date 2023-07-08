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
public enum EventEnum {

    // PV 加 1
    PV_INCREASE("PV INCREASE"),
    ;

    private String message;

}
