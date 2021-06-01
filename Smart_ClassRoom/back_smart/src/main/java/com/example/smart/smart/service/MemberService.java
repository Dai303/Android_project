package com.example.smart.smart.service;

import com.example.smart.smart.dao.MemberDao;
import com.example.smart.smart.entity.Manager;
import com.example.smart.smart.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    ManagerService managerService;
    @Autowired
    MemberDao memberDao;

    public List<Member> findALL(){
        return memberDao.findALL();
    }
    public void delete(String id){
        memberDao.delete(id);
    }
    public void insert(Member member){
        memberDao.insert(member);
    }
    public void update(Member member){
        memberDao.update(member);
    }
}
