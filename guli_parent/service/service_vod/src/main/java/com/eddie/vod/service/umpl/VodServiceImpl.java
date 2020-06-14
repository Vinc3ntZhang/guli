package com.eddie.vod.service.umpl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.eddie.servicebase.exceptionhandler.GuliException;
import com.eddie.vod.service.VodService;
import com.eddie.vod.util.ConstantPropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author eddie
 * @Date 2020-06-14 18:35
 * @Version 1.0
 */
@Service
public class VodServiceImpl implements VodService {

    /**
     * 上传视频到阿里云
     *
     * @param file
     * @return videoId视频id
     */
    @Override
    public String uploadAliyunVideo(MultipartFile file) {
        try {
            // inputStream上传文件输入流
            InputStream inputStream = file.getInputStream();
            // originalFilename上传文件原始名称
            String originalFilename = file.getOriginalFilename();
            // title上传之后显示名称
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));

            UploadStreamRequest request = new UploadStreamRequest(
                    ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET,
                    title, originalFilename, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
            // 其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            String videoId = response.getVideoId();
            if (!response.isSuccess()) {
                String errorMessage = "阿里云上传错误：" + "code：" + response.getCode() + ", message：" + response.getMessage();
                System.out.println(errorMessage);
                if(StringUtils.isEmpty(videoId)){
                    throw new GuliException(20001, errorMessage);
                }
            }

            return videoId;
        } catch (IOException e) {
            throw new GuliException(20001, "guli vod 服务上传失败");
        }
    }
}
