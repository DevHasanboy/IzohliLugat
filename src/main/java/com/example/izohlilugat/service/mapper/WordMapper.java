package com.example.izohlilugat.service.mapper;


import com.example.izohlilugat.dto.WordDto;
import com.example.izohlilugat.model.Word;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public  abstract class WordMapper {

    @Autowired
    protected NoteMapper noteMapper;

    @Autowired
    protected WordTypeMapper wordTypeMapper;


    @Autowired
    protected WordInSentenceMapper wordInSentenceMapper;

    @Autowired
    protected DayWordMapper dayWordMapper;

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public  abstract Word toEntity(WordDto dto);

    public abstract WordDto toDto(Word word);




    @Mapping(target = "notes",expression = "java(word.getNotes().stream().map(this.noteMapper::toDto).toList())")
    @Mapping(target = "dayWords" , expression = "java(word.getDayWords().stream().map(this.dayWordMapper::toDto).toList())")
    @Mapping(target = "wordInSentences", expression = "java(word.getWordInSentences().stream().map(this.wordInSentenceMapper::toDto).toList())")
    @Mapping(target = "wordTypes",expression = "java(word.getWordTypes().stream().map(this.wordTypeMapper::toDto).toList())")
    public abstract  WordDto toDtoWithAll (Word word);

    /*
    @Mapping(target = "notes",expression = "java(word.getNotes().stream().map(this.noteMapper::toDto).toList())")
    public abstract  WordDto toDtoWithNotes (Word word);*/
/*
    @Mapping(target = "dayWords" , expression = "java(word.getDayWords().stream().map(this.dayWordMapper::toDto).toList())")
    public abstract  WordDto toDtoWithDayWord (Word word);*/

/*    @Mapping(target = "wordInSentences", expression = "java(word.getWordInSentences().stream().map(this.wordInSentenceMapper::toDto).toList())")
    public abstract  WordDto toDtoWithWordIinSentence (Word word);*/

  /*  public void terd(){
        Word word=new Word();
        WordDto dto=new WordDto();
        dto.setWordInSentences(word.getWordInSentences().stream().map(this.wordInSentenceMapper::toDto).toList());
    }*/

    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    public  abstract void toUpdateFromDto(@MappingTarget Word word, WordDto dto);
}
