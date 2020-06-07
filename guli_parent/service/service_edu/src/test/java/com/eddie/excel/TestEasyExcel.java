package com.eddie.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author eddie
 * @Date 2020-06-07 19:27
 * @Version 1.0
 */
public class TestEasyExcel {
    public static void main(String[] args) {
        // 实现excel写的操作
        // 1、设置写入文件夹地址和excel文件名称
//        String fileName = "/Users/eddie/desktop/test.xlsx";
        // 2、调用easyexcel里面的方法实现写操作
        // write方法中两个参数：第一个参数是文件的路径名称，第二个参数实体类class
//        EasyExcel.write(fileName,DemoData.class).sheet("学生列表").doWrite(getData());

        // 实现excel读的操作
        String fileName = "/Users/eddie/desktop/test.xlsx";
        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();
    }

    // 创建方法返回list集合
    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSNo(i);
            data.setSName("mike"+i);
            list.add(data);
        }
        return list;
    }

}
