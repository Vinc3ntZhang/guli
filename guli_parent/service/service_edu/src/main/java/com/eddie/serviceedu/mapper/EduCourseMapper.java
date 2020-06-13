package com.eddie.serviceedu.mapper;

import com.eddie.serviceedu.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eddie.serviceedu.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author eddie
 * @since 2020-06-10
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    /**
     * 根据课程id查询课程确认信息
     * @param courseId
     * @return 课程确认信息
     */
    public CoursePublishVo getPublishCourseInfo(String courseId);
}
