package com.eddie.vod.controller;

import com.eddie.commonutils.R;
import com.eddie.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author eddie
 * @Date 2020-06-14 18:34
 * @Version 1.0
 */
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
@Api(tags = "视频上传")
public class VodController {

    @Autowired
    private VodService vodService;

    /**
     * 上传视频到阿里云
     * @param file
     * @return videoId视频id
     */
    @ApiOperation(value = "上传视频到阿里云")
    @PostMapping("uploadAliyunVideo")
    public R uploadAliyunVideo(@ApiParam(name = "上传视频") MultipartFile file){
        // 返回上传视频id
        String videoId = vodService.uploadAliyunVideo(file);
        return R.ok().data("videoId",videoId);
    }

}
