package com.woniuxy.dao;

import com.woniuxy.pojo.Dept;

import java.util.List;

public interface DeptMapper {
    public List<Dept> selectAllDepts();
    public Dept selectDeptBynoStep(int id);
    public void deleteDeptByNo(int id);
    public void saveDept(Dept dept);
    public void updateDept(Dept dept);
}
