package com.eddie.serviceedu.controller;

import com.eddie.commonutils.R;
import com.eddie.serviceedu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author eddie
 * @Date 2020-06-06 11:23
 * @Version 1.0
 */
@Api(tags = "讲师登录")
@RestController
@RequestMapping("/serviceedu/user")
@CrossOrigin //解决跨域
public class EduLoginController {
    @Autowired
    private EduTeacherService eduTeacherService;

    /**
     * 模拟讲师登录
     *
     * @return token
     */
    @ApiOperation(value = "讲师登录")
    @PostMapping("login")
    public R login() {
        return R.ok().data("token", "admin");
    }

    /**
     * 模拟获取讲师信息
     *
     * @return map
     */
    @ApiOperation(value = "讲师信息")
    @GetMapping("info")
    public R info() {
        return R.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
