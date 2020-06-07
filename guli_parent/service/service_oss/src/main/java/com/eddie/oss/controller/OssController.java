package com.eddie.oss.controller;

import com.eddie.commonutils.R;
import com.eddie.oss.service.OssService;
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
 * @Date 2020-06-07 10:50
 * @Version 1.0
 */
@Api(tags="阿里云文件管理")
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin//跨域
public class OssController {
    @Autowired
    private OssService ossService;

    @ApiOperation(value = "文件上传")
    @PostMapping
    public R uploadOssFile(@ApiParam(name = "上传文件") MultipartFile file) {
        // 获取上传文件MultipartFile
        // 返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return R.ok().message("文件上传成功").data("url",url);
    }
}
