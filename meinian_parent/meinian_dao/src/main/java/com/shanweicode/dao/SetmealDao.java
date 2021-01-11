package com.shanweicode.dao;

import com.github.pagehelper.Page;
import com.shanweicode.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * @author shanweih
 * @create 2021/1/4 下午6:34
 * tool IntelliJ IDEA
 * @Description:
 */
public interface SetmealDao {

    List<Setmeal> findAll();

    Setmeal findById(int id);

    void setSetmealAndTravelGroup(Map<String, Integer> map);

    void add(Setmeal setmeal);

    Page<Setmeal> findPage(String queryString);
}
