package com.shanweicode.dao;


import com.github.pagehelper.Page;
import com.shanweicode.pojo.TravelItem;

import java.util.List;

/**
 * @author shanweih
 * @create 2020/12/28 下午7:09
 * tool IntelliJ IDEA
 * @Description:
 */
public interface TravelItemDao {
    void add(TravelItem travelItem);

    Page<TravelItem> findPage(String queryString);

    long findCountByTravelItemId(Integer id);

    void deleteById(Integer id);

    TravelItem findById(Integer id);

    void edit(TravelItem travelItem);

    List<TravelItem> findTravelItemListById(Integer id);

    List<TravelItem> findAll();
}
