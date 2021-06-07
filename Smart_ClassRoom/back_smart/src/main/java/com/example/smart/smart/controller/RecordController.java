package com.example.smart.smart.controller;

import com.example.smart.smart.entity.Record;
import com.example.smart.smart.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @PostMapping("/delete")
    public List<Record> deletedata(@RequestParam String id){
        recordService.delete(id);
        return recordService.findALL();
    }

//    @RequestMapping(value = "/add",method = RequestMethod.POST,
//            produces = {"application/json;charset=UTF-8"})
//    @ResponseBody
    @PostMapping("/add")
    public List<Record> add(@RequestBody Record body){
        recordService.insert(body);
//        System.out.println(body);
        return recordService.findALL();
    }

    @PostMapping("/update")
    public List<Record> update(@RequestBody Record body){
//        Record record = new Record();
//        record.setEndT(endT);
//        record.setId(id);
        recordService.update(body);
        return recordService.findALL();
    }

    @PostMapping("/findByCard")
    public List<Record> findBycard(@RequestParam String card){
        return recordService.findByCard(card);
    }

    @PostMapping("/blooean")
    public Integer blooeanDate(@RequestBody Record record){
//        LocalDateTime time2 = LocalDateTime.now();
        return recordService.blooeanTime(record);
    }
}