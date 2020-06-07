package com.eddie.serviceedu.controller;


import com.eddie.commonutils.R;
import com.eddie.serviceedu.service.EduSubjectService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author eddie
 * @since 2020-06-07
 */
@RestController
@RequestMapping("/serviceedu/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    /**
     * 添加课程分类
     * 获取上传过来的文件，将文件内容读取出来
     * @param file
     * @return
     */
    @ApiOperation(value = "添加课程分类")
    @PostMapping("addSubject")
    public R addSubject(@ApiParam(name = "上传Excel文件") MultipartFile file){
        // 上传过来excel文件
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }

}

