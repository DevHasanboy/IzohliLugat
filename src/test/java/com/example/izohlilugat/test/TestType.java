package com.example.izohlilugat.test;

import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.dto.TypeDto;
import com.example.izohlilugat.model.Type;
import com.example.izohlilugat.repository.TypeRepository;
import com.example.izohlilugat.service.TypeService;
import com.example.izohlilugat.service.mapper.TypeMapper;
import com.example.izohlilugat.service.validation.TypeValidation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class TestType {
    private TypeRepository typeRepository;
    private TypeMapper typeMapper;
    private TypeService typeService;
    private TypeValidation typeValidation;

    @BeforeEach
    void init(){
        this.typeMapper=Mockito.mock(TypeMapper.class);
        this.typeRepository=Mockito.mock(TypeRepository.class);
        this.typeValidation=Mockito.mock(TypeValidation.class);

        this.typeService=new TypeService(typeRepository,typeMapper,typeValidation);

    }

    @Test
    void createPositive(){
        when(this.typeMapper.toEntity(any()))
                .thenReturn(Type.builder()
                        .name("hi")
                        .id(1)
                        .build());

        when(this.typeMapper.toDto(any()))
                .thenReturn(TypeDto.builder()
                        .name("spring")
                        .id(7)
                        .build());

        ResponseDto<TypeDto> response=this.typeService.create(any());
        Assertions.assertEquals(response.getData().getId(),7);
        Assertions.assertEquals(response.getData().getName(),"spring");

        Assertions.assertTrue(response.isSuccess());
        verify(this.typeMapper,times(1)).toDto(any());
        verify(this.typeMapper,times(1)).toEntity(any());
    }

    @Test
    void createException(){
        when(this.typeMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<TypeDto> response=this.typeService.create(any());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);
    }

    @Test
    void getPositive(){
        when(this.typeRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(Type.builder()
                                .id(1)
                                .name("name")
                                .build()));

        when(this.typeMapper.toDto(any()))
                .thenReturn(TypeDto.builder()
                        .name("spring")
                        .id(7)
                        .build());

        ResponseDto<TypeDto> response=this.typeService.get(any());
        Assertions.assertEquals(response.getData().getId(),7);
        Assertions.assertEquals(response.getData().getName(),"spring");

        Assertions.assertTrue(response.isSuccess());
        verify(this.typeMapper,times(1)).toDto(any());
        verify(this.typeRepository,times(1)).findByIdAndDeletedAtIsNull(any());

    }
    @Test
    void getNegative(){
        when(this.typeRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<TypeDto> response=this.typeService.get(any());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);
    }

    @Test
    void updatePositive(){
        when(this.typeRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(Type.builder()
                        .id(1)
                        .name("name")
                        .build()));

        when(this.typeMapper.toDto(any()))
                .thenReturn(TypeDto.builder()
                        .name("spring")
                        .id(7)
                        .build());

        ResponseDto<TypeDto> response=this.typeService.update(new TypeDto(),any());
        Assertions.assertEquals(response.getData().getId(),7);
        Assertions.assertEquals(response.getData().getName(),"spring");

        Assertions.assertTrue(response.isSuccess());
        verify(this.typeMapper,times(1)).toDto(any());
        verify(this.typeRepository,times(1)).findByIdAndDeletedAtIsNull(any());
    }
    @Test
    void updateNegative(){

        when(this.typeRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());


        ResponseDto<TypeDto> response=this.typeService.update(new TypeDto(),any());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);
    }

    @Test
    void updateException(){

        when(this.typeMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<TypeDto> response=this.typeService.create(any());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);
    }

    @Test
    void deletePositive(){

        when(this.typeRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(Type.builder()
                        .id(1)
                        .name("name")
                        .build()));

        when(this.typeMapper.toDto(any()))
                .thenReturn(TypeDto.builder()
                        .name("spring")
                        .id(7)
                        .build());

        ResponseDto<TypeDto> response=this.typeService.delete(any());
        Assertions.assertEquals(response.getData().getId(),7);
        Assertions.assertEquals(response.getData().getName(),"spring");

        Assertions.assertTrue(response.isSuccess());
        verify(this.typeMapper,times(1)).toDto(any());
        verify(this.typeRepository,times(1)).findByIdAndDeletedAtIsNull(any());
    }
    @Test
    void deleteNegative(){
        when(this.typeRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());


        ResponseDto<TypeDto> response=this.typeService.delete(any());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);
    }
}
