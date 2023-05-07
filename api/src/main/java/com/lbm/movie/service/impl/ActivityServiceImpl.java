package com.lbm.movie.service.impl;

import com.lbm.movie.model.entity.Activity;
import com.lbm.movie.utils.DataTimeUtil;
import com.lbm.movie.mapper.ActivityMapper;
import com.lbm.movie.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityMapper activityMapper;

    /**
    1.生成一个UUID，并将其设置为当前活动对象(activity)的ID属性。UUID是一种通用唯一识别码，可以保证每个ID都是独一无二的。
    2.获取当前时间的字符串表示，并将其设置为当前活动对象(activity)的createAt属性。
    3.这里使用DataTimeUtil.getNowTimeString()方法获取当前时间的字符串表示。
    4.调用activityMapper对象的insert方法，将activity对象插入到数据库中。
    综上所述，该方法实现了创建一个新的活动(Activity)记录并将其保存到数据库中,
    在该方法中，通过UUID来保证主键ID的唯一性，并将当前时间设为活动创建时间，以便后续查询和展示。
     */
    @Override
    public void create(Activity activity) {
        activity.setId(UUID.randomUUID().toString());
        activity.setCreateAt(DataTimeUtil.getNowTimeString());
        activityMapper.insert(activity);
    }

    @Override
    public Activity findById(String id) {
        return activityMapper.selectById(id);
    }

    @Override
    public List<Activity> findAll() {
        return activityMapper.selectList(null);
    }

    @Override
    public void deleteById(String id) {
        activityMapper.deleteById(id);
    }

}
