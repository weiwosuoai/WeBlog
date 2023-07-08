package com.quanxiaoha.weblog.common.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@TableName("t_statistics_article_pv")
public class StatisticsArticlePVDO {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Date pvDate;
    private Long pvCount;
    private Date createTime;
}
