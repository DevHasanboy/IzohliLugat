package com.example.izohlilugat.service.mapper;


import com.example.izohlilugat.dto.DayWordDto;
import com.example.izohlilugat.model.DayWord;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public  abstract class DayWordMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    public  abstract DayWord toEntity(DayWordDto dto);

    public abstract DayWordDto toDto(DayWord word);


    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public  abstract void toUpdateFromDto(@MappingTarget DayWord word, DayWordDto dto);
}
