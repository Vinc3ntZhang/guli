package com.eddie.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author eddie
 * @Date 2020-06-14 18:34
 * @Version 1.0
 */
public interface VodService {

    /**
     * 上传视频到阿里云
     * @param file
     * @return videoId视频id
     */
    String uploadAliyunVideo(MultipartFile file);
}
