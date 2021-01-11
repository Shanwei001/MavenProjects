package com.shanweicode.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shanweicode.constant.MessageConstant;
import com.shanweicode.entity.PageResult;
import com.shanweicode.entity.QueryPageBean;
import com.shanweicode.entity.Result;
import com.shanweicode.pojo.TravelGroup;
import com.shanweicode.service.TravelGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shanweih
 * @create 2020/12/30 上午11:47
 * tool IntelliJ IDEA
 * @Description:
 */
@RequestMapping("/travelgroup")
@RestController
public class TravelGroupController {
    @Reference
    private TravelGroupService travelGroupService;

    @RequestMapping("/add")
    public Result add(@RequestBody TravelGroup travelGroup,Integer[] travelItemIds){
        travelGroupService.add(travelGroup,travelItemIds);
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    // 查询所有
    @RequestMapping("/findAll")
    public Result findAll(){
        // 查询所有的跟团游
        List<TravelGroup> travelGroupList = travelGroupService.findAll();
        if (travelGroupList != null && travelGroupList.size()>0){
            Result result = new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,travelGroupList);
            return result;
        }
        return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);

    }
    // 传递当前页，每页显示的记录数，查询条件
    // 相应PageResult，封装总记录数，结果集
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = travelGroupService.findPage(queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
                );
        return pageResult;
    }

    // 使用自由行id，查询跟团游和自由行的中间表，获取自由行的集合，存放id的值
    @RequestMapping("/findTravelItemIdByTravelgroupId")
    public List<Integer> findTravelItemIdByTravelgroupId(Integer id){
        List<Integer> list = travelGroupService.findTravelItemIdByTravelgroupId(id);
        return list;
    }
    // 使用id查询跟团游，进行表单回显
    @RequestMapping("/findById")
    public Result findById(Integer id){
        TravelGroup travelGroup = travelGroupService.findById(id);
        return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,travelGroup);
    }
    // 编辑跟团游（返回public Result(boolean flag, String message ）
    @RequestMapping("/edit")
    public Result edit(@RequestBody TravelGroup travelGroup,Integer[] travelItemIds){
        travelGroupService.add(travelGroup,travelItemIds);
        return new Result(true,MessageConstant.EDIT_TRAVELGROUP_SUCCESS);
    }
}
