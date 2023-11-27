package com.example.izohlilugat.service.mapper;


import com.example.izohlilugat.dto.NoteDto;
import com.example.izohlilugat.model.Note;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public  abstract class NoteMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public abstract Note toEntity(NoteDto dto);


    public  abstract NoteDto toDto(Note word);


    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public  abstract void toUpdateFromDto(@MappingTarget Note word, NoteDto dto);
}
