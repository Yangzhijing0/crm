package com.woniuxy.service;

import com.woniuxy.pojo.Dept;

import java.util.List;

public interface DeptService {
    public List<Dept> findAllDepts();
    public Dept findDeptById(int id);
    public void addDept(Dept dept);
    public void deleteDept(int id);
    public void updateDept(Dept dept);
}
