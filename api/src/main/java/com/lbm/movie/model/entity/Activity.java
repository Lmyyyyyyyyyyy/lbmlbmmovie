package com.lbm.movie.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 影院活动
 */
@Data
@TableName("L_activity")
@NoArgsConstructor
public class Activity implements Serializable {

    private String id;

    private String content;

    private Integer number;

    private String startTime;

    private String endTime;

    private String createAt;

}
