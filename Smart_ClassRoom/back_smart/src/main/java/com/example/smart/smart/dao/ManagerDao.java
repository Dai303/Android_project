package com.example.smart.smart.dao;

import com.example.smart.smart.entity.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
public class ManagerDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<Manager> managerMapper = new BeanPropertyRowMapper<>(Manager.class);

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    //查全部
    public List<Manager> findALL(){
        return jdbcTemplate.query("select * from manager;",managerMapper);
    }

    //增加
    public void insert(Manager manager){
        String sql="insert into manager values(?,?)";
        jdbcTemplate.update(sql,manager.getId(),manager.getPassword());
    }
    //删除
    public void delete(String id){
        String sql="delete from manager where id =?";
        Object[] args={id};
        int[] argTypes={Types.INTEGER};
        jdbcTemplate.update(sql,args,argTypes);
    }
    //改
    public void update(Manager manager){
        String sql ="update manager set password =? where id =?";
        jdbcTemplate.update(sql,manager.getPassword(),manager.getId());
    }
}
