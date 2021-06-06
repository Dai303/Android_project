package com.example.smart.smart.controller;

import com.example.smart.smart.entity.Member;
import com.example.smart.smart.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    MemberService memberService;
    String card="123";

    @GetMapping("/findALL")
    public List<Member> getdata(){
        return memberService.findALL();
    }

    @GetMapping("/delete")
    public List<Member> deletedata(@RequestParam String id){
        memberService.delete(id);
        return memberService.findALL();
    }

//    @RequestMapping(value = "/add",method = RequestMethod.POST,
//            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
//    @ResponseBody
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

    @PostMapping("/findOne")
    public Member update(@RequestParam Integer id){
        return memberService.findOne(id);
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

//    @RequestMapping(value = "/GetSalary",method = RequestMethod.POST,
//            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
//    @ResponseBody
    @GetMapping("/GetSalary")
    public List<Member> login(){
        List<Member> list = memberService.findALL();
        return memberService.compute(list);
    }
    @GetMapping("/getCard")
    public String getCard(){
        return card;
    }

    @PostMapping("/setCard")
    public void getCard(@RequestParam String Newcard){
        this.card = Newcard;
    }
}
