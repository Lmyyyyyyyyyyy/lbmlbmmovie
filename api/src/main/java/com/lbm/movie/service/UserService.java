package com.lbm.movie.service;

import com.lbm.movie.model.dto.LoginDto;
import com.lbm.movie.model.entity.User;

import java.util.List;

public interface UserService {

    User login(LoginDto dto) throws Exception;

    List<User> findAll();

    User findById(String id);

    User update(User user);

    User save(User user) throws Exception;

    User findByUsername(String username);

    void deleteById(String id);

}
