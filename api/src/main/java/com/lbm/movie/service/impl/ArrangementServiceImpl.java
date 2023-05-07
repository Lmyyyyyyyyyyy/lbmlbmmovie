package com.lbm.movie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lbm.movie.mapper.FilmMapper;
import com.lbm.movie.mapper.OrderMapper;
import com.lbm.movie.model.entity.Arrangement;
import com.lbm.movie.model.entity.Order;
import com.lbm.movie.model.vo.ArrangementVO;
import com.lbm.movie.utils.DataTimeUtil;
import com.lbm.movie.mapper.ArrangementMapper;
import com.lbm.movie.service.ArrangementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArrangementServiceImpl implements ArrangementService {

    @Resource
    private ArrangementMapper arrangementMapper;

    @Resource
    private FilmMapper filmMapper;

    @Resource
    private OrderMapper orderMapper;

    /**
     * 该方法用于向数据库中插入一条新的场次记录(Arrangement)，设置该场次的初始票房为0，创建时间为当前时间，并将该场次添加到数据库中。
     * @param arrangement
     */

    @Override
    public void save(Arrangement arrangement) {
        arrangement.setBoxOffice(0);
        arrangement.setCreateAt(DataTimeUtil.getNowTimeString());
        arrangementMapper.insert(arrangement);
    }

    /**
     * 该方法用于从数据库中查找所有场次记录(Arrangement)，并将它们封装到List集合中返回给调用方。
     * @return
     */

    @Override
    public List<Arrangement> findAll() {
        return arrangementMapper.selectList(null);
    }

    /**
     * 该方法通过电影ID(fid)从数据库中查找符合条件的场次列表(List<Arrangement>)，
     并将其封装成一个包含场次列表和电影信息的对象（ArrangementVO）返回至前端展示。
     * @param fid
     * @return
     */

    @Override
    public ArrangementVO findByFilmId(String fid) {
        List<Arrangement> list = arrangementMapper.selectList(new QueryWrapper<Arrangement>().in("fid", fid));
        return new ArrangementVO(list, filmMapper.selectById(fid));
    }

    /**
     * 该方法通过场次ID(id)从数据库中获取所有预订该场次座位的订单数据(List<Order>)，
     并将这些订单中已经选定的座位号码(转换为整数形式)放入一个List<Integer>集合中返回给调用方。
     * @param id
     * @return
     */

    @Override
    public List<Integer> getSeatsHaveSelected(String id) {
        List<Order> orders = orderMapper.selectList(new QueryWrapper<Order>().in("aid", id));
        List<Integer> seats = new ArrayList<>();
        for (Order o : orders) {
            String[] split = o.getSeats().split("号");
            for (String s : split) {
                seats.add(Integer.parseInt(s));
            }
        }
        return seats;
    }

    /**
     * 该方法通过场次ID(id)从数据库中查找一条场次记录(Arrangement)，并将其返回给调用方。
     * @param id
     * @return
     */

    @Override
    public Arrangement findById(String id) {
        return arrangementMapper.selectById(id);
    }

    /**
     * 该方法通过场次ID(id)从数据库中删除一条场次记录(Arrangement)。
     * @param id
     */

    @Override
    public void deleteById(String id) {
        arrangementMapper.deleteById(id);
    }

    /**
     * 该方法用于更新一条场次记录(Arrangement)的信息，并将修改后的场次信息写入数据库中。
     * @param arrangement
     * @return
     */

    @Override
    public Arrangement Update(Arrangement arrangement) {
        arrangementMapper.updateById(arrangement);
        return arrangement;
    }

}
