package com.lbm.movie.service.impl;

import com.lbm.movie.mapper.OrderExceptionMapper;
import com.lbm.movie.model.entity.OrderException;
import com.lbm.movie.service.OrderExceptionService;
import com.lbm.movie.utils.DataTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class OrderExceptionServiceImpl implements OrderExceptionService {

    @Resource
    private OrderExceptionMapper orderExceptionMapper;

    @Override
    public OrderException create(OrderException orderException) {
        orderException.setStatus(false);
        orderException.setId(UUID.randomUUID().toString());
        orderException.setCreateAt(DataTimeUtil.getNowTimeString());
        orderExceptionMapper.insert(orderException);
        return orderException;
    }

    @Override
    public List<OrderException> findAll() {
        return orderExceptionMapper.selectList(null);
    }

    @Override
    public void handleException(OrderException orderException) {
        orderException.setEndAt(DataTimeUtil.getNowTimeString());
        orderExceptionMapper.updateById(orderException);
    }

}
