package com.shanweicode.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.shanweicode.dao.OrderSettingDao;
import com.shanweicode.pojo.OrderSetting;
import com.shanweicode.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shanweih
 * @create 2020/12/31 下午4:03
 * tool IntelliJ IDEA
 * @Description:
 */
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService{
    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    public void add(List<OrderSetting> datas){
        // 1.遍历List<OrderSetting>
        for (OrderSetting data : datas) {
            // 判断当前的日期之前是否已经被设置过预约日期，使用当前时间作为条件查询数量
            long count = orderSettingDao.findCountByOrderDate(data.getOrderDate());
            // 如果设置过预约日期，更新number数量
            if (count>0){
                orderSettingDao.editNumberByOrderDate(data);
            }else {
                // 如果没有设置过预约日期，执行保存
                orderSettingDao.add(data);
            }
        }
    }

    /**
     * 传递参数
     * date（格式 XXXX - XX）
     * 构建数据List<Map>
     *map.put(“date”,1);
     *           map.put(“number”,120);
     *           map.put(“reservations”,10);
     *         查询方案：SELECT * FROM t_ordersetting WHERE orderDate LIKE '2019-08-%'
     *         查询方案：SELECT * FROM t_ordersetting WHERE orderDate BETWEEN '2019-9-1' AND '2019-9-31'
     */
    // 根据日期查询预约设置数据
    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        // 1.组织查询Map，dateBegin表示月份开始时间，dateEnd月份结束时间
        String dateBegin = date + "-01";
        String dateEnd = date + "-31";
        Map<String,Object> map = new HashMap<>();
        map.put("dateBegin",dateBegin);
        map.put("dateEnd",dateEnd);
        // 2.查询当前月份的预约设置
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);
        List<Map> data = new ArrayList<>();
        // 3.将LIst<OrderSetting>,组织成List<Map>
        for (OrderSetting orderSetting : list) {
            Map orderSettingMap = new HashMap();
            orderSettingMap.put("date",orderSetting.getOrderDate().getDate()); // 获得日期（几号）
            orderSettingMap.put("number",orderSetting.getNumber()); // 可预约人数
            orderSettingMap.put("reservations",orderSetting.getReservations()); // 一预约人数
            data.add(orderSettingMap);
        }
        return data;
    }

    // 根据日期修改可预约人数
    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if (count>0){
            // 当前日期已经进行了预约设置。需要进行修改操作
            orderSettingDao.editNumberByDate(orderSetting);
        }else {
            // 当前日期没有进行预约设置，进行添加操作
            orderSettingDao.add(orderSetting);
        }

    }
}
