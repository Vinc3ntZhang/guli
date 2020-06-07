package com.eddie.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author eddie
 * @Date 2020-06-07 20:37
 * @Version 1.0
 */
@Data
@AllArgsConstructor  //生成有参数构造方法
@NoArgsConstructor   //生成无参数构造
public class GuliException extends RuntimeException {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String msg;
}
