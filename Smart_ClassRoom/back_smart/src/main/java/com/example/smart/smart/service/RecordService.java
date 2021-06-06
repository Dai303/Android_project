package com.example.smart.smart.service;

import com.example.smart.smart.dao.RecordDao;
import com.example.smart.smart.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    @Autowired
    RecordService recordService;
    @Autowired
    RecordDao recordDao;

    public List<Record> findALL() {
        return recordDao.findALL();
    }

    public void delete(String id) {
        recordDao.delete(id);
    }

    public void insert(Record record) {
        recordDao.insert(record);
    }

    public void update(Record record) {
        recordDao.update(record);
    }
}
