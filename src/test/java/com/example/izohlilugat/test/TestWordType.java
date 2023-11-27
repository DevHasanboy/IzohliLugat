package com.example.izohlilugat.test;

import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.dto.WordTypeDto;
import com.example.izohlilugat.model.WordType;
import com.example.izohlilugat.repository.WordTypeRepository;
import com.example.izohlilugat.service.WordTypeService;
import com.example.izohlilugat.service.mapper.WordTypeMapper;
import com.example.izohlilugat.service.validation.WordTypeValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class TestWordType {
    private WordTypeMapper wordTypeMapper;
    private WordTypeService wordTypeService;
    private WordTypeValidation wordTypeValidation;
    private WordTypeRepository wordTypeRepository;


    @BeforeEach
    void init(){

        this.wordTypeMapper= Mockito.mock(WordTypeMapper.class);
        this.wordTypeRepository=Mockito.mock(WordTypeRepository.class);
        this.wordTypeValidation=Mockito.mock(WordTypeValidation.class);

        this.wordTypeService=new WordTypeService(wordTypeMapper,wordTypeRepository,wordTypeValidation);
    }

    @Test
    void cretaePositive(){
        when(this.wordTypeMapper.toEntity(any()))
                .thenReturn(WordType.builder()
                        .wordId(1)
                        .id(4)
                        .build());
        when(this.wordTypeMapper.toDto(any()))
                .thenReturn(WordTypeDto.builder()
                        .wordId(7)
                        .id(7)
                        .build());
        ResponseDto<WordTypeDto> response=this.wordTypeService.create(any());
        Assertions.assertEquals(response.getData().getWordId(),7);
        Assertions.assertEquals(response.getData().getId(),7);

        verify(this.wordTypeMapper,times(1)).toDto(any());
        verify(this.wordTypeMapper,times(1)).toEntity(any());
    }
    @Test
    void createException(){
        when(this.wordTypeMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<WordTypeDto> response=this.wordTypeService.create(any());
        Assertions.assertEquals(response.getCode(),-1);
        Assertions.assertNull(response.getData());

    }

    @Test
    void getpositive(){

        when(this.wordTypeRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(WordType.builder()
                                .wordId(1)
                                .id(1)
                               .build()));

        when(this.wordTypeMapper.toDto(any()))
                .thenReturn(WordTypeDto.builder()
                        .wordId(7)
                        .id(7)
                        .build());
        ResponseDto<WordTypeDto> response=this.wordTypeService.get(any());
        Assertions.assertEquals(response.getData().getWordId(),7);
        Assertions.assertEquals(response.getData().getId(),7);

        verify(this.wordTypeMapper,times(1)).toDto(any());
        verify(this.wordTypeRepository,times(1)).findByIdAndDeletedAtIsNull(any());
    }
    @Test
    void getNegative(){
        when(this.wordTypeRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<WordTypeDto> response=this.wordTypeService.get(any());
        Assertions.assertEquals(response.getCode(),-1);
        Assertions.assertNull(response.getData());
    }

    @Test
    void updatePositive(){

        when(this.wordTypeRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(WordType.builder()
                        .wordId(1)
                        .id(1)
                        .build()));

        when(this.wordTypeMapper.toDto(any()))
                .thenReturn(WordTypeDto.builder()
                        .wordId(7)
                        .id(7)
                        .build());
        ResponseDto<WordTypeDto> response=this.wordTypeService.update(new WordTypeDto(),any());
        Assertions.assertEquals(response.getData().getWordId(),7);
        Assertions.assertEquals(response.getData().getId(),7);

        verify(this.wordTypeMapper,times(1)).toDto(any());
        verify(this.wordTypeRepository,times(1)).findByIdAndDeletedAtIsNull(any());
    }
    @Test
    void updateNegative(){

        when(this.wordTypeRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());
        ResponseDto<WordTypeDto> response=this.wordTypeService.update(new WordTypeDto(),any());
        Assertions.assertEquals(response.getCode(),-1);
        Assertions.assertNull(response.getData());
    }
    @Test
    void updateException(){
        when(this.wordTypeMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);
        ResponseDto<WordTypeDto> response=this.wordTypeService.update(new WordTypeDto(),any());
        Assertions.assertEquals(response.getCode(),-1);
        Assertions.assertNull(response.getData());
    }

    @Test
    void deletePositive(){

        when(this.wordTypeRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(WordType.builder()
                        .wordId(1)
                        .id(1)
                        .build()));

        when(this.wordTypeMapper.toDto(any()))
                .thenReturn(WordTypeDto.builder()
                        .wordId(7)
                        .id(7)
                        .build());
        ResponseDto<WordTypeDto> response=this.wordTypeService.delete(any());
        Assertions.assertEquals(response.getData().getWordId(),7);
        Assertions.assertEquals(response.getData().getId(),7);

        verify(this.wordTypeMapper,times(1)).toDto(any());
        verify(this.wordTypeRepository,times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void deleteNegative(){

        when(this.wordTypeRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());
        ResponseDto<WordTypeDto> response=this.wordTypeService.delete(any());
        Assertions.assertEquals(response.getCode(),-1);
        Assertions.assertNull(response.getData());
    }
}
