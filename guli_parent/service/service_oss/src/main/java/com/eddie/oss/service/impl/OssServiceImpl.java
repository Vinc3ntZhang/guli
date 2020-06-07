package com.eddie.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.eddie.oss.service.OssService;
import com.eddie.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @Author eddie
 * @Date 2020-06-07 10:51
 * @Version 1.0
 */
@Service
public class OssServiceImpl implements OssService {
    /**
     * 上传文件到oss
     *
     * @param file
     * @return url
     */
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        // 工具类获取值
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 获取文件名称
            String fileName = file.getOriginalFilename();
            // 上传文件流。
            InputStream inputStream = file.getInputStream();

            // 1、在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            fileName = uuid + fileName;
            // 2、把文件按照日期进行分类
            // 2020/06/07/01.jpg
            // 获取当前日期
            String datePath = new DateTime().toString("yyyy/MM/dd");
            // 拼接
            // 2020/06/07/01.jpg
            fileName = datePath + "/" + fileName;

            // 第一个参数 Bucket名称
            // 第二个参数 上传到oss文件路径和文件名称    /aa/bb/1.jpg
            // 第三个参数 上传文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            // 把上传之后文件路径返回
            // 需要把上传到阿里云oss路径手动拼接出来
            // https://eddie-study.oss-cn-shenzhen.aliyuncs.com/
            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
