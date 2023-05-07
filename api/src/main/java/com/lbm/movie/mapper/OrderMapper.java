package com.lbm.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lbm.movie.model.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
