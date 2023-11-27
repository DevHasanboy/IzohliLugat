package com.example.izohlilugat.service;


import com.example.izohlilugat.dto.ErrorDto;
import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.dto.SentenceDto;
import com.example.izohlilugat.model.Sentence;
import com.example.izohlilugat.repository.SentenceRepository;
import com.example.izohlilugat.service.mapper.SentenceMapper;
import com.example.izohlilugat.service.validation.SentenceValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SentenceService {

    private final SentenceMapper sentenceMapper;
    private final SentenceRepository sentenseRepository;
    private final SentenceValidation validation;

    public ResponseDto<SentenceDto> create(SentenceDto dto) {
        List<ErrorDto> errors=this.validation.validate(dto);
        if (!errors.isEmpty()){
            return ResponseDto.<SentenceDto>builder()
                    .code(-3)
                    .message("error")
                    .errors(errors)
                    .build();
        }

        try {
            Sentence word=this.sentenceMapper.toEntity(dto);
            word.setCreatedAt(LocalDateTime.now());
            this.sentenseRepository.save(word);
            return ResponseDto.<SentenceDto>builder()
                    .success(true)
                    .message("ok")
                    .data(this.sentenceMapper.toDto(word))
                    .build();
        }catch (Exception e){

            return ResponseDto.<SentenceDto>builder()
                    .code(-1)
                    .message(String.format("while is error %s :: saving",e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<SentenceDto> get(Integer sentenceId) {
        return this.sentenseRepository.findByIdAndDeletedAtIsNull(sentenceId)
                .map(sentence -> {
                    return ResponseDto.<SentenceDto>builder()
                            .success(true)
                            .message("ok")
                            .data(this.sentenceMapper.toDto(sentence))
                            .build();
                }).orElse(ResponseDto.<SentenceDto>builder()
                        .code(-1)
                        .message(String.format("not found of %d :: id",sentenceId))
                        .build());
    }

    public ResponseDto<SentenceDto> update(SentenceDto dto, Integer sentenceId) {
        List<ErrorDto> errors=this.validation.validate(dto);
        if (!errors.isEmpty()){
            return ResponseDto.<SentenceDto>builder()
                    .code(-3)
                    .message("error")
                    .errors(errors)
                    .build();
        }

        try {
            return this.sentenseRepository.findByIdAndDeletedAtIsNull(sentenceId)
                    .map(sentence -> {
                        this.sentenceMapper.toUpdateFromDto(sentence,dto);
                        sentence.setUpdatedAt(LocalDateTime.now());
                        this.sentenseRepository.save(sentence);
                        return ResponseDto.<SentenceDto>builder()
                                .success(true)
                                .message("ok")
                                .data(this.sentenceMapper.toDto(sentence))
                                .build();


                    }).orElse(ResponseDto.<SentenceDto>builder()
                            .code(-1)
                            .message(String.format("not found of %d :: id",sentenceId))
                            .build());
        }catch (Exception e){

            return ResponseDto.<SentenceDto>builder()
                    .code(-1)
                    .message(String.format("while is error %s :: updating",e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<SentenceDto> delete(Integer sentenceId) {
        return this.sentenseRepository.findByIdAndDeletedAtIsNull(sentenceId)
                .map(sentence -> {
                    sentence.setDeletedAt(LocalDateTime.now());
                    return ResponseDto.<SentenceDto>builder()
                            .success(true)
                            .message("ok")
                            .data(this.sentenceMapper.toDto(sentence))
                            .build();


                }).orElse(ResponseDto.<SentenceDto>builder()
                        .code(-1)
                        .message(String.format("not found of %d :: id",sentenceId))
                        .build());
    }

    public ResponseDto<List<SentenceDto>> getAll() {

        List<Sentence> list=this.sentenseRepository.findAll();
        if (list.isEmpty()){
            return ResponseDto.<List<SentenceDto>>builder()
                    .code(-1)
                    .message("error")
                    .build();
        }
        return ResponseDto.<List<SentenceDto>>builder()
                .success(true)
                .message("ok")
                .data(list.stream().map(this.sentenceMapper::toDto).toList())
                .build();
    }

    public ResponseDto<Page<SentenceDto>> getAllPage(Integer page, Integer size) {
        Page<Sentence> wordPage=this.sentenseRepository.findAllByDeletedAtIsNull(PageRequest.of(page,size));
        if (wordPage.isEmpty()){
            return ResponseDto.<Page<SentenceDto>>builder()
                    .code(-1)
                    .message("error")
                    .build();
        }
        return ResponseDto.<Page<SentenceDto>>builder()
                .success(true)
                .message("ok")
                .data(wordPage.map(this.sentenceMapper::toDto))
                .build();
    }
}
