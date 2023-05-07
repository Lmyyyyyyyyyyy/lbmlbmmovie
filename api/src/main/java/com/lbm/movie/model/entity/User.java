package com.lbm.movie.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户
 */
@Data
@TableName("L_user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private String id;

    private String username;

    private String password;

    //昵称
    private String nickname;

    //邮箱
    private String email;

    //生日
    private String birthday;

    //性别
    private String gender;

    //个人简介
    private String info;

    private String createAt;

    private String updateAt;

}
