package com.ny.lg.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ny.lg.user.dao.DemoMapper;
import com.ny.lg.user.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoMapper demoMapper;

    @Override
    public List<String> sayHello() {
        return demoMapper.sayHello();
    }

    @Override
    public PageInfo<String> sayHello(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<String> names = demoMapper.sayHello();
        PageInfo<String> pageInfo = new PageInfo<>(names);
        return pageInfo;
    }

}
