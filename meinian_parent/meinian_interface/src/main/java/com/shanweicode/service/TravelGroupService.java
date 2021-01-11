package com.shanweicode.service;

import com.shanweicode.entity.PageResult;
import com.shanweicode.pojo.TravelGroup;

import java.util.List;

/**
 * @author shanweih
 * @create 2020/12/30 上午11:55
 * tool IntelliJ IDEA
 * @Description:
 */
public interface TravelGroupService {
    List<TravelGroup> findAll();

    void edit(Integer[] travelItemIds, TravelGroup travelGroup);

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    List<Integer> findTravelItemIdByTravelgroupId(Integer id);

    TravelGroup findById(Integer id);

    void add(TravelGroup travelGroup,Integer[] travelItemIds);
}
