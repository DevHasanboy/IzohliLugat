package com.example.izohlilugat.service.mapper;


import com.example.izohlilugat.dto.AudioDto;
import com.example.izohlilugat.model.Audio;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public  abstract class AudioMapper {
    public abstract AudioDto toDto(Audio audio);


    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public  abstract void toUpdateFromDto(@MappingTarget Audio audio, AudioDto dto);
}
