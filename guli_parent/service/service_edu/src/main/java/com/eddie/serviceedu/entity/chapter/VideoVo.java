package com.eddie.serviceedu.entity.chapter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author eddie
 * @Date 2020-06-11 19:06
 * @Version 1.0
 */
@ApiModel(value = "课时信息查询对象",description = "课程查询对象封装")
@Data
public class VideoVo {

    @ApiModelProperty(value = "小节ID")
    private String id;

    @ApiModelProperty(value = "小节标题")
    private String title;

}
