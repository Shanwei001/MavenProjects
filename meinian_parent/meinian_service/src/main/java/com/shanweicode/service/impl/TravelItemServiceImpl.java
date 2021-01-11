package com.shanweicode.service.impl;

import com.alibaba.dubbo.config.annotation.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shanweicode.dao.TravelItemDao;
import com.shanweicode.entity.PageResult;
import com.shanweicode.entity.QueryPageBean;
import com.shanweicode.pojo.TravelItem;
import com.shanweicode.service.TravelItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author shanweih
 * @create 2020/12/28 下午7:03
 * tool IntelliJ IDEA
 * @Description:    TravelItemService的实现类
 *  // DUBBO （整合声明式事务处理
 *  1：配置applicationContext-tx.xml对类的代理，
 *  2：@Service（interfaceClass = TravelItemService.class））
 */
@Service(interfaceClass = TravelItemService.class)
@Transactional
public class TravelItemServiceImpl implements TravelItemService{
    @Autowired
    private TravelItemDao travelItemDao;

    @Override
    public void add(TravelItem travelItem){
        travelItemDao.add(travelItem);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        // 实现分页功能
        // 1.初始化分页操作
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        // 2.使用sql语句进行查询
        Page<TravelItem> page = travelItemDao.findPage(queryPageBean.getQueryString());
        // 3.封装
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void deleteById(Integer id) {
        // 在删除自由行之前，先判断自由行的id，在中间表中是否存在数据
        long count = travelItemDao.findCountByTravelItemId(id);
        // 中间表如果有数据，不要往后面执行，直接跑出异常
        // 如果非要删除也可以，delete from t-travelgroup_travelitem where travelitem_id=1
        if (count>0){
            // 说明有中间表
            throw new RuntimeException("不允许删除该数据");
        }
        // 使用自由行id进行删除
        travelItemDao.deleteById(id);
    }

    @Override
    public TravelItem findById(Integer id) {
        return travelItemDao.findById(id);
    }

    @Override
    public void edit(TravelItem travelItem) {
        travelItemDao.edit(travelItem);
    }

    @Override
    public List<TravelItem> findAll() {
        return travelItemDao.findAll();
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        // 不使用分页插件PageHelper
        // 至少写2条sql语句完成查询
        // 第1条，select count(*) from t_travelitem，查询的结果封装到PageResult中的total
        // 第2条，select * from t_travelitem where NAME = '001' OR CODE = '001' limit ?,?  （0,10）（10,10）(（currentPage-1)*pageSize,pageSize）
        // 使用分页插件PageHelper（简化上面的操作）
        // 1：初始化分页操作
        PageHelper.startPage(currentPage,pageSize);
        // 2: 使用sql语句进行查询(不必使用mysql的limit了)
        Page<TravelItem> page = travelItemDao.findPage(queryString);
        // 3. 封装
        return new PageResult(page.getTotal(),page.getResult());
    }
}
