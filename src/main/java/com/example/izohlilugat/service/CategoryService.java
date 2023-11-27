package com.example.izohlilugat.service;

import com.example.izohlilugat.dto.CategoryDto;
import com.example.izohlilugat.dto.ErrorDto;
import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.model.Category;
import com.example.izohlilugat.repository.CategoryRepository;
import com.example.izohlilugat.service.mapper.CategoryMapper;
import com.example.izohlilugat.service.validation.CategoryValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    private final CategoryValidation validation;

    public ResponseDto<CategoryDto> create(CategoryDto dto) {
        List<ErrorDto> errors=this.validation.validate(dto);
        if (!errors.isEmpty()){
            return ResponseDto.<CategoryDto>builder()
                    .code(-3)
                    .message("error")
                    .errors(errors)
                    .build();
        }

        try {



            Category category=this.categoryMapper.toEntity(dto);
            category.setCreatedAt(LocalDateTime.now());
            this.categoryRepository.save(category);
            return ResponseDto.<CategoryDto>builder()
                    .success(true)
                    .message("ok")
                    .data(this.categoryMapper.toDto(category))
                    .build();
        }catch (Exception e){

            return ResponseDto.<CategoryDto>builder()
                    .code(-1)
                    .message(String.format("while is error %s :: saving",e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<CategoryDto> get(Integer categoryId) {
        return this.categoryRepository.findByIdAndDeletedAtIsNull(categoryId)
                .map(category -> {
                    return ResponseDto.<CategoryDto>builder()
                            .success(true)
                            .message("ok")
                            .data(this.categoryMapper.toDto(category))
                            .build();
                }).orElse(ResponseDto.<CategoryDto>builder()
                        .code(-1)
                        .message(String.format("not found of %d :: id",categoryId))
                        .build());
    }

    public ResponseDto<CategoryDto> update(CategoryDto dto, Integer categoryId) {
        List<ErrorDto> errors=this.validation.validate(dto);
        if (!errors.isEmpty()){
            return ResponseDto.<CategoryDto>builder()
                    .code(-3)
                    .message("error")
                    .errors(errors)
                    .build();
        }

        try {
            return this.categoryRepository.findByIdAndDeletedAtIsNull(categoryId)
                    .map(category -> {
                        this.categoryMapper.toUpdateFromDto(category,dto);
                        category.setUpdatedAt(LocalDateTime.now());
                        this.categoryRepository.save(category);
                        return ResponseDto.<CategoryDto>builder()
                                .success(true)
                                .message("ok")
                                .data(this.categoryMapper.toDto(category))
                                .build();


                    }).orElse(ResponseDto.<CategoryDto>builder()
                            .code(-1)
                            .message(String.format("not found of %d :: id",categoryId))
                            .build());
        }catch (Exception e){

            return ResponseDto.<CategoryDto>builder()
                    .code(-1)
                    .message(String.format("while is error %s :: updating",e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<CategoryDto> delete(Integer categoryId) {
        return this.categoryRepository.findByIdAndDeletedAtIsNull(categoryId)
                .map(category -> {
                    category.setDeletedAt(LocalDateTime.now());
                    return ResponseDto.<CategoryDto>builder()
                            .success(true)
                            .message("ok")
                            .data(this.categoryMapper.toDto(category))
                            .build();


                }).orElse(ResponseDto.<CategoryDto>builder()
                        .code(-1)
                        .message(String.format("not found of %d :: id",categoryId))
                        .build());
    }

    public ResponseDto<List<CategoryDto>> getAll() {

        List<Category> list=new ArrayList<>();
        if (list.isEmpty()){
            return ResponseDto.<List<CategoryDto>>builder()
                    .code(-1)
                    .message("error")
                    .build();
        }
        return ResponseDto.<List<CategoryDto>>builder()
                .success(true)
                .message("ok")
                .data(list.stream().map(this.categoryMapper::toDto).toList())
                .build();
    }

    public ResponseDto<Page<CategoryDto>> getAllPage(Integer page, Integer size) {
        Page<Category> categoryPage=this.categoryRepository.findAllByDeletedAtIsNull(PageRequest.of(page,size));
        if (categoryPage.isEmpty()){
            return ResponseDto.<Page<CategoryDto>>builder()
                    .code(-1)
                    .message("error")
                    .build();
        }
        return ResponseDto.<Page<CategoryDto>>builder()
                .success(true)
                .message("ok")
                .data(categoryPage.map(this.categoryMapper::toDto))
                .build();
    }
}
