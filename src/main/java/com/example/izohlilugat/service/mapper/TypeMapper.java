package com.example.izohlilugat.service.mapper;


import com.example.izohlilugat.dto.TypeDto;
import com.example.izohlilugat.model.Type;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public  abstract class TypeMapper {


    @Autowired
    protected WordTypeMapper wordTypeMapper;
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public  abstract Type toEntity(TypeDto dto);


    public  abstract TypeDto toDto(Type word);


    @Mapping(target = "wordTypes",expression = "java(word.getWordTypes().stream().map(this.wordTypeMapper::toDto).toList())")
    public  abstract TypeDto toDtoWithWordType(Type word);

  /*  public void view(){
        Type type=new Type();
        TypeDto dto=new TypeDto();
        dto.setWordTypes(type.getWordTypes().stream().map(this.wordTypeMapper::toDto).toList());
    }*/

    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void toUpdateFromDto(@MappingTarget Type word, TypeDto dto);
}
