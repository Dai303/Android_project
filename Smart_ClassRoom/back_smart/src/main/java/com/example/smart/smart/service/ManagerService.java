package com.example.smart.smart.service;

import com.example.smart.smart.dao.ManagerDao;
import com.example.smart.smart.entity.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {
    @Autowired
    ManagerService managerService;
    @Autowired
    ManagerDao managerDao;

    public List<Manager> findALL(){
        return managerDao.findALL();
    }
    public void delete(String id){
        managerDao.delete(id);
    }
    public void insert(Manager manager){
        managerDao.insert(manager);
    }
    public void update(Manager manager){
        managerDao.update(manager);
    }
}
