package com.example.smart.smart.controller;

import com.example.smart.smart.entity.Record;
import com.example.smart.smart.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    RecordService recordService;

    @GetMapping("/findALL")
    public List<Record> getdata(){
        return recordService.findALL();
    }

    @PostMapping("/delete")
    public List<Record> deletedata(@RequestParam String id){
        recordService.delete(id);
        return recordService.findALL();
    }

    @PostMapping("/add")
    public List<Record> add(@RequestParam String name){
        Record record = new Record();
        record.setNumID(name);
        record.setStartT(LocalDateTime.of(2021,12,12,10,11,12));
        record.setEndT(LocalDateTime.of(2021,12,12,10,11,12));
        recordService.insert(record);
        return recordService.findALL();
    }

    @PostMapping("/update")
    public List<Record> update(@RequestParam String name,@RequestParam Integer id){
        Record record = new Record();
        record.setNumID(name);
        record.setStartT(LocalDateTime.of(2021,12,12,10,11,12));
        record.setEndT(LocalDateTime.of(2021,12,12,10,11,12));
        record.setId(id);
        recordService.update(record);
        return recordService.findALL();
    }
}