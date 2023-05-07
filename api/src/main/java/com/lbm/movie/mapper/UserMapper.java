package com.lbm.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lbm.movie.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
