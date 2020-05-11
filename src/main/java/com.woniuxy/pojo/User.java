package com.woniuxy.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class User implements Serializable {
    private int id;
    private String telephone;
    private String password;
    private String realname;
    private Date birthday;
    private String headimg;
    private int available;
    private List<Role> roles;
    private Dept dept;
}
