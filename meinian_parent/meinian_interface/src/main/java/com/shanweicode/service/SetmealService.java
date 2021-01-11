package com.shanweicode.service;

import com.shanweicode.entity.PageResult;
import com.shanweicode.pojo.Setmeal;

import java.util.List;

/**
 * @author shanweih
 * @create 2021/1/4 下午6:31
 * tool IntelliJ IDEA
 * @Description:
 */
public interface SetmealService {
    List<Setmeal> findAll();

    Setmeal findById(int id);

    void add(Setmeal setmeal, Integer[] travelgroupIds);

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);
}
