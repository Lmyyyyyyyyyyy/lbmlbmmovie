package com.lbm.movie.controller;

import com.lbm.movie.model.entity.Registration;
import com.lbm.movie.service.RegistrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "新闻接口")
@RequestMapping("/api/registration")
public class RegistrationController {

    @Resource
    private RegistrationService registrationService;

    @PostMapping("")
    @ApiOperation("报名活动")
    public void create(@RequestBody Registration registration) throws Exception {
        registrationService.create(registration);
    }

    @GetMapping("")
    @ApiOperation("查询所有报名信息")
    public List<Registration> create() {
        return registrationService.findAll();
    }

}
