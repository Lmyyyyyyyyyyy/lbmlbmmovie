package com.lbm.movie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lbm.movie.model.dto.LoginDto;
import com.lbm.movie.model.entity.Admin;
import com.lbm.movie.utils.JwtTokenUtil;
import com.lbm.movie.constant.Roles;
import com.lbm.movie.mapper.AdminMapper;
import com.lbm.movie.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    /**
    这段代码实现了管理员登录功能，并生成相应的JWT(Json Web Token)令牌用于身份验证。
    具体步骤如下：
    1.根据用户输入的用户名和密码构建一个QueryWrapper对象(wrapper)，使用in方法匹配数据库中对应的管理员信息，即查询符合条件的唯一管理员对象。
    2.通过调用adminMapper.selectOne(wrapper)方法获取满足查询条件的管理员对象(admin)。
    3.进行异常判断，如果管理员对象为null，则抛出"用户名密码错误"的自定义异常。
    4.判断是否选择记住我，如果是则设置过期时间为JwtTokenUtil.REMEMBER_EXPIRATION_TIME；否则设置过期时间为JwtTokenUtil.EXPIRATION_TIME。
    5.创建一个角色列表(roles)并向其中添加Roles.ROLE_ADMIN枚举值作为管理员角色标识。
    6.调用JwtTokenUtil.createToken方法创建JWT认证令牌，并将其返回给前端进行保存与使用。
    综上所述，该方法实现了管理员登录验证，将JWT令牌用于基于标准Web Token的身份验证，可通过已知私钥验签JWT令牌以确认其内容是否有效。
     */
    @Override
    public String login(LoginDto loginDto) throws Exception {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.in("username", loginDto.getUsername());
        wrapper.in("password", loginDto.getPassword());
        Admin admin = adminMapper.selectOne(wrapper);
        if (admin == null) throw new Exception("用户名密码错误");
        //是否选择记住我
        long exp = loginDto.isRemember() ? JwtTokenUtil.REMEMBER_EXPIRATION_TIME : JwtTokenUtil.EXPIRATION_TIME;
        List<String> roles = new ArrayList<>();
        roles.add(Roles.ROLE_ADMIN);
        return JwtTokenUtil.createToken(loginDto.getUsername(), roles, exp);
    }

}
