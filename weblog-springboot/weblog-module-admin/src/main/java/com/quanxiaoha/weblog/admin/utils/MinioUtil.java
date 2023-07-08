package com.quanxiaoha.weblog.admin.utils;

import com.quanxiaoha.weblog.admin.config.MinioProperties;
import com.quanxiaoha.weblog.common.exception.BizException;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author: 犬小哈
 * @url: www.quanxiaoha.com
 * @date: 2023-05-11 9:02
 * @description: TODO
 **/
@Component
@Slf4j
public class MinioUtil {

    @Autowired
    private MinioProperties minioProperties;

    @Autowired
    private MinioClient minioClient;

    public String uploadFile(MultipartFile file) throws Exception {
        if (file == null || file.getSize() == 0) {
            log.error("==> 上传文件异常：文件大小为空 ...");
            throw new RuntimeException("文件大小不能为空");
        }

        String originalFileName = file.getOriginalFilename();
        String contentType = file.getContentType();

        String key = UUID.randomUUID().toString().replace("-", "");
        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));

        String objectName = String.format("%s%s", key, suffix);

        log.info("==> 开始上传文件至 Minio, ObjectName: {}", objectName);
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(minioProperties.getBucketName())
                .object(objectName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(contentType)
                .build());

        String url = String.format("%s/%s/%s", minioProperties.getEndpoint(), minioProperties.getBucketName(), objectName);
        log.info("==> 上传文件至 Minio 成功，访问路径: {}", url);
        return url;
    }
}
