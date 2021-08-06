package com.ny.lg.user.module;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-06T10:11:18+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class StuMapperImpl implements StuMapper {

    @Override
    public StuDO toStu(StuDTO stuDTO) {
        if ( stuDTO == null ) {
            return null;
        }

        StuDO stuDO = new StuDO();

        stuDO.setName( stuDTO.getName() );
        stuDO.setAge( stuDTO.getAge() );

        return stuDO;
    }

    @Override
    public StuDTO toStuDTO(StuDO stuDO) {
        if ( stuDO == null ) {
            return null;
        }

        StuDTO stuDTO = new StuDTO();

        stuDTO.setName( stuDO.getName() );
        stuDTO.setAge( stuDO.getAge() );

        return stuDTO;
    }
}
