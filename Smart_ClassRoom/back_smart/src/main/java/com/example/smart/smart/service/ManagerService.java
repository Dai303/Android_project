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
    public int match(Manager manager){
        int flag = 0;
        List<Manager> list = managerService.findALL();
        for(int i=0;i<list.size();i++){
            if(manager.getId().equals(list.get(i).getId())){
                flag = 1;//找到该用户
                System.out.println(list.get(i).getId());
                if(manager.getPassword().equals(list.get(i).getPassword())){
                    flag = 2;//并且密码匹配
                    System.out.println(list.get(i).getPassword());
                }
                break;
            }
        }
        return flag;
    }
}
