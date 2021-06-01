package com.example.smart.smart.dao;

import com.example.smart.smart.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
public class MemberDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<Member> managerMapper = new BeanPropertyRowMapper<>(Member.class);

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    //查全部
    public List<Member> findALL(){
        return jdbcTemplate.query("select * from member;",managerMapper);
    }

    //增加
    public void insert(Member member){
        String sql="insert into member values(null,?,?,?,?)";
        jdbcTemplate.update(sql,member.getName(),member.getCard(),member.getMoney(),member.getDay());
    }
    //删除
    public void delete(String id){
        String sql="delete from member where id =?";
        Object[] args={id};
        int[] argTypes={Types.INTEGER};
        jdbcTemplate.update(sql,args,argTypes);
    }
    //改
    public void update(Member member){
        String sql ="update member set Name =?,Card =?,Money =?,Day =? where id =?";
        jdbcTemplate.update(sql,member.getName(),member.getCard(),member.getMoney(),member.getDay(),member.getId());
    }
}
