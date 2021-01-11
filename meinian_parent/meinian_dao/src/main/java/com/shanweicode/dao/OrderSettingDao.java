package com.shanweicode.dao;

import com.shanweicode.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author shanweih
 * @create 2021/1/2 下午5:42
 * tool IntelliJ IDEA
 * @Description:
 */
public interface OrderSettingDao {
    long findCountByOrderDate(Date orderDate);

    void add(OrderSetting orderSetting);

    List<OrderSetting> getOrderSettingByMonth(Map<String, Object> map);

    void editNumberByOrderDate(OrderSetting orderSetting);

    void editNumberByDate(OrderSetting orderSetting);
}
