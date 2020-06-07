package com.eddie.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author eddie
 * @Date 2020-06-07 19:24
 * @Version 1.0
 */
@Data
public class DemoData {
    // 设置excel表头
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer sNo;
    @ExcelProperty(value = "学生姓名",index = 1)
    private String sName;
}
