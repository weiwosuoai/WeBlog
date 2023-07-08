package com.quanxiaoha.weblog.common.eventbus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @author: 犬小哈
 * @url: www.quanxiaoha.com
 * @date: 2023-07-01 21:39
 * @description: TODO
 **/
@Getter
@Builder
public class ArticleEvent {
    private Long articleId;
    private String message;
}
