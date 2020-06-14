package com.eddie.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eddie.servicebase.exceptionhandler.GuliException;
import com.eddie.serviceedu.entity.EduChapter;
import com.eddie.serviceedu.entity.EduVideo;
import com.eddie.serviceedu.entity.chapter.ChapterVo;
import com.eddie.serviceedu.entity.chapter.VideoVo;
import com.eddie.serviceedu.mapper.EduChapterMapper;
import com.eddie.serviceedu.service.EduChapterService;
import com.eddie.serviceedu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author eddie
 * @since 2020-06-10
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;

    /**
     * 课程大纲列表，根绝课程id进行查询
     *
     * @param courseId
     * @return list集合
     */
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        // 1、根据课程id查询课程里面所有的章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapperChapter);

        // 2、根据课程id查询课程里面所有的小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        List<EduVideo> eduVideoList = videoService.list(wrapperVideo);

        // 创建list集合，用于最终封装数据
        List<ChapterVo> finalChapterVoList = new ArrayList<>();

        // 3、循环遍历章节list集合进行封装
        // 遍历查询章节list集合
        for (int i = 0; i < eduChapterList.size(); i++) {
            // 每个章节
            EduChapter eduChapter = eduChapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            // eduChapter对象值复制到ChapterVo
            BeanUtils.copyProperties(eduChapter, chapterVo);
            // 把chapterVo放到最终list集合
            finalChapterVoList.add(chapterVo);

            // 创建集合，用于封装章节的小节
            List<VideoVo> videoVoList = new ArrayList<>();

            // 4、循环遍历小节集合，进行封装
            for (int m = 0; m < eduVideoList.size(); m++) {
                // 得到每个小节
                EduVideo eduVideo = eduVideoList.get(m);
                //判断：小节里面chapter_id和章节id是否一样
                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    // 方法小节封装对象
                    videoVoList.add(videoVo);
                }
            }
            // 封装之后小节list集合，放到章节对象里面
            chapterVo.setChildren(videoVoList);
        }
        return finalChapterVoList;
    }

    /**
     * 删除章节的方法
     *
     * @param chapterId
     * @return 布尔值
     */
    @Override
    public boolean deleteChapter(String chapterId) {
        // 根据chapter_id查询小节表，如果查询数据，不进行删除
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("chapter_id", chapterId);
        int count = videoService.count(wrapperVideo);
        // 判断：
        // 查询出小节，不能删除，抛出错误信息
        if (count > 0) {
            throw new GuliException(20001, "该章节有小节，不能删除");
        } else { // 不查询数据，直接删除
            // 删除章节
            int result = baseMapper.deleteById(chapterId);
            // 成功
            return result>0;
        }
    }

    /**
     * 根据课程id删除章节
     * @param courseId
     */
    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        baseMapper.delete(queryWrapper);
    }
}
