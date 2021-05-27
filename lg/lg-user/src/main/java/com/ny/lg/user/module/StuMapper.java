package com.ny.lg.user.module;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @version: 1.0.0
 * @author: guog
 * @date: 2021/5/27 22:33
 * @description:
 **/
@Mapper
public interface StuMapper {
    StuMapper STU_INSTANCE = Mappers.getMapper(StuMapper.class);

    StuDO toStu(StuDTO stuDTO);

    StuDTO toStuDTO(StuDO stuDO);
}
