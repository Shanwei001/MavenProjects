package com.shanweicode.service;

import com.shanweicode.entity.PageResult;
import com.shanweicode.entity.QueryPageBean;
import com.shanweicode.pojo.TravelItem;

import java.util.List;

public interface TravelItemService {

    PageResult findPage(QueryPageBean queryPageBean);


    void add(TravelItem travelItem);

    void deleteById(Integer id);

    TravelItem findById(Integer id);

    void edit(TravelItem travelItem);

    List<TravelItem> findAll();

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);
}