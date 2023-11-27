package com.example.izohlilugat.service;

import com.example.izohlilugat.dto.DayWordDto;
import com.example.izohlilugat.dto.ErrorDto;
import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.model.DayWord;
import com.example.izohlilugat.repository.DayWordRepository;
import com.example.izohlilugat.service.mapper.DayWordMapper;
import com.example.izohlilugat.service.validation.DayWordValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DayWordService {

    private final DayWordRepository dayWordRepository;
    private  final DayWordMapper dayWordMapper;

    private final DayWordValidation validation;


    public ResponseDto<DayWordDto> create(DayWordDto dto) {
        List<ErrorDto> errors=this.validation.validate(dto);
        if (!errors.isEmpty()){
            return ResponseDto.<DayWordDto>builder()
                    .code(-3)
                    .message("error")
                    .errors(errors)
                    .build();
        }

        try {
            DayWord word=this.dayWordMapper.toEntity(dto);
            word.setCreatedAt(LocalDateTime.now());
            this.dayWordRepository.save(word);
            return ResponseDto.<DayWordDto>builder()
                    .success(true)
                    .message("ok")
                    .data(this.dayWordMapper.toDto(word))
                    .build();
        }catch (Exception e){

            return ResponseDto.<DayWordDto>builder()
                    .code(-1)
                    .message(String.format("while is error %s :: saving",e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<DayWordDto> get(Integer wordId) {
        return this.dayWordRepository.findByIdAndDeletedAtIsNull(wordId)
                .map(word -> {
                    return ResponseDto.<DayWordDto>builder()
                            .success(true)
                            .message("ok")
                            .data(this.dayWordMapper.toDto(word))
                            .build();
                }).orElse(ResponseDto.<DayWordDto>builder()
                        .code(-1)
                        .message(String.format("not found of %d :: id",wordId))
                        .build());
    }

    public ResponseDto<DayWordDto> update(DayWordDto dto, Integer wordId) {
        List<ErrorDto> errors=this.validation.validate(dto);
        if (!errors.isEmpty()){
            return ResponseDto.<DayWordDto>builder()
                    .code(-3)
                    .message("error")
                    .errors(errors)
                    .build();
        }

        try {
            return this.dayWordRepository.findByIdAndDeletedAtIsNull(wordId)
                    .map(word -> {
                        this.dayWordMapper.toUpdateFromDto(word,dto);
                        word.setUpdatedAt(LocalDateTime.now());
                        this.dayWordRepository.save(word);
                        return ResponseDto.<DayWordDto>builder()
                                .success(true)
                                .message("ok")
                                .data(this.dayWordMapper.toDto(word))
                                .build();


                    }).orElse(ResponseDto.<DayWordDto>builder()
                            .code(-1)
                            .message(String.format("not found of %d :: id",wordId))
                            .build());
        }catch (Exception e){

            return ResponseDto.<DayWordDto>builder()
                    .code(-1)
                    .message(String.format("while is error %s :: updating",e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<DayWordDto> delete(Integer wordId) {
        return this.dayWordRepository.findByIdAndDeletedAtIsNull(wordId)
                .map(word -> {
                    word.setDeletedAt(LocalDateTime.now());
                    return ResponseDto.<DayWordDto>builder()
                            .success(true)
                            .message("ok")
                            .data(this.dayWordMapper.toDto(word))
                            .build();


                }).orElse(ResponseDto.<DayWordDto>builder()
                        .code(-1)
                        .message(String.format("not found of %d :: id",wordId))
                        .build());
    }

    public ResponseDto<List<DayWordDto>> getAll() {

        List<DayWord> list=new ArrayList<>();
        if (list.isEmpty()){
            return ResponseDto.<List<DayWordDto>>builder()
                    .code(-1)
                    .message("error")
                    .build();
        }
        return ResponseDto.<List<DayWordDto>>builder()
                .success(true)
                .message("ok")
                .data(list.stream().map(this.dayWordMapper::toDto).toList())
                .build();
    }

    public ResponseDto<Page<DayWordDto>> getAllPage(Integer page, Integer size) {
        Page<DayWord> wordPage=this.dayWordRepository.findAllByDeletedAtIsNull(PageRequest.of(page,size));
        if (wordPage.isEmpty()){
            return ResponseDto.<Page<DayWordDto>>builder()
                    .code(-1)
                    .message("error")
                    .build();
        }
        return ResponseDto.<Page<DayWordDto>>builder()
                .success(true)
                .message("ok")
                .data(wordPage.map(this.dayWordMapper::toDto))
                .build();
    }
}
