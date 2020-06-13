package com.eddie.serviceedu.service;

import com.eddie.serviceedu.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eddie.serviceedu.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author eddie
 * @since 2020-06-10
 */
public interface EduChapterService extends IService<EduChapter> {

    /**
     * 课程大纲列表，根绝课程id进行查询
     * @param courseId
     * @return list集合
     */
    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    /**
     * 删除章节的方法
     * @param chapterId
     * @return 布尔值
     */
    boolean deleteChapter(String chapterId);
}
