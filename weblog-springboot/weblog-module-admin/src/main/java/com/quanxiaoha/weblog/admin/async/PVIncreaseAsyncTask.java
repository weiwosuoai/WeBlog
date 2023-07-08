package com.quanxiaoha.weblog.admin.async;

import com.quanxiaoha.weblog.admin.dao.AdminArticleDao;
import com.quanxiaoha.weblog.admin.dao.AdminStatisticsArticlePVDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: 犬小哈
 * @url: www.quanxiaoha.com
 * @date: 2023-07-02 9:31
 * @description: TODO
 **/
@Service
@Slf4j
public class PVIncreaseAsyncTask {

    @Autowired
    private AdminArticleDao articleDao;
    @Autowired
    private AdminStatisticsArticlePVDao statisticsArticlePVDao;

    @Async
    public void handle(Long articleId) {
        log.info("## 文章被阅读量异步 +1, articleId: {}", articleId);
        articleDao.readNumIncrease(articleId);

        Date currDate = new Date();
        log.info("## 文章 PV 异步 +1, currDate: {}", currDate);
        statisticsArticlePVDao.pvIncrease(currDate);
    }
}
