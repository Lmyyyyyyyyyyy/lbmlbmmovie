package com.lbm.movie.service;

import com.lbm.movie.model.entity.OrderException;

import java.util.List;

public interface OrderExceptionService {

    OrderException create(OrderException orderException);

    List<OrderException> findAll();

    void handleException(OrderException orderException);

}
