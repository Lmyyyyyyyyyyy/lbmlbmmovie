package com.lbm.movie.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 活动报名
 */
@Data
@TableName("L_registration")
public class Registration implements Serializable {

    private String id;

    //活动id
    private String aid;

    //报名用户id
    private String uid;

    private String createAt;

}
