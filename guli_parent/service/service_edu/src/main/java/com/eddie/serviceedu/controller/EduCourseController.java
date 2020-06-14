package com.eddie.serviceedu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eddie.commonutils.R;
import com.eddie.serviceedu.entity.EduCourse;
import com.eddie.serviceedu.entity.vo.CourseInfoVo;
import com.eddie.serviceedu.entity.vo.CoursePublishVo;
import com.eddie.serviceedu.entity.vo.CourseQuery;
import com.eddie.serviceedu.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return R.ok().data("courseId", id);
    }

    /**
     * 根据课程id查询基本信息
     *
     * @param courseId
     * @return courseInfoVo对象
     */
    @ApiOperation(value = "根据课程id查询基本信息")
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@ApiParam(name = "课程id") @PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo", courseInfoVo);
    }

    /**
     * 修改课程信息
     *
     * @param courseInfoVo
     * @return
     */
    @ApiOperation(value = "根据课程id修改课程")
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    /**
     * 根据课程id查询课程确认信息
     *
     * @param id
     * @return CoursePublishVo对象
     */
    @ApiOperation(value = "根据课程id查询课程确认信息")
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = courseService.getPublishCourseInfo(id);
        return R.ok().data("publishCourse", coursePublishVo);
    }

    /**
     * 课程最终发布，修改课程状态
     *
     * @return json成功信息
     */
    @ApiOperation(value = "课程最终发布，修改课程状态")
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@ApiParam(name = "课程id") @PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        boolean result = courseService.updateById(eduCourse);
        if (result) {
            return R.ok();
        }else {
            return R.error().message("课程发布失败");
        }
    }

    /**
     * 多条件课程分页查询
     * @return 课程列表list集合
     */
    @ApiOperation(value = "课程列表")
    @PostMapping("courseList/{current}/{limit}")
    public R courseList(
            @ApiParam(name = "当前页数") @PathVariable long current,
            @ApiParam(name = "每页记录数") @PathVariable long limit,
            @ApiParam(name = "前端传入数据对象") @RequestBody(required = false)CourseQuery courseQuery
            ){
        System.out.println("==============="+courseQuery);
        // 创建page对象
        Page<EduCourse> coursePage = new Page<>(current,limit);
        // 调用service方法进行分页查询
        courseService.pageQuery(coursePage,courseQuery);
        // 将结果返回
        //数据list集合
        List<EduCourse> records = coursePage.getRecords();
        // 总记录数
        Long total = coursePage.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }

    /**
     * 删除课程：
     * 1、根据课程id删除小节
     * 2、根据课程id删除章节
     * 3、根据课程id删除描述
     * 4、根据课程id删除课程本身
     * @param courseId
     * @return json成功信息
     */
    @DeleteMapping("{courseId}")
    public R removeCourse(@ApiParam(name = "课程id")@PathVariable String courseId){
        courseService.removeCourse(courseId);
        return R.ok();
    }

}

