package com.woniuxy.controller;

import com.woniuxy.pojo.Dept;
import com.woniuxy.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController{
    @Autowired
    private DeptService deptService;
    @GetMapping(value = "/depts/list.do")
    public List<Dept> selectAllDepts(){
        return deptService.findAllDepts();
    }
}
