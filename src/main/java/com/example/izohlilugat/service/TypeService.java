package com.example.izohlilugat.service;


import com.example.izohlilugat.dto.ErrorDto;
import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.dto.TypeDto;
import com.example.izohlilugat.model.Type;
import com.example.izohlilugat.repository.TypeRepository;
import com.example.izohlilugat.service.mapper.TypeMapper;
import com.example.izohlilugat.service.validation.TypeValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeService {

    private final TypeRepository typeRepository;
    private final TypeMapper typeMapper;
    private final TypeValidation validation;


    public ResponseDto<TypeDto> create(TypeDto dto) {
        List<ErrorDto> errors=this.validation.validate(dto);
        if (!errors.isEmpty()){
            return ResponseDto.<TypeDto>builder()
                    .code(-3)
                    .message("error")
                    .errors(errors)
                    .build();
        }

        try {
            Type word=this.typeMapper.toEntity(dto);
            word.setCreatedAt(LocalDateTime.now());
            this.typeRepository.save(word);
            return ResponseDto.<TypeDto>builder()
                    .success(true)
                    .message("ok")
                    .data(this.typeMapper.toDto(word))
                    .build();
        }catch (Exception e){

            return ResponseDto.<TypeDto>builder()
                    .code(-1)
                    .message(String.format("while is error %s :: saving",e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<TypeDto> get(Integer typeId) {
        return this.typeRepository.findByIdAndDeletedAtIsNull(typeId)
                .map(word -> {
                    return ResponseDto.<TypeDto>builder()
                            .success(true)
                            .message("ok")
                            .data(this.typeMapper.toDto(word))
                            .build();
                }).orElse(ResponseDto.<TypeDto>builder()
                        .code(-1)
                        .message(String.format("not found of %d :: id",typeId))
                        .build());
    }

    public ResponseDto<TypeDto> update(TypeDto dto, Integer typeId) {

        List<ErrorDto> errors=this.validation.validate(dto);
        if (!errors.isEmpty()){
            return ResponseDto.<TypeDto>builder()
                    .code(-3)
                    .message("error")
                    .errors(errors)
                    .build();
        }

        try {
            return this.typeRepository.findByIdAndDeletedAtIsNull(typeId)
                    .map(word -> {
                        this.typeMapper.toUpdateFromDto(word,dto);
                        word.setUpdatedAt(LocalDateTime.now());
                        this.typeRepository.save(word);
                        return ResponseDto.<TypeDto>builder()
                                .success(true)
                                .message("ok")
                                .data(this.typeMapper.toDto(word))
                                .build();


                    }).orElse(ResponseDto.<TypeDto>builder()
                            .code(-1)
                            .message(String.format("not found of %d :: id",typeId))
                            .build());
        }catch (Exception e){

            return ResponseDto.<TypeDto>builder()
                    .code(-1)
                    .message(String.format("while is error %s :: updating",e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<TypeDto> delete(Integer typeId) {
        return this.typeRepository.findByIdAndDeletedAtIsNull(typeId)
                .map(word -> {
                    word.setDeletedAt(LocalDateTime.now());
                    return ResponseDto.<TypeDto>builder()
                            .success(true)
                            .message("ok")
                            .data(this.typeMapper.toDto(word))
                            .build();


                }).orElse(ResponseDto.<TypeDto>builder()
                        .code(-1)
                        .message(String.format("not found of %d :: id",typeId))
                        .build());
    }

    public ResponseDto<List<TypeDto>> getAll() {

        List<Type> list=new ArrayList<>();
        if (list.isEmpty()){
            return ResponseDto.<List<TypeDto>>builder()
                    .code(-1)
                    .message("error")
                    .build();
        }
        return ResponseDto.<List<TypeDto>>builder()
                .success(true)
                .message("ok")
                .data(list.stream().map(this.typeMapper::toDto).toList())
                .build();
    }

    public ResponseDto<Page<TypeDto>> getAllPage(Integer page, Integer size) {
        Page<Type> wordPage=this.typeRepository.findAllByDeletedAtIsNull(PageRequest.of(page,size));
        if (wordPage.isEmpty()){
            return ResponseDto.<Page<TypeDto>>builder()
                    .code(-1)
                    .message("error")
                    .build();
        }
        return ResponseDto.<Page<TypeDto>>builder()
                .success(true)
                .message("ok")
                .data(wordPage.map(this.typeMapper::toDto))
                .build();
    }
}
