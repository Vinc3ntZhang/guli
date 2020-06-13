package com.eddie.serviceedu.entity.chapter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author eddie
 * @Date 2020-06-11 19:06
 * @Version 1.0
 */
@ApiModel(value = "章节信息查询对象",description = "课程查询对象封装")
@Data
public class ChapterVo {

    @ApiModelProperty(value = "章节ID")
    private String id;

    @ApiModelProperty(value = "章节标题")
    private String title;

    /**
     * 表示小节
     */
    private List<VideoVo> children = new ArrayList<>();
}
