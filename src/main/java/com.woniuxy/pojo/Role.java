package com.woniuxy.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Role implements Serializable {
    private int id;
    private String rolename;
    private List<User> users;
    private List<Permission> permissions;
}
