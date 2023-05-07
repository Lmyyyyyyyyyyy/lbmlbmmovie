package com.lbm.movie.model.vo;

import com.lbm.movie.model.entity.Arrangement;
import com.lbm.movie.model.entity.Cart;
import com.lbm.movie.model.entity.Film;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 购物车前端展示
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartVO {

    private Film film;

    private Arrangement arrangement;

    private Cart cart;

}
