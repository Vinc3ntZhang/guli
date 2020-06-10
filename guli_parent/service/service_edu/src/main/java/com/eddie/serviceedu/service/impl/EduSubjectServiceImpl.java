package com.eddie.serviceedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eddie.serviceedu.entity.EduSubject;
import com.eddie.serviceedu.entity.excel.SubjectData;
import com.eddie.serviceedu.entity.subject.OneSubject;
import com.eddie.serviceedu.entity.subject.TwoSubject;
import com.eddie.serviceedu.listener.SubjectExcelListener;
import com.eddie.serviceedu.mapper.EduSubjectMapper;
import com.eddie.serviceedu.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 课程分类列表 (树形)
     *
     * @return finalAllSubjectList课程分类列表
     */
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        // 1、查询所有一级分类 parent_id=0
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", "0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);

        // 2、查询所有二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id", "0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        // 创建list集合，用于存储最终封装数据
        List<OneSubject> finalAllSubjectList = new ArrayList<>();

        // 3、封装一级分类
        // 查询出来所有的一级分类list集合遍历，得到每一个一级分类对象，获取每个一级分类对象值
        // 封装到要求list集合里面List<OneSubject> finalAllSubjectList
        for (int i = 0; i < oneSubjectList.size(); i++) {
            // 得到oneSubjectList每个对象eduSubject对象
            EduSubject oSubject = oneSubjectList.get(i);
            // 把eduSubject里面值获取出来，放OneSubject对象里面
            OneSubject oneSubject = new OneSubject();
            //oneSubject.setId(eduSubject.getId());
            //eduSubject.setTitle(eduSubject.getTitle());

            BeanUtils.copyProperties(oSubject, oneSubject);
            //多个OneSubject放到finalAllSubjectList里面
            finalAllSubjectList.add(oneSubject);

            // 在一级分类循环中遍历查询所有的二级分类
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            // 遍历二级分类list集合
            // 4、分装二级分类
            for (int m = 0; m < twoSubjectList.size(); m++) {
                // 获取每个二级分类
                EduSubject tSubject = twoSubjectList.get(m);
                //判断二级分类parent_id和一级分类id是否一样
                if (tSubject.getParentId().equals(oneSubject.getId())) {
                    // 把tSubject值复制到TwoSubject里面，放到twoFinalSubjectList里面
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject, twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }


            }

            // 把一级下面所有的二级分类放回一级分类里面
            oneSubject.setChildren(twoFinalSubjectList);

        }

        return finalAllSubjectList;
    }

}
