package com.quanxiaoha.weblog.admin.model.vo.category;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCategoryReqVO {

    @NotNull(message = "分类 ID 不能为空")
    private Long categoryId;

}
