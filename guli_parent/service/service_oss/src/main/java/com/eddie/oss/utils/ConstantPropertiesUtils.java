package com.eddie.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author eddie
 * @Date 2020-06-07 10:36
 * @Version 1.0
 * 当项目一启动，spring接口，spring加载之后，执行接口中的一个方法
 */
@Component
public class ConstantPropertiesUtils implements InitializingBean {
    /**
     * 读取配置文件内容
     */
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyid;

    @Value("${aliyun.oss.file.keysecret}")
    private String keysecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketname;

    /**
     * 定义公开静态常量
     */
    public static String END_POINT;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String BUCKET_NAME;

    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        KEY_ID = keyid;
        KEY_SECRET = keysecret;
        BUCKET_NAME = bucketname;
    }
}
