package com.quanxiaoha.weblog.common.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@TableName("t_tag")
public class TagDO {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Date createTime;
    private Date updateTime;
    private Boolean isDeleted;
}
