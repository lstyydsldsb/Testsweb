package ldu.service;

import ldu.dao.IDao;
import ldu.ldu.entity.Branch;
import ldu.ldu.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Mapper
public class Branchservice {
    @Autowired
    IDao dao;
    public List<Branch> findall(){
        return dao.findall();
    }
    public int insertstu(Branch stu) {
        int res = 0;
        try {
            res = dao.insert(stu);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
    public int deletestu(String name) {

        return dao.delete(name);
    }
    public int updatestu(Branch stu) {
        return dao.update(stu);
    }
    public List<Branch> findstu(String name) {
        return dao.find("%"+name + '%');
    }
    public List<Users> findalluser() {
        return dao.findalluser();
    }
    public List<Users> login(String username, String password){
        return  dao.login(username,password);
    }
    public List<Branch> findbycity(String name) {
        return dao.findbycity("%"+name + '%');
    }

}
