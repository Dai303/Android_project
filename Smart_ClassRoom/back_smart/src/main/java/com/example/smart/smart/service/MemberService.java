package com.example.smart.smart.service;

import com.example.smart.smart.dao.MemberDao;
import com.example.smart.smart.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberService memberService;
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
    public List<Member> compute(List<Member> list){
//        List<Member> list1 = new ArrayList<Member>();
        for(int i=0;i<list.size();i++){
            list.get(i).setMoney(list.get(i).getDay()*300);
        }
        return list;
    }
    public Member findOne(Integer id){
        List<Member> list = memberService.findALL();
        Member member = new Member();
        for( int i=0;i<list.size();i++){
            if(list.get(i).getId() == id)
                member=list.get(i);
        }
        return member;
    }
    public Member findByID(String card){
        List<Member> list = memberService.findALL();
        Member member = new Member();
        for( int i=0;i<list.size();i++){
            if(list.get(i).getCard().equals(card))
                member=list.get(i);
        }
        return member;
    }
}
