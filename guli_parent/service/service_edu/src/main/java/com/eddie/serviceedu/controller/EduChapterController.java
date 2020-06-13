package com.eddie.serviceedu.controller;


import com.eddie.commonutils.R;
import com.eddie.serviceedu.entity.EduChapter;
import com.eddie.serviceedu.entity.chapter.ChapterVo;
import com.eddie.serviceedu.service.EduChapterService;
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
@Api(tags = "课程章节")
@RequestMapping("/serviceedu/chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;

    /**
     * 课程大纲列表，根绝课程id进行查询
     *
     * @return
     */
    @ApiOperation(value = "课程大纲列表，根绝课程id进行查询")
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(
            @ApiParam(name = "课程id", value = "courseId")
            @PathVariable String courseId
    ) {
        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo", list);
    }

    /**
     * 添加章节
     *
     * @param eduChapter
     * @return json成功消息
     */
    @ApiOperation(value = "添加章节")
    @PostMapping("addChapter")
    public R addChapter(@ApiParam(name = "eduChapter对象") @RequestBody EduChapter eduChapter) {
        chapterService.save(eduChapter);
        return R.ok();
    }

    /**
     * 根据章节id查询
     * @param chapterId
     * @return eduChapter对象
     */
    @ApiOperation(value = "根据章节id查询")
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@ApiParam(name = "章节id") @PathVariable String chapterId) {
        EduChapter eduChapter = chapterService.getById(chapterId);
        return R.ok().data("chapter", eduChapter);
    }

    /**
     * 修改章节
     * @param eduChapter
     * @return json成功消息
     */
    @ApiOperation(value = "修改章节")
    @PostMapping("updateChapter")
    public R updateChapter(@ApiParam(name = "eduChapter对象") @RequestBody EduChapter eduChapter) {
        chapterService.updateById(eduChapter);
        return R.ok();
    }

    /**
     * 删除章节的方法
     * @param chapterId
     * @return
     */
    @ApiOperation(value = "删除章节")
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@ApiParam(name = "章节id")@PathVariable String chapterId){
        boolean flag = chapterService.deleteChapter(chapterId);
        return R.ok();
    }

}

