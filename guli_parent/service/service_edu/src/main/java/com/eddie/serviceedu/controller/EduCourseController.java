package com.eddie.serviceedu.controller;


import com.eddie.commonutils.R;
import com.eddie.serviceedu.entity.vo.CourseInfoVo;
import com.eddie.serviceedu.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author eddie
 * @since 2020-06-10
 */
@RestController
@Api(tags = "课程管理")
@RequestMapping("/serviceedu/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    @ApiOperation(value = "添加课程信息")
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@ApiParam(name = "课程信息", value = "课程信息") @RequestBody CourseInfoVo courseInfoVo) {
        // 返回添加之后课程id，为了后面添加大纲使用
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }

}

