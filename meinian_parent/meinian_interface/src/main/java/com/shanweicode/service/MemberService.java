package com.shanweicode.service;

import com.shanweicode.pojo.Member;

/**
 * @author shanweih
 * @create 2021/1/5 下午3:23
 * tool IntelliJ IDEA
 * @Description:
 */
public interface MemberService {
    Member findByTelephone(String telephone);

    void add(Member member);
}
