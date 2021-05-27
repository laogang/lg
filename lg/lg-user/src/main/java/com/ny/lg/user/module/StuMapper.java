package com.ny.lg.user.module;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @version: 1.0.0
 * @author: guog
 * @date: 2021/5/27 22:33
 * @description:
 **/
//add componentModel = "spring" 整合spring
//@Autowired
//    private StuMapper stuMapper;
//
//    // 转化
//    StuDO stuDO = stuMapper.toStu(stuDTO);
@Mapper(componentModel = "spring")
public interface StuMapper {
    StuMapper STU_INSTANCE = Mappers.getMapper(StuMapper.class);

    StuDO toStu(StuDTO stuDTO);

    StuDTO toStuDTO(StuDO stuDO);
}
