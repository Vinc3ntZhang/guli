package com.eddie.servicebase.exceptionhandler;

import com.eddie.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author eddie
 * @Date 2020-06-05 21:14
 * @Version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 指定出现什么异常执行这个方法
     *
     * @param e
     * @return 异常信息处理
     * ResponseBody 为了返回数据
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("执行了全局异常类...");
    }

    /**
     * @param e
     * @return ArithmeticException异常信息处理
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理....");
    }

}
