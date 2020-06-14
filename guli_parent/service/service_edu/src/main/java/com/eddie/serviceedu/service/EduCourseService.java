package com.eddie.serviceedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eddie.serviceedu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eddie.serviceedu.entity.vo.CourseInfoVo;
import com.eddie.serviceedu.entity.vo.CoursePublishVo;
import com.eddie.serviceedu.entity.vo.CourseQuery;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author eddie
 * @since 2020-06-10
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 添加课程基本信息的方法
     * @param courseInfoVo
     * @return
     */
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * 根据课程id查询基本信息
     * @param courseId
     * @return CourseInfoVo对象
     */
    CourseInfoVo getCourseInfo(String courseId);

    /**
     * 修改课程信息
     * @param courseInfoVo
     */
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * 根据课程id查询课程确认信息
     * @param id
     * @return CoursePublishVo对象
     */
    CoursePublishVo getPublishCourseInfo(String id);

    /**
     * 多条件课程分页查询
     * @param coursePage
     * @param courseQuery
     */
    void pageQuery(Page<EduCourse> coursePage, CourseQuery courseQuery);

    void removeCourse(String courseId);
}
