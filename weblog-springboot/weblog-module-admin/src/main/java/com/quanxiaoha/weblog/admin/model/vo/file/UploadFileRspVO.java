package com.quanxiaoha.weblog.admin.model.vo.file;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileRspVO {
    private String url;
}
