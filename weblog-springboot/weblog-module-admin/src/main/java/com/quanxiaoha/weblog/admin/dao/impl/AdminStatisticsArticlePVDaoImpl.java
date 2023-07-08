package com.quanxiaoha.weblog.admin.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.quanxiaoha.weblog.admin.dao.AdminStatisticsArticlePVDao;
import com.quanxiaoha.weblog.common.domain.dos.StatisticsArticlePVDO;
import com.quanxiaoha.weblog.common.domain.mapper.StatisticsArticlePVMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: 犬小哈
 * @url: www.quanxiaoha.com
 * @date: 2023-04-17 12:08
 * @description: TODO
 **/
@Service
@Slf4j
public class AdminStatisticsArticlePVDaoImpl implements AdminStatisticsArticlePVDao {

    @Autowired
    private StatisticsArticlePVMapper statisticsArticlePVMapper;

    @Override
    public void pvIncrease(Date currDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(currDate);

        QueryWrapper<StatisticsArticlePVDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().apply("pv_date = {0}", dateStr);
        Long count = statisticsArticlePVMapper.selectCount(queryWrapper);

        if (count > 0) {
            UpdateWrapper<StatisticsArticlePVDO> updateWrapper = new UpdateWrapper<>();
            updateWrapper.lambda().setSql("pv_count = pv_count + 1").apply("pv_date = {0}", dateStr);
            statisticsArticlePVMapper.update(null, updateWrapper);
        } else {
            StatisticsArticlePVDO statisticsArticlePVDO = StatisticsArticlePVDO.builder()
                    .pvCount(1L)
                    .pvDate(currDate)
                    .createTime(new Date())
                    .build();
            statisticsArticlePVMapper.insert(statisticsArticlePVDO);
        }
    }

    @Override
    public List<StatisticsArticlePVDO> selectLatestWeekRecords() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(date);

        QueryWrapper<StatisticsArticlePVDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().apply("pv_date <= {0}", dateStr)
                .orderByDesc(StatisticsArticlePVDO::getPvDate)
                .last("limit 7");
        return statisticsArticlePVMapper.selectList(wrapper);
    }

    @Override
    public List<StatisticsArticlePVDO> selectAllPVCount() {
        return statisticsArticlePVMapper.selectList(null);
    }
}
