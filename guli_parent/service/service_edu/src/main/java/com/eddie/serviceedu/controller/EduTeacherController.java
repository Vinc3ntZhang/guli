package com.eddie.serviceedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eddie.commonutils.R;
import com.eddie.serviceedu.entity.EduTeacher;
import com.eddie.serviceedu.entity.vo.TeacherQuery;
import com.eddie.serviceedu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author eddie
 * @since 2020-06-05
 */
@Api(tags = "讲师管理", description = "讲师管理控制器")
@RestController
@RequestMapping("/serviceedu/teacher")
public class EduTeacherController {
    //访问地址：http://localhost:8001/serviceedu/teacher/findAll
    // 把service注入
    @Autowired
    private EduTeacherService teacherService;

    /**
     * 查询讲师表所有数据
     */
    //rest风格
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public List<EduTeacher> findAllTeacher() {
        // 调用service的方法实现查询所有的操作
        List<EduTeacher> list = teacherService.list(null);
        return list;
    }

    /**
     * 讲师的逻辑删除
     */
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("removeTeacherById/{id}")
    public boolean logicDeleteTeacher(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        boolean result = teacherService.removeById(id);
        return result;
    }

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAllTeacher")
    public R list() {
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items", list);
    }

    /**
     * 分页查询方法
     */
    @ApiOperation(value = "讲师分页列表(返回json对象)")
    @GetMapping("pageTeacher/{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页数", required = true) @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable Long limit
    ) {
        // 创建page对象
        Page<EduTeacher> eduTeacherPage = new Page(page, limit);
        // 调用方法实现分页
        // 调用方法的时候，底层封装，把分页所有数据封装到eduTeacherPage对象里面
        teacherService.page(eduTeacherPage, null);
        List<EduTeacher> records = eduTeacherPage.getRecords();
        long total = eduTeacherPage.getTotal();
//        Map map = new HashMap();
//        map.put("total", total);
//        map.put("rows", records);
//        return R.ok().data(map);
        return R.ok().data("total", total).data("rows", records);
    }

    /**
     * 多条件讲师分页查询
     */
    @ApiOperation(value = "多条件讲师分页查询")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(
            @ApiParam(name = "current", value = "当前页数", required = true) @PathVariable long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable long limit,
            @ApiParam(name = "teacherQuery", value = "前端传入数据对象") @RequestBody(required = false) TeacherQuery teacherQuery
    ) {
        // 创建page对象
        Page<EduTeacher> teacherPage = new Page<>(current, limit);
        // 构建条件
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        // 多条件组合查寻
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        // 判断条件值是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            queryWrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_modified", end);
        }
        // 调用方法实现条件查询分页
        teacherService.page(teacherPage, queryWrapper);

        Long total = teacherPage.getTotal();//总记录数
        List<EduTeacher> records = teacherPage.getRecords();//数据list集合
        Map map = new HashMap<>();
        map.put("total", total);
        map.put("rows", records);
        return R.ok().data(map);
    }

    /**
     * 添加讲师接口的方法
     */
    @ApiOperation(value = "添加讲师接口")
    @PostMapping("addTeacher")
    public R addTeacher(@ApiParam(value = "eduTeacher", name = "添加讲师对象") @RequestBody EduTeacher eduTeacher) {
        boolean save = teacherService.save(eduTeacher);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 根据讲师ID查询
     *
     * @param id
     * @return 讲师对象
     */
    @ApiOperation(value = "根据讲师ID查询")
    @GetMapping("getTeacherById/{id}")
    public R getTeacherById(@ApiParam(value = "id", name = "讲师id") @PathVariable String id) {
        EduTeacher eduTeacher = teacherService.getById(id);
        return R.ok().data("teacher", eduTeacher);
    }

    /**
     * 根据id修改讲师
     *
     * @param eduTeacher
     * @return 布尔值
     */
    @ApiOperation(value = "根据id修改讲师")
    @PostMapping("updateTeacher")
    public R updateTeacher(@ApiParam(value = "eduTeacher", name = "修改讲师对象") @RequestBody EduTeacher eduTeacher) {
        boolean flag = teacherService.updateById(eduTeacher);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}

