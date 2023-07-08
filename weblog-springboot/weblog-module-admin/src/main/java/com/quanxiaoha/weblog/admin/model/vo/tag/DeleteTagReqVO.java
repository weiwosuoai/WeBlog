package com.quanxiaoha.weblog.admin.model.vo.tag;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteTagReqVO {

    @NotNull(message = "标签 ID 不能为空")
    private Long tagId;

}
