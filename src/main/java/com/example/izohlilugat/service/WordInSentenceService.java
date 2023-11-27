package com.example.izohlilugat.service;


import com.example.izohlilugat.dto.ErrorDto;
import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.dto.WordInSentenceDto;
import com.example.izohlilugat.model.WordInSentence;
import com.example.izohlilugat.repository.WordInSentenceRepository;
import com.example.izohlilugat.service.mapper.WordInSentenceMapper;
import com.example.izohlilugat.service.validation.WordInSentenceValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WordInSentenceService {
    private final WordInSentenceMapper wordInSentenceMapper;
    private final WordInSentenceRepository wordInSentenceRepository;

    private final WordInSentenceValidation validation;


    public ResponseDto<WordInSentenceDto> create(WordInSentenceDto dto) {

        List<ErrorDto> errors=this.validation.validate(dto);
        if (!errors.isEmpty()){
            return ResponseDto.<WordInSentenceDto>builder()
                    .code(-3)
                    .message("error")
                    .errors(errors)
                    .build();
        }
        try {
            WordInSentence word=this.wordInSentenceMapper.toEntity(dto);
            word.setCreatedAt(LocalDateTime.now());
            this.wordInSentenceRepository.save(word);
            return ResponseDto.<WordInSentenceDto>builder()
                    .success(true)
                    .message("ok")
                    .data(this.wordInSentenceMapper.toDto(word))
                    .build();
        }catch (Exception e){

            return ResponseDto.<WordInSentenceDto>builder()
                    .code(-1)
                    .message(String.format("while is error %s :: saving",e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<WordInSentenceDto> get(Integer wordId) {
        return this.wordInSentenceRepository.findByIdAndDeletedAtIsNull(wordId)
                .map(word -> {
                    return ResponseDto.<WordInSentenceDto>builder()
                            .success(true)
                            .message("ok")
                            .data(this.wordInSentenceMapper.toDto(word))
                            .build();
                }).orElse(ResponseDto.<WordInSentenceDto>builder()
                        .code(-1)
                        .message(String.format("not found of %d :: id",wordId))
                        .build());
    }

    public ResponseDto<WordInSentenceDto> update(WordInSentenceDto dto, Integer wordId) {

        List<ErrorDto> errors=this.validation.validate(dto);
        if (!errors.isEmpty()){
            return ResponseDto.<WordInSentenceDto>builder()
                    .code(-3)
                    .message("error")
                    .errors(errors)
                    .build();
        }

        try {
            return this.wordInSentenceRepository.findByIdAndDeletedAtIsNull(wordId)
                    .map(word -> {
                        this.wordInSentenceMapper.toUpdateFromDto(word,dto);
                        word.setUpdatedAt(LocalDateTime.now());
                        this.wordInSentenceRepository.save(word);
                        return ResponseDto.<WordInSentenceDto>builder()
                                .success(true)
                                .message("ok")
                                .data(this.wordInSentenceMapper.toDto(word))
                                .build();


                    }).orElse(ResponseDto.<WordInSentenceDto>builder()
                            .code(-1)
                            .message(String.format("not found of %d :: id",wordId))
                            .build());
        }catch (Exception e){

            return ResponseDto.<WordInSentenceDto>builder()
                    .code(-1)
                    .message(String.format("while is error %s :: updating",e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<WordInSentenceDto> delete(Integer wordId) {
        return this.wordInSentenceRepository.findByIdAndDeletedAtIsNull(wordId)
                .map(word -> {
                    word.setDeletedAt(LocalDateTime.now());
                    return ResponseDto.<WordInSentenceDto>builder()
                            .success(true)
                            .message("ok")
                            .data(this.wordInSentenceMapper.toDto(word))
                            .build();


                }).orElse(ResponseDto.<WordInSentenceDto>builder()
                        .code(-1)
                        .message(String.format("not found of %d :: id",wordId))
                        .build());
    }

    public ResponseDto<List<WordInSentenceDto>> getAll() {

        List<WordInSentence> list=this.wordInSentenceRepository.findAll();
        if (list.isEmpty()){
            return ResponseDto.<List<WordInSentenceDto>>builder()
                    .code(-1)
                    .message("error")
                    .build();
        }
        return ResponseDto.<List<WordInSentenceDto>>builder()
                .success(true)
                .message("ok")
                .data(list.stream().map(this.wordInSentenceMapper::toDto).toList())
                .build();
    }

    public ResponseDto<Page<WordInSentenceDto>> getAllPage(Integer page, Integer size) {
        Page<WordInSentence> wordPage=this.wordInSentenceRepository.findAllByDeletedAtIsNull(PageRequest.of(page,size));
        if (wordPage.isEmpty()){
            return ResponseDto.<Page<WordInSentenceDto>>builder()
                    .code(-1)
                    .message("error")
                    .build();
        }
        return ResponseDto.<Page<WordInSentenceDto>>builder()
                .success(true)
                .message("ok")
                .data(wordPage.map(this.wordInSentenceMapper::toDto))
                .build();
    }
}
