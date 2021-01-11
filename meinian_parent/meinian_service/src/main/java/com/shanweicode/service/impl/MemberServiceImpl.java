package com.shanweicode.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.shanweicode.dao.MemberDao;
import com.shanweicode.pojo.Member;
import com.shanweicode.service.MemberService;
import com.shanweicode.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
/**
 * @author shanweih
 * @create 2021/1/5 下午6:35
 * tool IntelliJ IDEA
 * @Description:
 * 会员服务
 */
@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;
    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    @Override
    public void add(Member member) {
        if (member.getPassword() != null){
            member.setPassword(MD5Utils.md5(member.getPassword()));
        }
        memberDao.add(member);
    }
}
