package com.quanxiaoha.weblog.common.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@TableName("t_user")
public class UserDO {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    private Date createTime;
    private Date updateTime;
    private Boolean isDeleted;
}
