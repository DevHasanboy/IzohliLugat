package com.example.izohlilugat.test;

import com.example.izohlilugat.dto.CategoryDto;
import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.model.Category;
import com.example.izohlilugat.repository.CategoryRepository;
import com.example.izohlilugat.service.CategoryService;
import com.example.izohlilugat.service.mapper.CategoryMapper;
import com.example.izohlilugat.service.validation.CategoryValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestCategory {
    private CategoryMapper categoryMapper;
    private CategoryService categoryService;
    private CategoryValidation categoryValidation;
    private CategoryRepository categoryRepository;


    @BeforeEach
    void init(){
        this.categoryMapper= Mockito.mock(CategoryMapper.class);
        this.categoryRepository=Mockito.mock(CategoryRepository.class);
        this.categoryValidation=Mockito.mock(CategoryValidation.class);

        this.categoryService=new CategoryService(categoryMapper,categoryRepository,categoryValidation);
    }

    @Test
    void CreatePositive(){

        when(this.categoryMapper.toEntity(any()))
                .thenReturn(Category.builder()
                        .id(1)
                        .description("hello")
                        .name("name")
                        .build());

        when(this.categoryMapper.toDto(any()))
                .thenReturn(CategoryDto.builder()
                        .id(3)
                        .description("hi")
                        .name("white")
                        .build());

        ResponseDto<CategoryDto> response=this.categoryService.create(any());
        Assertions.assertNotNull(response.getData());
        Assertions.assertEquals(response.getData().getId(),3);
        Assertions.assertEquals(response.getData().getName(),"white");
        Assertions.assertEquals(response.getData().getDescription(),"hi");

        verify(this.categoryMapper,times(1)).toDto(any());
        verify(this.categoryMapper,times(1)).toEntity(any());
    }

    @Test
    void createException() {
        when(this.categoryMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);
        ResponseDto<CategoryDto> response = this.categoryService.create(any());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);

    }

    @Test
    void getPositive(){
        when(this.categoryRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(Category.builder()
                                .name("name")
                                .description("description")
                                .build()));

        when(this.categoryMapper.toDto(any()))
                .thenReturn(CategoryDto.builder()
                        .id(3)
                        .description("hi")
                        .name("white")
                        .build());

        ResponseDto<CategoryDto> response=this.categoryService.get(any());
        Assertions.assertNotNull(response.getData());
        Assertions.assertEquals(response.getData().getId(),3);
        Assertions.assertEquals(response.getData().getName(),"white");
        Assertions.assertEquals(response.getData().getDescription(),"hi");

        verify(this.categoryMapper,times(1)).toDto(any());
        verify(this.categoryRepository,times(1)).findByIdAndDeletedAtIsNull(any());

    }
    @Test
    void getNegative(){
        when(this.categoryRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());
        ResponseDto<CategoryDto> response=this.categoryService.get(any());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);
    }

    @Test
    void updatePositive(){
        when(this.categoryRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(Category.builder()
                        .name("name")
                        .description("description")
                        .build()));


        when(this.categoryMapper.toDto(any()))
                .thenReturn(CategoryDto.builder()
                        .id(3)
                        .description("hi")
                        .name("white")
                        .build());

        ResponseDto<CategoryDto> response=this.categoryService.update( new CategoryDto(),any());
        Assertions.assertNotNull(response.getData());
        Assertions.assertEquals(response.getData().getId(),3);
        Assertions.assertEquals(response.getData().getName(),"white");
        Assertions.assertEquals(response.getData().getDescription(),"hi");

        verify(this.categoryMapper,times(1)).toDto(any());
        verify(this.categoryRepository,times(1)).findByIdAndDeletedAtIsNull(any());

    }

    @Test
    void updateException(){

        when(this.categoryMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);
        ResponseDto<CategoryDto> response=this.categoryService.update( new CategoryDto(),any());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);
    }
    @Test
    void updateNegative(){

        when(this.categoryRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<CategoryDto> response=this.categoryService.update( new CategoryDto(),any());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);

    }

    @Test
    void deletePositive(){

        when(this.categoryRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(Category.builder()
                        .name("name")
                        .description("description")
                        .build()));


        when(this.categoryMapper.toDto(any()))
                .thenReturn(CategoryDto.builder()
                        .id(3)
                        .description("hi")
                        .name("white")
                        .build());

        ResponseDto<CategoryDto> response=this.categoryService.delete(any());
        Assertions.assertNotNull(response.getData());
        Assertions.assertEquals(response.getData().getId(),3);
        Assertions.assertEquals(response.getData().getName(),"white");
        Assertions.assertEquals(response.getData().getDescription(),"hi");

        verify(this.categoryMapper,times(1)).toDto(any());
        verify(this.categoryRepository,times(1)).findByIdAndDeletedAtIsNull(any());

    }

    @Test
    void deleteNegative(){

        when(this.categoryRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());
        ResponseDto<CategoryDto> response=this.categoryService.delete(any());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);
    }
}
