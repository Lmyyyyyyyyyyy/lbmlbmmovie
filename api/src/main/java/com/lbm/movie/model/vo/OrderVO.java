package com.lbm.movie.model.vo;

import com.lbm.movie.model.entity.Arrangement;
import com.lbm.movie.model.entity.Film;
import com.lbm.movie.model.entity.Order;
import com.lbm.movie.model.entity.User;
import lombok.Data;

@Data
public class OrderVO {

    private Order order;

    private User user;

    private Film film;

    private Arrangement arrangement;

}
