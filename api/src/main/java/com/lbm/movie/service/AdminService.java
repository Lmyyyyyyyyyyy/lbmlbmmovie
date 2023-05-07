package com.lbm.movie.service;

import com.lbm.movie.model.dto.LoginDto;

public interface AdminService {

    String login(LoginDto loginDto) throws Exception;

}
