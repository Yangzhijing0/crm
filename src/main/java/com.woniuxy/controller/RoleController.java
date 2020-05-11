package com.woniuxy.controller;

import com.woniuxy.pojo.Permission;
import com.woniuxy.service.RoleService;
import com.woniuxy.util.DataGridView;
import com.woniuxy.util.ResultDto;
import com.woniuxy.vo.RoleVo;
import com.woniuxy.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping("/roles")
    public DataGridView selectRolesByPage(RoleVo roleVo){
        return roleService.selectRolesByPage(roleVo);
    }
    @GetMapping("/roles/selectAllPermission.do")
    public List<Permission> selectAllPermission(){
        return this.roleService.selectAllPersmission();
    }

    @GetMapping(value = "/roles/getChecked.do")
    public Integer[] getChecked(int rid){
        return this.roleService.selectPerIdsByRid(rid);
    }
    @GetMapping(value ="/roles/assignRight.do")
    public ResultDto assignRight(int rid, @RequestParam("perids[]") Integer[] perids){
        try {
            this.roleService.assignRight(rid,perids);
            return ResultDto.RIGHT_GRANT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDto.RIGHT_GRANT_FAILURE;
        }
    }

}
