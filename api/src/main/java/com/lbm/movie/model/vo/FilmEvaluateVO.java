package com.lbm.movie.model.vo;

import com.lbm.movie.model.entity.FilmEvaluate;
import com.lbm.movie.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 电影评分前端展示
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmEvaluateVO implements Serializable {

    private String id;

    private FilmEvaluate filmEvaluate;

    private User user;

}
