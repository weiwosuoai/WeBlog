package com.quanxiaoha.weblog.web.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.quanxiaoha.weblog.common.PageResponse;
import com.quanxiaoha.weblog.common.Response;
import com.quanxiaoha.weblog.common.constant.Constants;
import com.quanxiaoha.weblog.common.domain.dos.*;
import com.quanxiaoha.weblog.common.domain.mapper.ArticleMapper;
import com.quanxiaoha.weblog.web.convert.ArticleConvert;
import com.quanxiaoha.weblog.web.dao.ArticleDao;
import com.quanxiaoha.weblog.web.model.vo.archive.QueryArchiveItemRspVO;
import com.quanxiaoha.weblog.web.model.vo.archive.QueryArchivePageListReqVO;
import com.quanxiaoha.weblog.web.model.vo.archive.QueryArchivePageListRspVO;
import com.quanxiaoha.weblog.web.service.ArchiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: 犬小哈
 * @url: www.quanxiaoha.com
 * @date: 2023-04-17 12:08
 * @description: TODO
 **/
@Service
@Slf4j
public class ArchiveServiceImpl extends ServiceImpl<ArticleMapper, ArticleDO> implements ArchiveService {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleConvert articleConvert;

    @Override
    public Response queryArchive(QueryArchivePageListReqVO queryArchivePageListReqVO) {
        Long current = queryArchivePageListReqVO.getCurrent();
        Long size = queryArchivePageListReqVO.getSize();

        IPage<ArticleDO> articleDOIPage = articleDao.queryArticlePageList(current, size);
        List<ArticleDO> records = articleDOIPage.getRecords();

        List<QueryArchivePageListRspVO> list = Lists.newArrayList();
        List<QueryArchiveItemRspVO> itemRspVOList = null;
        if (!CollectionUtils.isEmpty(records)) {
            itemRspVOList = records.stream()
                    .map(articleDO -> articleConvert.convert2Archive(articleDO))
                    .collect(Collectors.toList());

            Map<String, List<QueryArchiveItemRspVO>> map = itemRspVOList.stream().collect(Collectors.groupingBy(QueryArchiveItemRspVO::getCreateMonth));
            Map<String, List<QueryArchiveItemRspVO>> sortedMap = new TreeMap<>(new MonthKeyComparator());
            sortedMap.putAll(map);

            sortedMap.forEach((k, v) -> list.add(QueryArchivePageListRspVO.builder().month(k).articles(v).build()));
        }
        return PageResponse.success(articleDOIPage, list);
    }

    class MonthKeyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            // 使用 YearMonth 类将字符串解析成日期，并根据日期进行倒序排序
            YearMonth ym1 = YearMonth.parse(o1);
            YearMonth ym2 = YearMonth.parse(o2);
            return ym2.compareTo(ym1);
        }
    }
}
