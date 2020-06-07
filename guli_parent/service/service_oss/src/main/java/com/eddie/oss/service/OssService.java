package com.eddie.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author eddie
 * @Date 2020-06-07 10:50
 * @Version 1.0
 */
public interface OssService {
    /**
     * 上传文件到oss
     * @param file
     * @return url
     */
    String uploadFileAvatar(MultipartFile file);
}
