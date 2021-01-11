package com.shanweicode.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shanweicode.constant.MessageConstant;
import com.shanweicode.constant.RedisMessageConstant;
import com.shanweicode.entity.Result;
import com.shanweicode.pojo.Member;
import com.shanweicode.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @author shanweih
 * @create 2021/1/5 下午2:59
 * tool IntelliJ IDEA
 * @Description:
 * 用户登陆
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Reference
    private MemberService memberService;

    @Autowired
    private JedisPool jedisPool;

    // 使用手机号和验证码登陆
    @RequestMapping("/check")
    public Result check(HttpServletResponse response, @RequestBody Map map) {
        String telephone = (String) map.get("telephone");
        String validateCode = (String) map.get("validateCode");
        // 从redis中获取缓存的验证码，判断验证码输入是否正确
        String codeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_LOGIN);
        if (codeInRedis == null|| !codeInRedis.equals(validateCode)){
            // 验证码输入错误
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }else {
            // 验证码输入正确
            // 判断当前用户是否为会员
            Member member = memberService.findByTelephone(telephone);
            if (member == null){
                // 当前用户不是会员,自动完成注册
                member = new Member();
                member.setPhoneNumber(telephone);
                member.setRegTime(new Date());
                memberService.add(member);
            }
            // 登陆成功
            // 写入cookie，跟踪用户
            Cookie cookie = new Cookie("login_member_telephone",telephone);
            cookie.setPath("/"); // 路径
            cookie.setMaxAge(60*60*30); // 有效期30天(单位秒)
            response.addCookie(cookie);
            return new Result(true,MessageConstant.LOGIN_SUCCESS);

        }
    }
}
