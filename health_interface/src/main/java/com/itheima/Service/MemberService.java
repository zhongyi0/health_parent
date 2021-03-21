package com.itheima.Service;

import com.itheima.pojo.Member;

import java.util.List;

public interface MemberService {
    Member findByTelephone(String telephone);

    void add(Member member);

    List<Integer> findByMonth(List<String> list);
}
