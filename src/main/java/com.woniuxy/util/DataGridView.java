package com.woniuxy.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGridView {
    private Integer code=0;
    private String msg="";
    private Long count;
    private Object data;
    public DataGridView(Long count, Object data) {
        this.count = count;
        this.data = data;
    }
}
