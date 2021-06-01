package com.example.smart.smart.controller;

import com.example.smart.smart.entity.Member;
import com.example.smart.smart.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("/findALL")
    public List<Member> getdata(){
        return memberService.findALL();
    }

    @GetMapping("/delete")
    public List<Member> deletedata(@RequestParam String id){
        memberService.delete(id);
        return memberService.findALL();
    }

    @GetMapping("/add")
    public List<Member> add(@RequestParam String name,@RequestParam String card,@RequestParam Integer money,@RequestParam Integer day){
        Member member = new Member();
        member.setName(name);
        member.setCard(card);
        member.setMoney(money);
        member.setDay(day);
        memberService.insert(member);
        return memberService.findALL();
    }

    @GetMapping("/update")
    public List<Member> update(@RequestParam String name,@RequestParam String card,@RequestParam Integer money,@RequestParam Integer day,@RequestParam Integer id){
        Member member = new Member();
        member.setName(name);
        member.setCard(card);
        member.setMoney(money);
        member.setDay(day);
        member.setId(id);
        memberService.update(member);
        return memberService.findALL();
    }
}
