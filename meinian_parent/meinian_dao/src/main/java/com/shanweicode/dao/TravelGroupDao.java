package com.shanweicode.dao;

import com.github.pagehelper.Page;
import com.shanweicode.pojo.TravelGroup;

import java.util.List;
import java.util.Map;

/**
 * @author shanweih
 * @create 2020/12/30 下午12:00
 * tool IntelliJ IDEA
 * @Description:
 */
public interface TravelGroupDao {

    List<TravelGroup> findAll();

    Page<TravelGroup> findPage(String queryString);

    TravelGroup findById(Integer id);

    List<Integer> findTravelItemIdByTravelgroupId(Integer id);

    void edit(TravelGroup travelGroup);

    void deleteTravelGroupAndTravelItemByTravelGroupId(Integer id);

    List<TravelGroup> findTravelGroupListById(Integer id);

    void add(TravelGroup travelGroup);

    void setTravelGroupAndTravelItem(Map map);
}
