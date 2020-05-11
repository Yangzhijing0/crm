package com.woniuxy.vo;

import com.woniuxy.pojo.Role;
import lombok.Data;

@Data
public class RoleVo extends Role {
    private int page=1;//设置当前页，默认首页为1
    private int limit;//设置每页的记录数
    public boolean LAY_CHECKED=false;
}
