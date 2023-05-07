package com.lbm.movie.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 管理员
 */
@Data
@TableName("L_admin")
@NoArgsConstructor
@AllArgsConstructor
public class Admin implements Serializable {

    private String id;

    private String username;

    private String password;

}
