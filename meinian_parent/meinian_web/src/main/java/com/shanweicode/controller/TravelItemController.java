package com.shanweicode.controller;

import com.alibaba.dubbo.config.annotation.Reference;

import com.shanweicode.constant.MessageConstant;
import com.shanweicode.entity.PageResult;
import com.shanweicode.entity.QueryPageBean;
import com.shanweicode.entity.Result;
import com.shanweicode.pojo.TravelItem;
import com.shanweicode.service.TravelItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shanweih
 * @create 2020/12/28 下午6:41
 * tool IntelliJ IDEA
 * @Description:
 */
@RestController
@RequestMapping(value = "/travelItem")
public class TravelItemController {
    @Reference
    private TravelItemService travelItemService;

    // 新增
    @RequestMapping("/add")
    public Result add(@RequestBody TravelItem travelItem) {
        try {
            travelItemService.add(travelItem);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_TRAVELITEM_FAIL);
        }
        return new Result(true, MessageConstant.ADD_TRAVELITEM_SUCCESS);
    }
    // 查找
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = travelItemService.findPage(queryPageBean);
        return pageResult;
    }
    // 删除
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            travelItemService.deleteById(id);
            return new Result(true,MessageConstant.DELETE_TRAVELITEM_SUCCESS);
        } catch (RuntimeException e) {
            // 运行时异常，表示自由行和跟团游的关联表中存在数据
            return new Result(false, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_TRAVELITEM_FAIL);
        }
    }
    // 查询id,根据id查处自由行
    @RequestMapping("/findById")
    public Result findById(Integer id){
        TravelItem travelItem = travelItemService.findById(id);
        return new Result(true,MessageConstant.DELETE_TRAVELITEM_SUCCESS,travelItem);
    }
    // 编辑自由行
    @RequestMapping("/edit")
    public Result edit(@RequestBody TravelItem travelItem){
        travelItemService.edit(travelItem);
        return new Result(true,MessageConstant.EDIT_TRAVELITEM_SUCCESS);
    }

    // 查询所有自由行
    @RequestMapping("/findAll")
    public Result findAll(){
        List<TravelItem> list = travelItemService.findAll();
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
    }
}
