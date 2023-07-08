package com.quanxiaoha.weblog.admin.model.vo.blogsetting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author: 犬小哈
 * @url: www.quanxiaoha.com
 * @date: 2023-04-19 16:06
 * @description: TODO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBlogSettingReqVO {
    private Long id;
    @NotBlank(message = "博客名称不能为空")
    private String blogName;
    @NotBlank(message = "博客作者不能为空")
    private String author;
    @NotBlank(message = "作者头像不能为空")
    private String avatar;
    private String introduction;
    private String githubHome;
    private String csdnHome;
    private String giteeHome;
    private String zhihuHome;
}

