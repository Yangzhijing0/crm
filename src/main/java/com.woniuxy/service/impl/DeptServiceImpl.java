package com.woniuxy.service.impl;

import com.woniuxy.dao.DeptMapper;
import com.woniuxy.pojo.Dept;
import com.woniuxy.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Override
    public List<Dept> findAllDepts() {
        return deptMapper.selectAllDepts();
    }
    @Override
    public Dept findDeptById(int id) {
        return deptMapper.selectDeptBynoStep(id);
    }

    @Override
    public void addDept(Dept dept) {
        this.deptMapper.saveDept(dept);
    }

    @Override
    public void deleteDept(int id) {
        this.deptMapper.deleteDeptByNo(id);
    }

    @Override
    public void updateDept(Dept dept) {
        this.deptMapper.updateDept(dept);
    }
}