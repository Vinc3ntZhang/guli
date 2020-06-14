package com.eddie.serviceedu.service;

import com.eddie.serviceedu.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author eddie
 * @since 2020-06-10
 */
public interface EduVideoService extends IService<EduVideo> {

    /**
     * 根据课程id删除小节
     * @param courseId
     */
    void removeVideoByCourseId(String courseId);
}
