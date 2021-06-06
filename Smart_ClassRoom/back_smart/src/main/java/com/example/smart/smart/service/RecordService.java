package com.example.smart.smart.service;

import com.example.smart.smart.dao.RecordDao;
import com.example.smart.smart.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecordService {
    @Autowired
    RecordService recordService;
    @Autowired
    RecordDao recordDao;

    public List<Record> findALL(){
        return recordDao.findALL();
    }
    public void delete(String id){
        recordDao.delete(id);
    }
    public void insert(Record record){
        recordDao.insert(record);
    }
    public void update(Record record){
        recordDao.update(record);
    }
    public List<Record> findByCard(String card){return recordDao.findByCard(card);}
    public Integer blooeanTime(String card, LocalDateTime time){
        List<Record> list = new ArrayList<Record>();
        list = recordService.findByCard(card);
        for(int i=0;i<list.size();i++){
            if(list.get(i).getStartT().getDayOfMonth() == time.getDayOfMonth()){
                return list.get(i).getId();
            }
        }
        return 0;
    }
}
