package com.shanweicode.service;

import com.shanweicode.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @author shanweih
 * @create 2020/12/31 下午4:01
 * tool IntelliJ IDEA
 * @Description:
 */
public interface OrderSettingService {
    void add(List<OrderSetting> orderSettings);

    List<Map> getOrderSettingByMonth(String date); // 参数格式为XXXX-XX

    void editNumberByDate(OrderSetting orderSetting);
}
