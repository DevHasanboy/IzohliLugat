package com.example.izohlilugat.test;

import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.dto.SentenceDto;
import com.example.izohlilugat.model.Sentence;
import com.example.izohlilugat.repository.SentenceRepository;
import com.example.izohlilugat.service.SentenceService;
import com.example.izohlilugat.service.mapper.SentenceMapper;
import com.example.izohlilugat.service.validation.SentenceValidation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class testSentence {
    private SentenceValidation sentenceValidation;
    private SentenceService sentenceService;
    private SentenceMapper sentenceMapper;
    private SentenceRepository sentenceRepository;

    @BeforeEach
    void init(){
        this.sentenceMapper=Mockito.mock(SentenceMapper.class);
        this.sentenceRepository=Mockito.mock(SentenceRepository.class);
        this.sentenceValidation=Mockito.mock(SentenceValidation.class);

        this.sentenceService=new SentenceService(sentenceMapper,sentenceRepository,sentenceValidation);
    }

    @Test
    void createPositive(){
        when(this.sentenceMapper.toEntity(any()))
                .thenReturn(Sentence.builder()
                        .content("content")
                        .id(2)
                        .build());

        when(this.sentenceMapper.toDto(any()))
                .thenReturn(SentenceDto.builder()
                        .content("hello")
                        .id(5)
                        .build());

        ResponseDto<SentenceDto> response=this.sentenceService.create(any());
        Assertions.assertEquals(response.getData().getId(),5);
        Assertions.assertEquals(response.getData().getContent(),"hello");


        verify(this.sentenceMapper,times(1)).toDto(any());
        verify(this.sentenceMapper,times(1)).toEntity(any());
    }

    @Test
    void createexception(){
        when(this.sentenceMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<SentenceDto> response=this.sentenceService.create(any());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);
    }

    @Test
    void getPositive(){
        when(this.sentenceRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(Sentence.builder()
                                .id(2)
                                .content("content")
                                .build()));


        when(this.sentenceMapper.toDto(any()))
                .thenReturn(SentenceDto.builder()
                        .content("hello")
                        .id(5)
                        .build());

        ResponseDto<SentenceDto> response=this.sentenceService.get(any());
        Assertions.assertEquals(response.getData().getId(),5);
        Assertions.assertEquals(response.getData().getContent(),"hello");


        verify(this.sentenceMapper,times(1)).toDto(any());
        verify(this.sentenceRepository,times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void getNegative(){
        when(this.sentenceRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<SentenceDto> response=this.sentenceService.get(any());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);
    }

    @Test
    void updatePositive(){
        when(this.sentenceRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(Sentence.builder()
                        .id(2)
                        .content("content")
                        .build()));


        when(this.sentenceMapper.toDto(any()))
                .thenReturn(SentenceDto.builder()
                        .content("hello")
                        .id(5)
                        .build());

        ResponseDto<SentenceDto> response=this.sentenceService.update(new SentenceDto(),any());
        Assertions.assertEquals(response.getData().getId(),5);
        Assertions.assertEquals(response.getData().getContent(),"hello");


        verify(this.sentenceMapper,times(1)).toDto(any());
        verify(this.sentenceRepository,times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void updateNegative(){
        when(this.sentenceRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<SentenceDto> response=this.sentenceService.update(new SentenceDto(),any());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);
    }

    @Test
    void updateException(){
        when(this.sentenceMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<SentenceDto> response=this.sentenceService.update(new SentenceDto(),any());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);
    }

    @Test
    void deletePositive(){
        when(this.sentenceRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(Sentence.builder()
                        .id(2)
                        .content("content")
                        .build()));


        when(this.sentenceMapper.toDto(any()))
                .thenReturn(SentenceDto.builder()
                        .content("hello")
                        .id(5)
                        .build());

        ResponseDto<SentenceDto> response=this.sentenceService.delete(any());
        Assertions.assertEquals(response.getData().getId(),5);
        Assertions.assertEquals(response.getData().getContent(),"hello");


        verify(this.sentenceMapper,times(1)).toDto(any());
        verify(this.sentenceRepository,times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void deleteNegative(){

        when(this.sentenceRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<SentenceDto> response=this.sentenceService.delete(any());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);
    }
}
