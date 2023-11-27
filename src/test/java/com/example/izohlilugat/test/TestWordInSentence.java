package com.example.izohlilugat.test;

import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.dto.WordInSentenceDto;
import com.example.izohlilugat.model.WordInSentence;
import com.example.izohlilugat.repository.WordInSentenceRepository;
import com.example.izohlilugat.service.WordInSentenceService;
import com.example.izohlilugat.service.mapper.WordInSentenceMapper;
import com.example.izohlilugat.service.validation.WordInSentenceValidation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class TestWordInSentence {
    private WordInSentenceMapper wordInSentenceMapper;
    private WordInSentenceValidation wordInSentenceValidation;
    private WordInSentenceService wordInSentenceService;
    private WordInSentenceRepository wordInSentenceRepository;

    @BeforeEach
    void init(){
        this.wordInSentenceMapper= Mockito.mock(WordInSentenceMapper.class);
        this.wordInSentenceValidation=Mockito.mock(WordInSentenceValidation.class);
        this.wordInSentenceRepository=Mockito.mock(WordInSentenceRepository.class);

        this.wordInSentenceService=new WordInSentenceService(wordInSentenceMapper,wordInSentenceRepository,wordInSentenceValidation);
    }

    @Test
    void createPositive(){
        when(this.wordInSentenceMapper.toEntity(any()))
                .thenReturn(WordInSentence.builder()
                        .wordId(1)
                        .id(3)
                        .build());

        when(this.wordInSentenceMapper.toDto(any()))
                .thenReturn(WordInSentenceDto.builder()
                        .wordId(7)
                        .id(7)
                        .build());

        ResponseDto<WordInSentenceDto> response=this.wordInSentenceService.create(any());
        Assertions.assertEquals(response.getData().getWordId(),7);
        Assertions.assertEquals(response.getData().getId(),7);
        Assertions.assertNotNull(response.getData());
        Assertions.assertTrue(response.isSuccess());

        verify(this.wordInSentenceMapper,times(1)).toDto(any());
        verify(this.wordInSentenceMapper,times(1)).toEntity(any());

    }

    @Test
    void createException(){
        when(this.wordInSentenceMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<WordInSentenceDto> response=this.wordInSentenceService.create(any());
        Assertions.assertNull(response.getData());
        Assertions.assertFalse(response.isSuccess());

    }
    @Test

    void getPositive(){
        when(this.wordInSentenceRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(WordInSentence.builder()
                                .id(1)
                                .wordId(1)
                        .build()));


        when(this.wordInSentenceMapper.toDto(any()))
                .thenReturn(WordInSentenceDto.builder()
                        .wordId(7)
                        .id(7)
                        .build());

        ResponseDto<WordInSentenceDto> response=this.wordInSentenceService.get(any());
        Assertions.assertEquals(response.getData().getWordId(),7);
        Assertions.assertEquals(response.getData().getId(),7);
        Assertions.assertNotNull(response.getData());
        Assertions.assertTrue(response.isSuccess());

        verify(this.wordInSentenceMapper,times(1)).toDto(any());
        verify(this.wordInSentenceRepository,times(1)).findByIdAndDeletedAtIsNull(any());

    }

    @Test
    void getNegative(){
        when(this.wordInSentenceRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());


        ResponseDto<WordInSentenceDto> response=this.wordInSentenceService.get(any());
        Assertions.assertNull(response.getData());
        Assertions.assertFalse(response.isSuccess());
    }

    @Test
    void updatePositive(){

        when(this.wordInSentenceRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(WordInSentence.builder()
                        .id(1)
                        .wordId(1)
                        .build()));


        when(this.wordInSentenceMapper.toDto(any()))
                .thenReturn(WordInSentenceDto.builder()
                        .wordId(7)
                        .id(7)
                        .build());

        ResponseDto<WordInSentenceDto> response=this.wordInSentenceService.update(new WordInSentenceDto(),any());
        Assertions.assertEquals(response.getData().getWordId(),7);
        Assertions.assertEquals(response.getData().getId(),7);
        Assertions.assertNotNull(response.getData());
        Assertions.assertTrue(response.isSuccess());

        verify(this.wordInSentenceMapper,times(1)).toDto(any());
        verify(this.wordInSentenceRepository,times(1)).findByIdAndDeletedAtIsNull(any());
    }
    @Test
    void updateNegative(){

        when(this.wordInSentenceMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<WordInSentenceDto> response=this.wordInSentenceService.update(new WordInSentenceDto(),any());
        Assertions.assertNull(response.getData());
        Assertions.assertFalse(response.isSuccess());
    }
    @Test
    void deletePositive(){

        when(this.wordInSentenceRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(WordInSentence.builder()
                        .id(1)
                        .wordId(1)
                        .build()));


        when(this.wordInSentenceMapper.toDto(any()))
                .thenReturn(WordInSentenceDto.builder()
                        .wordId(7)
                        .id(7)
                        .build());

        ResponseDto<WordInSentenceDto> response=this.wordInSentenceService.delete(any());
        Assertions.assertEquals(response.getData().getWordId(),7);
        Assertions.assertEquals(response.getData().getId(),7);
        Assertions.assertNotNull(response.getData());
        Assertions.assertTrue(response.isSuccess());

        verify(this.wordInSentenceMapper,times(1)).toDto(any());
        verify(this.wordInSentenceRepository,times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void deleteNegative(){

        when(this.wordInSentenceRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());


        ResponseDto<WordInSentenceDto> response=this.wordInSentenceService.delete(any());
        Assertions.assertNull(response.getData());
        Assertions.assertFalse(response.isSuccess());
    }
}
