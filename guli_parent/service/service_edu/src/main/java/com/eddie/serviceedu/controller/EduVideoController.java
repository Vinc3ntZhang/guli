package com.eddie.serviceedu.controller;


import com.eddie.commonutils.R;
import com.eddie.serviceedu.entity.EduVideo;
import com.eddie.serviceedu.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author eddie
 * @since 2020-06-10
 */
@RestController
@Api(tags = "课程视频")
@RequestMapping("/serviceedu/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    /**
     * 添加小节
     * @param eduVideo
     * @return json成功数据
     */
    @ApiOperation(value = "添加小节")
    @PostMapping("addVideo")
    public R addVideo(@ApiParam(name = "小节对象") @RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return R.ok();
    }

    /**
     * 删除小节
     * @param id
     * @return json成功数据
     * TODO 后面这个方法需要完善：删除小节的时候，同时把里面视频删除
     */
    @ApiOperation(value = "删除小节")
    @DeleteMapping("{id}")
    public R deleteVideo(@ApiParam(name = "小节id") @PathVariable String id) {
        videoService.removeById(id);
        return R.ok();
    }

    /**
     * 修改小节
     * @param eduVideo
     * @return json成功数据
     */
    @ApiOperation(value = "修改小节")
    @PostMapping("updateVideo")
    public R updateVideo(@ApiParam(name = "小节对象")@RequestBody EduVideo eduVideo){
        videoService.updateById(eduVideo);
        return R.ok();
    }

    @ApiOperation(value = "显示小节信息")
    @GetMapping("getVideoInfo/{videoId}")
    public R getVideoInfo(@ApiParam(name = "小节id")@PathVariable String videoId){
        EduVideo eduVideo = videoService.getById(videoId);
        return R.ok().data("video",eduVideo);
    }

}

