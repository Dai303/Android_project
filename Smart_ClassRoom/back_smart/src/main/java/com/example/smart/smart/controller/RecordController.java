package com.example.smart.smart.controller;

import com.example.smart.smart.entity.Record;
import com.example.smart.smart.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import java.time.LocalDateTime;
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

    @GetMapping("/delete")
    public List<Record> deletedata(@RequestParam String id){
        recordService.delete(id);
        return recordService.findALL();
    }

    @PostMapping("/add")
    public List<Record> add(@RequestParam String name,@RequestParam LocalDateTime startT,@RequestParam LocalDateTime endT){
        Record record = new Record();
        record.setNumID(name);
        record.setStartT(startT);
        record.setEndT(endT);
        recordService.insert(record);
        return recordService.findALL();
    }

    @PostMapping("/update")
    public List<Record> update(@RequestParam Integer id,@RequestParam LocalDateTime endT){
        Record record = new Record();
        record.setEndT(endT);
        record.setId(id);
        recordService.update(record);
        return recordService.findALL();
    }

    @PostMapping("/findByCard")
    public List<Record> findBycard(@RequestParam String card){
        return recordService.findByCard(card);
    }

    @PostMapping("/blooean")
    public Integer blooeanDate(@RequestParam String card,@RequestParam LocalDateTime time){
//        LocalDateTime time2 = LocalDateTime.now();
        return recordService.blooeanTime(card,time);
    }
}