package com.eddie.serviceedu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author eddie
 * @Date 2020-06-07 20:23
 * @Version 1.0
 */
@Data
public class SubjectData {

    @ExcelProperty(index = 0)
    private String oneSubject;

    @ExcelProperty(index = 1)
    private String twoSubject;

}
