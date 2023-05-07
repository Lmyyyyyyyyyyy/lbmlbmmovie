package com.lbm.movie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lbm.movie.mapper.OrderMapper;
import com.lbm.movie.model.entity.Arrangement;
import com.lbm.movie.model.entity.Cart;
import com.lbm.movie.model.entity.Film;
import com.lbm.movie.model.entity.Order;
import com.lbm.movie.model.vo.OrderVO;
import com.lbm.movie.utils.DataTimeUtil;
import com.lbm.movie.constant.OrderStatus;
import com.lbm.movie.mapper.FilmMapper;
import com.lbm.movie.mapper.UserMapper;
import com.lbm.movie.service.ArrangementService;
import com.lbm.movie.service.OrderService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "order")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private ArrangementService arrangementService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private FilmMapper filmMapper;

    @Override
    public Order create(Cart cart) throws Exception {
        List<Integer> seats = arrangementService.getSeatsHaveSelected(cart.getAid());
        String[] split = cart.getSeats().split("号");
        for (String s : split) {
            if (seats.contains(Integer.parseInt(s))) throw new Exception("影片在购物车中躺了太长时间了，座位已被其他用户预订并支付了");
        }
        Order order = new Order();
        //生成订单id
        order.setId(UUID.randomUUID().toString());
        //写入用户id
        order.setUid(cart.getUid());
        //写入用户电话
        order.setPhone(cart.getPhone());
        //写入场次id
        order.setAid(cart.getAid());
        //写入座位信息
        order.setStatus(cart.getStatus());
        order.setSeats(cart.getSeats());
        if (cart.getStatus() == 2) order.setPayAt(DataTimeUtil.getNowTimeString());
        order.setPrice(cart.getPrice());
        order.setCreateAt(DataTimeUtil.getNowTimeString());
        orderMapper.insert(order);

        //订了几个座位就添加多少热度
        Film film = filmMapper.selectById(arrangementService.findById(cart.getAid()).getFid());
        film.setHot(film.getHot() + split.length);
        filmMapper.updateById(film);
        return order;
    }

    @Override
    public Order pay(String id) throws Exception {
        Order order = orderMapper.selectById(id);
        if (order == null) throw new Exception("不存在的订单id");

        if (DataTimeUtil.parseTimeStamp(order.getCreateAt()) + OrderStatus.EXPIRATION_TIME
                < System.currentTimeMillis()) {
            order.setStatus(OrderStatus.PAYMENT_FAILED);
            orderMapper.updateById(order);
            throw new Exception("订单支付超时");
        }

        order.setStatus(OrderStatus.PAYMENT_SUCCESSFUL);
        order.setPayAt(DataTimeUtil.getNowTimeString());
        orderMapper.updateById(order);
        return order;
    }

    @Override
    public void update(Order order) {
        orderMapper.updateById(order);
    }

    @Override
    public List<OrderVO> findAll() {
        return findByWrapper(new QueryWrapper<>());
    }

    @Override
    public List<OrderVO> findAll2() {
        return findByWrapper2(new QueryWrapper<>());
    }

    @Override
    public List<OrderVO> findByUser(String uid) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.in("uid", uid);
        return findByWrapper(wrapper);
    }

    /**
    1.根据查询条件(wrapper)从数据库中查询出满足条件的订单数据，存入orders列表
    2.循环遍历orders列表中每个订单信息，对每个订单信息进行处理并封装成OrderVO对象
    3.在OrderVO对象中分别设置订单、用户、场次和电影信息，其中用户信息从userMapper根据订单中的uid获取，
    场次信息通过arrangementService根据订单中的aid获取，电影信息通过filmMapper根据场次信息的fid获取
    4.将每个OrderVO对象添加到result列表中
    5.返回结果result列表
     */
    private List<OrderVO> findByWrapper(QueryWrapper<Order> wrapper) {
        List<Order> orders = orderMapper.selectList(wrapper);
        List<OrderVO> result = new ArrayList<>();
        for (Order order : orders) {
            OrderVO orderVO = new OrderVO();
            orderVO.setOrder(order);
            orderVO.setUser(userMapper.selectById(order.getUid()));
            Arrangement arrangement = arrangementService.findById(order.getAid());
            orderVO.setArrangement(arrangement);
            orderVO.setFilm(filmMapper.selectById(arrangement.getFid()));
            result.add(orderVO);
        }
        return result;
    }
    private List<OrderVO> findByWrapper2(QueryWrapper<Order> wrapper) {
        List<Order> orders = orderMapper.selectList(wrapper);
        List<OrderVO> result = new ArrayList<>();
        for (Order order : orders) {
            OrderVO orderVO = new OrderVO();
            orderVO.setOrder(order);
            orderVO.setUser(userMapper.selectById(order.getUid()));
            result.add(orderVO);
        }
        return result;
    }

}
