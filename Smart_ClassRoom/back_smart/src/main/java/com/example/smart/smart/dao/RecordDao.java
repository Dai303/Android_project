package com.example.smart.smart.dao;

import com.example.smart.smart.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
public class RecordDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<Record> managerMapper = new BeanPropertyRowMapper<>(Record.class);

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    //查全部
    public List<Record> findALL(){
        return jdbcTemplate.query("select * from recard;",managerMapper);
    }

    //按卡号查找
    public List<Record> findByCard(String card){
        String sql="select * from recard where NumID =?";
        Object[] args={card};
        int[] argTypes={Types.VARCHAR};
        return jdbcTemplate.query(sql,args,argTypes,managerMapper);
    }

    //增加
    public void insert(Record record){
        String sql="insert into recard values(null,?,?,?)";
        jdbcTemplate.update(sql,record.getNumID(),record.getStartT(),record.getEndT());
    }
    //删除
    public void delete(String id){
        String sql="delete from recard where id =?";
        Object[] args={id};
        int[] argTypes={Types.INTEGER};
        jdbcTemplate.update(sql,args,argTypes);
    }
    //改
    public void update(Record record){
        String sql ="update recard set StartT =?,EndT =? where id =?";
        jdbcTemplate.update(sql,record.getStartT(),record.getEndT(),record.getId());
    }
}