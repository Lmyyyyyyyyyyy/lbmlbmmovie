package com.lbm.movie.model.vo;

import com.lbm.movie.model.entity.Arrangement;
import com.lbm.movie.model.entity.Film;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ArrangementVO {

    private List<Arrangement> arrangements;

    private Film film;

}
