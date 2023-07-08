package com.quanxiaoha.weblog.admin.service;

import com.quanxiaoha.weblog.common.Response;
import org.springframework.web.multipart.MultipartFile;

public interface AdminFileService {
    Response uploadFile(MultipartFile file);
}
