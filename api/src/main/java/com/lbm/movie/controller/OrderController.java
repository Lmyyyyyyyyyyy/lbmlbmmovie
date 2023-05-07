package com.lbm.movie.controller;

import com.lbm.movie.model.entity.Cart;
import com.lbm.movie.model.entity.Order;
import com.lbm.movie.model.vo.OrderVO;
import com.lbm.movie.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "订单接口")
@RequestMapping("/api/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("")
    @ApiOperation(value = "创建订单")
    public void save(@RequestBody Cart cart) throws Exception {
        orderService.create(cart);
    }

    @GetMapping("/former")
    @ApiOperation(value = "查询所有订单")
    public List<OrderVO> findAll() {
        return orderService.findAll();
    }

    @GetMapping("")
    @ApiOperation(value = "查询所有订单")
    public List<OrderVO> findAll2() {
        return orderService.findAll2();
    }

    @PutMapping("")
    @ApiOperation(value = "查询所有订单")
    public void update(@RequestBody Order order) {
        orderService.update(order);
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "查询用户订单")
    public List<OrderVO> findByUser(@PathVariable String id) {
        return orderService.findByUser(id);
    }

    @GetMapping("/pay")
    @ApiOperation(value = "支付订单")
    public Order save(String id) throws Exception {
        return orderService.pay(id);
    }

}
