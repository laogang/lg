package com.ny.lg.user.module;

import com.alibaba.fastjson.JSONObject;

/**
 * @version: 1.0.0
 * @author: guog
 * @date: 2021/5/27 22:43
 * @description:
 **/
public class TestMap {
    public static void main(String[] args) {
        StuDO stuDO = new StuDO();
        stuDO.setName("张三");
        stuDO.setAge(99);
        stuDO.setSex("男");

        StuDTO stuDTO = new StuDTO();
        stuDTO.setAge(98);
        stuDTO.setName("李四");
        stuDTO.setId("no.1");
        StuDO stu = StuMapper.STU_INSTANCE.toStu(stuDTO);
        System.out.println(JSONObject.toJSONString(stu));
        System.out.println("*************");

        StuDTO stuDO1 = StuMapper.STU_INSTANCE.toStuDTO(stuDO);
        System.out.println(JSONObject.toJSONString(stuDO1));
    }
}
