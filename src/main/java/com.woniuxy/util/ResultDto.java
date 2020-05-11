package com.woniuxy.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 定义返回结果的格式的Bean
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {
    public static ResultDto LOGIN_SUCCESS=new ResultDto(1001,"登录成功");
    public static ResultDto LOGIN_FAILURE=new ResultDto(-1001,"登录失败");
    public static ResultDto DELETE_USER_SUCCESS=new ResultDto(1002,"删除用户成功");
    public static ResultDto DELETE_USER_FAILURE=new ResultDto(-1002,"删除用户失败");
    public static ResultDto UPLOAD_FILE_FAILURE=new ResultDto(-1003,"上传失败");
    public static ResultDto USER_EXITS=new ResultDto(-1004,"用户已存在");
    public static ResultDto USER_ADD_SUCCESS=new ResultDto(2001,"成功增加用户");
    public static ResultDto USER_ADD_FAILURE=new ResultDto(-2001,"增加用户失败");
    public static ResultDto ROLE_GRANT_SUCCESS=new ResultDto(2002,"分配角色成功");
    public static ResultDto ROLE_GRANT_FAILURE=new ResultDto(-2002,"分配角色失败");
    public static ResultDto RIGHT_GRANT_SUCCESS=new ResultDto(2003,"分配权限成功");
    public static ResultDto RIGHT_GRANT_FAILURE=new ResultDto(-2003,"分配权限失败");
    private int code;
    private String msg;
    private Object data;

    public ResultDto(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
