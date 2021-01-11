package com.shanweicode.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shanweicode.dao.TravelGroupDao;
import com.shanweicode.entity.PageResult;
import com.shanweicode.pojo.TravelGroup;
import com.shanweicode.service.TravelGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shanweih
 * @create 2020/12/30 上午11:57
 * tool IntelliJ IDEA
 * @Description:
 */
@Service(interfaceClass = TravelGroupService.class)
@Transactional
public class TravelGroupServiceImpl implements TravelGroupService {
    @Autowired
    private TravelGroupDao travelGroupDao;

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        // 使用分页插件PageHelper，设置当前页面，每页最多显示的记录数
        PageHelper.startPage(currentPage, pageSize);
        // 响应分页插件的page对象
        Page<TravelGroup> page = travelGroupDao.findPage(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public List<TravelGroup> findAll() {
        return travelGroupDao.findAll();
    }

    @Override
    public void edit(Integer[] travelItemIds, TravelGroup travelGroup) {
        // 修改跟团游的基本信息
        travelGroupDao.edit(travelGroup);
        /**
         * 2: 修改跟团游和自由行的中间表（先删除，再创建）
         * 之前的数据删除
         * 在新增页面选中的数据
         */
        // 删除之前中间表的数据
        travelGroupDao.deleteTravelGroupAndTravelItemByTravelGroupId(travelGroup.getId());
        // 再新增页面选中的数据
        setTravelGroupAndTravelItem(travelGroup.getId(), travelItemIds);
    }

    @Override
    public List<Integer> findTravelItemIdByTravelgroupId(Integer id) {
        return travelGroupDao.findTravelItemIdByTravelgroupId(id);
    }

    @Override
    public TravelGroup findById(Integer id) {
        return travelGroupDao.findById(id);
    }

    @Override
    public void add(TravelGroup travelGroup, Integer[] travelItemIds) {
        // 1 新增跟团游，向t_travelgroup中添加数据，新增后返回新增的id
        travelGroupDao.add(travelGroup);
        // 2 新增跟团游和自由行中间表t_travelgroup_travelitem新增数据(新增几条，由travelItemIds决定)
        setTravelGroupAndTravelItem(travelGroup.getId(), travelItemIds);
    }

    private void setTravelGroupAndTravelItem(Integer id, Integer[] travelItemIds) {
        if (travelItemIds != null && travelItemIds.length > 0) {
            for (Integer travelItemId : travelItemIds) {
                Map map = new HashMap<>();
                map.put("travelGroupId", id);
                map.put("travelItems", travelItemId);
                travelGroupDao.setTravelGroupAndTravelItem(map);
            }
        }
    }
}
