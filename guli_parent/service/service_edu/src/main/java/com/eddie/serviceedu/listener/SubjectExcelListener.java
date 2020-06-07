package com.eddie.serviceedu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eddie.servicebase.exceptionhandler.GuliException;
import com.eddie.serviceedu.entity.EduSubject;
import com.eddie.serviceedu.entity.excel.SubjectData;
import com.eddie.serviceedu.service.EduSubjectService;

/**
 * @Author eddie
 * @Date 2020-06-07 20:28
 * @Version 1.0
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    // 因为SubjectExcelListener不能交给spring进行管理，需要自己new,不能注入其他对象
    // 不能实现数据库操作

    public EduSubjectService subjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    /**
     * 读取excel内容，一行一行进行读取
     *
     * @param subjectData
     * @param analysisContext
     */
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new GuliException(20001, "文件数据为空");
        }
        // 一行一行读取，每次读取有两个值，第一个值一级分类，第二个值二级分类
        EduSubject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubject());
        // 没有相同一级分类，进行添加
        if (existOneSubject == null) {
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            //一级分类名称
            existOneSubject.setTitle(subjectData.getOneSubject());
            subjectService.save(existOneSubject);
        }
        // 获取一级分类的id值
        String pid = existOneSubject.getId();
        // 没有相同二级分类，进行添加
        EduSubject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubject(), pid);
        if (existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubject());
            subjectService.save(existTwoSubject);
        }

    }

    /**
     * 判断一级分类不能重复添加
     *
     * @param name
     * @return oneSubject
     */
    private EduSubject existOneSubject(EduSubjectService subjectService, String name) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", name);
        queryWrapper.eq("parent_id", "0");
        EduSubject oneSubject = subjectService.getOne(queryWrapper);
        return oneSubject;
    }

    /**
     * 判断二级分类不能重复添加
     *
     * @param subjectService
     * @param name
     * @param pid
     * @return twoSubject
     */
    private EduSubject existTwoSubject(EduSubjectService subjectService, String name, String pid) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", name);
        queryWrapper.eq("parent_id", pid);
        EduSubject twoSubject = subjectService.getOne(queryWrapper);
        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
