package com.shanweicode;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;

/**
 * @author shanweih
 * @create 2020/12/29 下午3:26
 * tool IntelliJ IDEA
 * @Description:
 */
public class QiNiuTest {
    @Test
    public void test1(){
        /* 上传文件 */
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "zi3jIHeTWwAcOadyrbe-tn9W4VXvZmR9xqdlCxBs";
        String secretKey = "x4ZY7z8eD01vrUkTfxlCcDXxzA1LQHesAX1mv3UR";
        String bucket = "sz-0922";
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "/Users/shanweih/qiniu/test.png";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = null;
            try {
                response = uploadManager.put(localFilePath, key, upToken);
            } catch (QiniuException e) {
                e.printStackTrace();
            }
            //解析上传成功的结果
            DefaultPutRet putRet = null;
            try {
                putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            } catch (QiniuException e) {
                e.printStackTrace();
            }
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch(Exception e) {
            System.out.println("e = " + e);
        }
    }
    @Test
    public void delFile(){
        /* 删除上传的文件*/
        //构造一个带指定Zone对象的配置类，zone0表示华东地区（默认）
        Configuration cfg = new Configuration(Zone.zone0());
//...其他参数参考类注释
        String accessKey = "zi3jIHeTWwAcOadyrbe-tn9W4VXvZmR9xqdlCxBs";
        String secretKey = "x4ZY7z8eD01vrUkTfxlCcDXxzA1LQHesAX1mv3UR";
        String bucket = "sz-0922";
        String key = "FpR9YoDi4kM_2kxqzEK4O4Seck1X";

        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }
}
