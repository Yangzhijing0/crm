package com.woniuxy.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Permission implements Serializable {
    private int id;
    private int pid;
    private String type;
    private String percode;
    private String name;
    private String icon;
    private String href;
    private int open;
    private List<Role> roles;
}
