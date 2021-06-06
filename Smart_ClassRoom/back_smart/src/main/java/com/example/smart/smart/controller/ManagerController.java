package com.example.smart.smart.controller;

import com.example.smart.smart.entity.Manager;
import com.example.smart.smart.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    ManagerService managerService;

    @GetMapping("/findALL")
    public List<Manager> getdata(){
        return managerService.findALL();
    }

    @GetMapping("/delete")
    public List<Manager> deletedata(@RequestParam String id){
        managerService.delete(id);
        return managerService.findALL();
    }

    @GetMapping("/add")
    public List<Manager> add(@RequestParam String id,@RequestParam String passward){
        Manager manager = new Manager();
        manager.setId(id);
        manager.setPassword(passward);
        managerService.insert(manager);
        return managerService.findALL();
    }

    @GetMapping("/update")
    public List<Manager> update(@RequestParam String id,@RequestParam String passward){
        Manager manager = new Manager();
        manager.setId(id);
        manager.setPassword(passward);
        managerService.update(manager);
        return managerService.findALL();
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public int login(@RequestBody Manager manager){
//        System.out.print(manager);
        return managerService.match(manager);
    }
}
