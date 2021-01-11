package com.shanweicode.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shanweicode.constant.RedisConstant;
import com.shanweicode.dao.SetmealDao;
import com.shanweicode.entity.PageResult;
import com.shanweicode.pojo.Setmeal;
import com.shanweicode.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shanweih
 * @create 2021/1/4 下午6:32
 * tool IntelliJ IDEA
 * @Description:
 */
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealDao setmealDao;

    @Override
    public List<Setmeal> findAll() {
        return setmealDao.findAll();
    }

    @Override
    public Setmeal findById(int id) {
        return setmealDao.findById(id);
    }

    // 绑定套餐和跟团游的多对多关系
    private void setSetmealAndTravelGroup(Integer id, Integer[] travelgroupIds) {
        for (Integer checkgroupId : travelgroupIds) {
            Map<String, Integer> map = new HashMap<>();
            map.put("travelgroup_id", checkgroupId);
            map.put("setmeal_id", id);
            setmealDao.setSetmealAndTravelGroup(map);
        }
    }
    @Autowired
    private JedisPool jedisPool;
    // 新增套餐
    public void add(Setmeal setmeal,Integer[] travelgroupIds){
        setmealDao.add(setmeal);
        if (travelgroupIds != null && travelgroupIds.length>0){
            // 绑定套餐和跟团游的多对多关系
            setSetmealAndTravelGroup(setmeal.getId(),travelgroupIds);
        }
        // 将图片名称保存到redis
        savePic2Redis(setmeal.getImg());
    }

    // 将图片名称保存到redis
    private void savePic2Redis(String img) {
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,img);
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> page = setmealDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }
}
