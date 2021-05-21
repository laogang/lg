package com.ny.lg.user.controller;

import com.ny.lg.common.constants.Result;
import com.ny.lg.user.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@Api(tags = "用户管理相关的接口")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "/say", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户", notes = "查询用户", httpMethod = "GET")
    public Result say() {
        return Result.success(demoService.sayHello());
    }

    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询用户", notes = "分页查询用户", httpMethod = "GET")
    public Result sayHello(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        return Result.success(demoService.sayHello(pageNum, pageSize));
    }
}

