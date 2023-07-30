package com.quanxiaoha.weblog.web.model.vo.archive;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryArchivePageListRspVO {
    private String month;
    private List<QueryArchiveItemRspVO> articles;
}
