package com.shanweicode.dao;

import com.shanweicode.pojo.Member;

/**
 * @author shanweih
 * @create 2021/1/5 下午6:40
 * tool IntelliJ IDEA
 * @Description:
 */
public interface MemberDao {
    Member findByTelephone(String telephone);

    void add(Member member);
}
