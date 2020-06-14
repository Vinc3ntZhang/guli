package com.eddie.serviceedu.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author eddie
 * @Date 2020-06-14 14:10
 * @Version 1.0
 */
@Data
@ApiModel(value = "Course查询对象",description = "课程查询对象封装")
public class CourseQuery {

    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布")
    private String status;

}
