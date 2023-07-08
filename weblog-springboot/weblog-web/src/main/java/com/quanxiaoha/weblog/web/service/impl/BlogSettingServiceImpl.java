package com.quanxiaoha.weblog.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanxiaoha.weblog.common.Response;
import com.quanxiaoha.weblog.common.domain.mapper.BlogSettingMapper;
import com.quanxiaoha.weblog.common.domain.dos.BlogSettingDO;
import com.quanxiaoha.weblog.web.model.vo.blogsetting.QueryBlogSettingRspVO;
import com.quanxiaoha.weblog.web.service.BlogSettingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author: 犬小哈
 * @url: www.quanxiaoha.com
 * @date: 2023-04-17 12:08
 * @description: TODO
 **/
@Service
@Slf4j
public class BlogSettingServiceImpl extends ServiceImpl<BlogSettingMapper, BlogSettingDO> implements BlogSettingService {

    @Override
    public Response queryBlogSettingDetail() {
        BlogSettingDO blogSettingDO = getOne(null);

        QueryBlogSettingRspVO queryBlogSettingRspVO = null;
        if (Objects.nonNull(blogSettingDO)) {
            queryBlogSettingRspVO = QueryBlogSettingRspVO.builder()
                    .blogName(blogSettingDO.getBlogName())
                    .author(blogSettingDO.getAuthor())
                    .avatar(blogSettingDO.getAvatar())
                    .introduction(blogSettingDO.getIntroduction())
                    .githubHome(blogSettingDO.getGithubHome())
                    .csdnHome(blogSettingDO.getCsdnHome())
                    .giteeHome(blogSettingDO.getGiteeHome())
                    .zhihuHome(blogSettingDO.getZhihuHome())
                    .build();
        }
        return Response.success(queryBlogSettingRspVO);
    }
}
