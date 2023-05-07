package com.lbm.movie.service;

import com.lbm.movie.model.entity.Cart;
import com.lbm.movie.model.entity.Order;
import com.lbm.movie.model.vo.OrderVO;

import java.util.List;

public interface OrderService {

    Order create(Cart cart) throws Exception;

    Order pay(String id) throws Exception;

    void update(Order order);

    List<OrderVO> findAll();

    List<OrderVO> findAll2();

    List<OrderVO> findByUser(String uid);

}
