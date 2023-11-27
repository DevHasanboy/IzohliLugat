package com.example.izohlilugat.service.mapper;


import com.example.izohlilugat.dto.WordInSentenceDto;
import com.example.izohlilugat.model.WordInSentence;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public  abstract class WordInSentenceMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public abstract WordInSentence toEntity(WordInSentenceDto dto);

    public abstract WordInSentenceDto toDto(WordInSentence word);



    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public  abstract void toUpdateFromDto(@MappingTarget WordInSentence word, WordInSentenceDto dto);
}
