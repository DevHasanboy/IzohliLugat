package com.example.izohlilugat.service.mapper;



import com.example.izohlilugat.dto.WordTypeDto;
import com.example.izohlilugat.model.WordType;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public  abstract class WordTypeMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public abstract WordType toEntity(WordTypeDto dto);

    public  abstract WordTypeDto toDto(WordType word);

    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public  abstract void toUpdateFromDto(@MappingTarget WordType word, WordTypeDto dto);
}
