package com.quanxiaoha.weblog.admin.model.vo.tag;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchTagReqVO {

    @NotBlank(message = "标签查询关键字不能为空")
    private String key;

}
