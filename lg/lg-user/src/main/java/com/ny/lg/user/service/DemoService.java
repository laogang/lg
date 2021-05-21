package com.ny.lg.user.service;

import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DemoService {
    List<String> sayHello();

    PageInfo<String> sayHello(int pageNum, int pageSize);

}
