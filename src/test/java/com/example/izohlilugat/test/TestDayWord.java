package com.example.izohlilugat.test;

import com.example.izohlilugat.dto.DayWordDto;
import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.model.DayWord;
import com.example.izohlilugat.repository.DayWordRepository;
import com.example.izohlilugat.service.DayWordService;
import com.example.izohlilugat.service.mapper.DayWordMapper;
import com.example.izohlilugat.service.validation.DayWordValidation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestDayWord {

    private DayWordMapper dayWordMapper;
    private DayWordService dayWordService;
    private DayWordValidation dayWordValidation;
    private DayWordRepository dayWordRepository;


    @BeforeEach
    void init(){

        this.dayWordMapper=Mockito.mock(DayWordMapper.class);
         this.dayWordRepository=Mockito.mock(DayWordRepository.class);
         this.dayWordValidation=Mockito.mock(DayWordValidation.class);
         this.dayWordService=new DayWordService(dayWordRepository,dayWordMapper,dayWordValidation);
    }

    @Test
    void createPpositive(){
        when(this.dayWordMapper.toEntity(any()))
                .thenReturn(DayWord.builder()
                        .wordId(4)
                        .id(6)
                        .build());



        when(this.dayWordMapper.toDto(any()))
                .thenReturn(DayWordDto.builder()
                        .wordId(1)
                        .id(3)
                        .build());

        ResponseDto<DayWordDto> response=this.dayWordService.create(any());
        Assertions.assertEquals(response.getData().getWordId(),1);
        Assertions.assertEquals(response.getData().getId(),3);
        Assertions.assertNotNull(response.getData());

        verify(this.dayWordMapper,times(1)).toDto(any());
        verify(this.dayWordMapper,times(1)).toEntity(any());
    }

    @Test
    void createException(){
        when(this.dayWordMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<DayWordDto> response=this.dayWordService.create(any());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);

    }

    @Test
    void getPositive(){
        when(this.dayWordRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(DayWord.builder()
                                .wordId(1)
                                .id(7)
                               .build()));

        when(this.dayWordMapper.toDto(any()))
                .thenReturn(DayWordDto.builder()
                        .wordId(1)
                        .id(3)
                        .build());

        ResponseDto<DayWordDto> response=this.dayWordService.get(any());
        Assertions.assertEquals(response.getData().getWordId(),1);
        Assertions.assertEquals(response.getData().getId(),3);
        Assertions.assertNotNull(response.getData());

        verify(this.dayWordMapper,times(1)).toDto(any());
        verify(this.dayWordRepository,times(1)).findByIdAndDeletedAtIsNull(any());

    }

    @Test
    void getNegative(){
        when(this.dayWordRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<DayWordDto> response=this.dayWordService.get(any());

        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);
    }

    @Test
    void updatePositive(){

        when(this.dayWordRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(DayWord.builder()
                        .wordId(1)
                        .id(7)
                        .build()));

        when(this.dayWordMapper.toDto(any()))
                .thenReturn(DayWordDto.builder()
                        .wordId(1)
                        .id(3)
                        .build());

        ResponseDto<DayWordDto> response=this.dayWordService.update(new DayWordDto(),any());
        Assertions.assertEquals(response.getData().getWordId(),1);
        Assertions.assertEquals(response.getData().getId(),3);
        Assertions.assertNotNull(response.getData());

        verify(this.dayWordMapper,times(1)).toDto(any());
        verify(this.dayWordRepository,times(1)).findByIdAndDeletedAtIsNull(any());


    }

    @Test
    void updateNegative(){

        when(this.dayWordRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());


        ResponseDto<DayWordDto> response=this.dayWordService.update(new DayWordDto(),any());

        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);
    }

    @Test
    void updateException(){
        when(this.dayWordMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<DayWordDto> response=this.dayWordService.update(new DayWordDto(),any());

        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);
    }

    @Test
    void deletePositive(){

        when(this.dayWordRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(DayWord.builder()
                        .wordId(1)
                        .id(7)
                        .build()));

        when(this.dayWordMapper.toDto(any()))
                .thenReturn(DayWordDto.builder()
                        .wordId(1)
                        .id(3)
                        .build());

        ResponseDto<DayWordDto> response=this.dayWordService.delete(any());
        Assertions.assertEquals(response.getData().getWordId(),1);
        Assertions.assertEquals(response.getData().getId(),3);
        Assertions.assertNotNull(response.getData());

        verify(this.dayWordMapper,times(1)).toDto(any());
        verify(this.dayWordRepository,times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void deleteNegative(){

        when(this.dayWordRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());


        ResponseDto<DayWordDto> response=this.dayWordService.delete(any());

        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);
    }
}
