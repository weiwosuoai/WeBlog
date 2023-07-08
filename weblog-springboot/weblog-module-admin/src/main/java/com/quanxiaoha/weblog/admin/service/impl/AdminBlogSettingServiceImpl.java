package com.quanxiaoha.weblog.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanxiaoha.weblog.admin.model.vo.blogsetting.QueryBlogSettingRspVO;
import com.quanxiaoha.weblog.admin.model.vo.blogsetting.UpdateBlogSettingReqVO;
import com.quanxiaoha.weblog.admin.model.vo.user.QueryUserDetailRspVO;
import com.quanxiaoha.weblog.admin.service.AdminBlogSettingService;
import com.quanxiaoha.weblog.common.Response;
import com.quanxiaoha.weblog.common.domain.mapper.BlogSettingMapper;
import com.quanxiaoha.weblog.common.domain.dos.BlogSettingDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class AdminBlogSettingServiceImpl extends ServiceImpl<BlogSettingMapper, BlogSettingDO> implements AdminBlogSettingService {
    @Override
    public Response updateBlogSetting(UpdateBlogSettingReqVO updateBlogSettingReqVO) {
        BlogSettingDO blogSettingDO = BlogSettingDO.builder()
                .id(1L)
                .blogName(updateBlogSettingReqVO.getBlogName())
                .author(updateBlogSettingReqVO.getAuthor())
                .avatar(updateBlogSettingReqVO.getAvatar())
                .introduction(updateBlogSettingReqVO.getIntroduction())
                .githubHome(updateBlogSettingReqVO.getGithubHome())
                .giteeHome(updateBlogSettingReqVO.getGiteeHome())
                .csdnHome(updateBlogSettingReqVO.getCsdnHome())
                .zhihuHome(updateBlogSettingReqVO.getZhihuHome())
                .build();
        saveOrUpdate(blogSettingDO);
        return Response.success();
    }

    @Override
    public Response queryBlogSettingDetail() {
        BlogSettingDO blogSettingDO = getOne(null);

        QueryBlogSettingRspVO queryBlogSettingRspVO = null;
        if (Objects.nonNull(blogSettingDO)) {
            queryBlogSettingRspVO = QueryBlogSettingRspVO.builder()
                    .id(blogSettingDO.getId())
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

    @Override
    public Response<QueryUserDetailRspVO> queryNicknameAndAvatar() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        QueryUserDetailRspVO queryBlogSettingRspVO = QueryUserDetailRspVO.builder().username(username).build();

        BlogSettingDO blogSettingDO = getOne(null);
        queryBlogSettingRspVO.setAvatar(blogSettingDO.getAvatar());
        return Response.success(queryBlogSettingRspVO);
    }
}
