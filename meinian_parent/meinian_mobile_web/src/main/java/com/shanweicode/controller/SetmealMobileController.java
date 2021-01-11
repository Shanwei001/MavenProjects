package com.shanweicode.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shanweicode.constant.MessageConstant;
import com.shanweicode.entity.Result;
import com.shanweicode.pojo.Setmeal;
import com.shanweicode.service.SetmealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shanweih
 * @create 2021/1/4 下午3:30
 * tool IntelliJ IDEA
 * @Description:
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealMobileController {
    @Reference
    private SetmealService setmealService;

    // 获取所有套餐信息
    @RequestMapping("/getSetmeal")
    public Result getSetmeal() {
        try {
            List<Setmeal> list = setmealService.findAll();
            return new Result(true, MessageConstant.GET_SETMEAL_LIST_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_SETMEAL_LIST_FAIL);
        }
    }
    // 根据id查询套餐信息
    @RequestMapping("/findById")
    public Result findById(int id){
        try {
            Setmeal setmeal = setmealService.findById(id);
            return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }
}
