package com.eddie.serviceedu.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author eddie
 * @Date 2020-06-10 12:14
 * @Version 1.0
 */
@Data
public class OneSubject {

    private String id;

    private String title;

    /**
     * 一个一级分类有多二级分类
     */
    private List<TwoSubject> children = new ArrayList<>();
}
