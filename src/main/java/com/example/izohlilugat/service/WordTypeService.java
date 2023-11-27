package com.example.izohlilugat.service;

import com.example.izohlilugat.dto.ErrorDto;
import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.dto.WordTypeDto;
import com.example.izohlilugat.model.WordType;
import com.example.izohlilugat.repository.WordTypeRepository;
import com.example.izohlilugat.service.mapper.WordTypeMapper;
import com.example.izohlilugat.service.validation.WordTypeValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WordTypeService {

    private final WordTypeMapper wordTypeMapper;
    private final WordTypeRepository wordTypeRepository;
    private final WordTypeValidation validation;


    public ResponseDto<WordTypeDto> create(WordTypeDto dto) {

        List<ErrorDto> errors=this.validation.validate(dto);
        if (!errors.isEmpty()){
            return ResponseDto.<WordTypeDto>builder()
                    .code(-3)
                    .message("error")
                    .errors(errors)
                    .build();
        }

        try {
            WordType word=this.wordTypeMapper.toEntity(dto);
            word.setCreatedAt(LocalDateTime.now());
            this.wordTypeRepository.save(word);
            return ResponseDto.<WordTypeDto>builder()
                    .success(true)
                    .message("ok")
                    .data(this.wordTypeMapper.toDto(word))
                    .build();
        }catch (Exception e){

            return ResponseDto.<WordTypeDto>builder()
                    .code(-1)
                    .message(String.format("while is error %s :: saving",e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<WordTypeDto> get(Integer wordId) {
        return this.wordTypeRepository.findByIdAndDeletedAtIsNull(wordId)
                .map(word -> {
                    return ResponseDto.<WordTypeDto>builder()
                            .success(true)
                            .message("ok")
                            .data(this.wordTypeMapper.toDto(word))
                            .build();
                }).orElse(ResponseDto.<WordTypeDto>builder()
                        .code(-1)
                        .message(String.format("not found of %d :: id",wordId))
                        .build());
    }

    public ResponseDto<WordTypeDto> update(WordTypeDto dto, Integer wordId) {

        List<ErrorDto> errors=this.validation.validate(dto);
        if (!errors.isEmpty()){
            return ResponseDto.<WordTypeDto>builder()
                    .code(-3)
                    .message("error")
                    .errors(errors)
                    .build();
        }

        try {
            return this.wordTypeRepository.findByIdAndDeletedAtIsNull(wordId)
                    .map(wordType -> {
                        this.wordTypeMapper.toUpdateFromDto(wordType,dto);
                        wordType.setUpdatedAt(LocalDateTime.now());
                        this.wordTypeRepository.save(wordType);
                        return ResponseDto.<WordTypeDto>builder()
                                .success(true)
                                .message("ok")
                                .data(this.wordTypeMapper.toDto(wordType))
                                .build();


                    }).orElse(ResponseDto.<WordTypeDto>builder()
                            .code(-1)
                            .message(String.format("not found of %d :: id",wordId))
                            .build());
        }catch (Exception e){

            return ResponseDto.<WordTypeDto>builder()
                    .code(-1)
                    .message(String.format("while is error %s :: updating",e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<WordTypeDto> delete(Integer wordId) {
        return this.wordTypeRepository.findByIdAndDeletedAtIsNull(wordId)
                .map(word -> {
                    word.setDeletedAt(LocalDateTime.now());
                    return ResponseDto.<WordTypeDto>builder()
                            .success(true)
                            .message("ok")
                            .data(this.wordTypeMapper.toDto(word))
                            .build();


                }).orElse(ResponseDto.<WordTypeDto>builder()
                        .code(-1)
                        .message(String.format("not found of %d :: id",wordId))
                        .build());
    }

    public ResponseDto<List<WordTypeDto>> getAll() {

        List<WordType> list=this.wordTypeRepository.findAllBy();
        if (list.isEmpty()){
            return ResponseDto.<List<WordTypeDto>>builder()
                    .code(-1)
                    .message("error")
                    .build();
        }
        return ResponseDto.<List<WordTypeDto>>builder()
                .success(true)
                .message("ok")
                .data(list.stream().map(this.wordTypeMapper::toDto).toList())
                .build();
    }

    public ResponseDto<Page<WordTypeDto>> getAllPage(Integer page, Integer size) {
        Page<WordType> wordPage=this.wordTypeRepository.findAllByDeletedAtIsNull(PageRequest.of(page,size));
        if (wordPage.isEmpty()){
            return ResponseDto.<Page<WordTypeDto>>builder()
                    .code(-1)
                    .message("error")
                    .build();
        }
        return ResponseDto.<Page<WordTypeDto>>builder()
                .success(true)
                .message("ok")
                .data(wordPage.map(this.wordTypeMapper::toDto))
                .build();
    }
}
