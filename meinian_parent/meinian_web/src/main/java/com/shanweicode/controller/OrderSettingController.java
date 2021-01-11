package com.shanweicode.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shanweicode.constant.MessageConstant;
import com.shanweicode.utils.POIUtils;
import com.shanweicode.entity.Result;
import com.shanweicode.pojo.OrderSetting;
import com.shanweicode.service.OrderSettingService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author shanweih
 * @create 2020/12/31 下午2:31
 * tool IntelliJ IDEA
 * @Description:
 */
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    // 1.使用POI解析文件，得到List<String[]> list
    // 2.把List<String[]> list 转成list<OrderSetting>list
    // 3.调用业务，进行保存

    @Reference
    private OrderSettingService ordersettingService;

    @RequestMapping("/upload")
    public Result upload(MultipartFile excelFile){
        try {
            // 使用poi工具类解析excel文件，读取里面的内容
            List<String[]> lists = POIUtils.readExcel(excelFile);
            // 把list<String[]>数据转换成List<OrderSetting>数据
            List<OrderSetting> orderSettings = new ArrayList<>();
            // 迭代里面的每一行数据，进行封装到集合里面
            for (String[] str : lists) {
                // 获取到一行里面，每个表格数据，进行封装
                OrderSetting orderSetting = new OrderSetting(new Date(str[0]),Integer.parseInt(str[1]));
                orderSettings.add(orderSetting);
            }
            // 调用业务进行保存
            ordersettingService.add(orderSettings);
            return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(true,MessageConstant.ORDERSETTING_FAIL);
        }
    }
    /**
     * 根据日期查询预约设置数据（获取指定日期所在月份的预约设置数据）
     *
     */
    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date){
        // 参数格式为xxxx-xx
        try {
            List<Map> list = ordersettingService.getOrderSettingByMonth(date);
            // 获取预约设置数据成功
            return  new Result(true,MessageConstant.ORDERSETTING_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            // 获取预约设置数据失败
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }

    }
    /**
     * 根据指定日期修改可预约人数
     *
     */
    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
        try {
            ordersettingService.editNumberByDate(orderSetting);
            // 预约设置成功
            return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            // 预约设置失败
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }
    }

}
