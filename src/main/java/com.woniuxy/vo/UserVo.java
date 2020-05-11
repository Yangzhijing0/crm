package com.woniuxy.vo;

import com.woniuxy.pojo.User;
import lombok.Data;

@Data
public class UserVo extends User {
    private int page=1;//设置当前页，默认首页为1
    private int limit;//设置每页的记录数
}
