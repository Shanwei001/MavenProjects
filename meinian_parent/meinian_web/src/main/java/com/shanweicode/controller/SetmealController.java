package com.shanweicode.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shanweicode.constant.MessageConstant;
import com.shanweicode.constant.RedisConstant;
import com.shanweicode.entity.PageResult;
import com.shanweicode.entity.QueryPageBean;
import com.shanweicode.entity.Result;
import com.shanweicode.pojo.Setmeal;
import com.shanweicode.service.SetmealService;
import com.shanweicode.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

/**
 * @author shanweih
 * @create 2020/12/30 下午2:30
 * tool IntelliJ IDEA
 * @Description:
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;

    // imgFile:需要跟页面el-upload里面的name保持一致
    @Autowired
    private JedisPool jedisPool;
    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = setmealService.findPage(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
        return pageResult;
    }
    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile) {
        try {
            // 获取原始文件名
            String originalFilename = imgFile.getOriginalFilename();
            // 找到.最后出现的位置
            int lastIndexOf = originalFilename.lastIndexOf(".");
            // 获取文件后缀
            String suffix = originalFilename.substring(lastIndexOf);
            // 使用uuid随机产生文件名，防止同名文件覆盖
            String fileName = UUID.randomUUID().toString() + suffix;
            QiniuUtils.upload2Qiniu(imgFile.getBytes(), fileName);
            // 图片上传成功
            Result result = new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, fileName);
            // 将上传的图片名称存入redis，基于redis的set集合存储
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            // 图片上传失败
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    // 新增
    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal,Integer[] travelgroupIds){
        try {
            setmealService.add(setmeal,travelgroupIds);
        } catch (Exception e) {
            // 新增套餐失败
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
        // 新增套餐成功
        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
    }
}
