package com.eddie.serviceedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.eddie.serviceedu.entity.EduSubject;
import com.eddie.serviceedu.entity.excel.SubjectData;
import com.eddie.serviceedu.listener.SubjectExcelListener;
import com.eddie.serviceedu.mapper.EduSubjectMapper;
import com.eddie.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author eddie
 * @since 2020-06-07
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    /**
     * 添加课程分类
     *
     * @param file
     * @param subjectService
     */
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
