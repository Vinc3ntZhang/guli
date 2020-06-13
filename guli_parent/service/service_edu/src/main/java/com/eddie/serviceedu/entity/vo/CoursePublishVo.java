package com.eddie.serviceedu.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author eddie
 * @Date 2020-06-13 20:12
 * @Version 1.0
 */
@ApiModel(value = "课程发布信息")
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    /**
     * 只用于显示
     */
    private String price;
}
