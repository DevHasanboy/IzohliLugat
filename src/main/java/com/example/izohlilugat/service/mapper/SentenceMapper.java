package com.example.izohlilugat.service.mapper;

import com.example.izohlilugat.dto.SentenceDto;
import com.example.izohlilugat.model.Sentence;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring")
public  abstract class SentenceMapper {

    @Autowired
    protected WordInSentenceMapper wordInSentenceMapper;

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public  abstract Sentence toEntity(SentenceDto dto);

    public  abstract SentenceDto toDto(Sentence word);

    @Mapping(target = "wordInSentences", expression = "java(word.getWordInSentences().stream().map(this.wordInSentenceMapper::toDto).toList())")
    public  abstract SentenceDto toDtoWithWordInSentence(Sentence word);


  /*  public void view(){
        Sentence sentence=new Sentence();
        SentenceDto dto=new SentenceDto();

        dto.setWordInSentences(sentence.getWordInSentences().stream().map(this.wordInSentenceMapper::toDto).toList());
    }*/

    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public  abstract void toUpdateFromDto(@MappingTarget Sentence sentence, SentenceDto dto);
}
