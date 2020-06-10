package com.eddie.serviceedu.service;

import com.eddie.serviceedu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eddie.serviceedu.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author eddie
 * @since 2020-06-07
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * 添加课程分类
     * @param file
     * @param subjectService
     */
    void saveSubject(MultipartFile file, EduSubjectService subjectService);

    /**
     * 课程分类列表 (树形)
     * @return
     */
    List<OneSubject> getAllOneTwoSubject();
}
