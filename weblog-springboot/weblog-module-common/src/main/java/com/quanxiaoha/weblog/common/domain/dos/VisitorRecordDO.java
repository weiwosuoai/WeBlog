package com.quanxiaoha.weblog.common.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author yjf
 * @description 访客记录表
 */
@Data
@Builder
@TableName("t_visitor_record")
@AllArgsConstructor
@NoArgsConstructor
public class VisitorRecordDO {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String visitor;
    private String ipAddress;
    private String ipRegion;
    private Date visitTime;
    private Integer isNotify;
}

