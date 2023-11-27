package com.example.izohlilugat.service;

import com.example.izohlilugat.dto.ErrorDto;
import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.dto.WordDto;
import com.example.izohlilugat.model.Word;
import com.example.izohlilugat.repository.WordRepository;
import com.example.izohlilugat.service.mapper.WordMapper;
import com.example.izohlilugat.service.validation.WordValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WordService {

    private final WordMapper wordMapper;
    private final WordRepository wordRepository;
    private final WordValidation validation;


    public ResponseDto<WordDto> create(WordDto dto) {

        List<ErrorDto> errors=this.validation.validate(dto);
        if (!errors.isEmpty()){
            return ResponseDto.<WordDto>builder()
                    .code(-3)
                    .message("error")
                    .errors(errors)
                    .build();
        }

        try {
            Word word=this.wordMapper.toEntity(dto);
            word.setCreatedAt(LocalDateTime.now());
            this.wordRepository.save(word);
            return ResponseDto.<WordDto>builder()
                    .success(true)
                    .message("ok")
                    .data(this.wordMapper.toDto(word))
                    .build();
        }catch (Exception e){

            return ResponseDto.<WordDto>builder()
                    .code(-1)
                    .message(String.format("while is error %s :: saving",e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<WordDto> get(Integer wordId) {
        return this.wordRepository.findByIdAndDeletedAtIsNull(wordId)
                .map(word -> {
                    return ResponseDto.<WordDto>builder()
                            .success(true)
                            .message("ok")
                            .data(this.wordMapper.toDto(word))
                            .build();
                }).orElse(ResponseDto.<WordDto>builder()
                        .code(-1)
                        .message(String.format("not found of %d :: id",wordId))
                        .build());
    }

    public ResponseDto<WordDto> update(WordDto dto, Integer wordId) {

        List<ErrorDto> errors=this.validation.validate(dto);
        if (!errors.isEmpty()){
            return ResponseDto.<WordDto>builder()
                    .code(-3)
                    .message("error")
                    .errors(errors)
                    .build();
        }

       try {
           return this.wordRepository.findByIdAndDeletedAtIsNull(wordId)
                   .map(word -> {
                       this.wordMapper.toUpdateFromDto(word,dto);
                       word.setUpdatedAt(LocalDateTime.now());
                       this.wordRepository.save(word);
                       return ResponseDto.<WordDto>builder()
                               .success(true)
                               .message("ok")
                               .data(this.wordMapper.toDto(word))
                               .build();


                   }).orElse(ResponseDto.<WordDto>builder()
                           .code(-1)
                           .message(String.format("not found of %d :: id",wordId))
                           .build());
       }catch (Exception e){

           return ResponseDto.<WordDto>builder()
                   .code(-1)
                   .message(String.format("while is error %s :: updating",e.getMessage()))
                   .build();
       }
    }

    public ResponseDto<WordDto> delete(Integer wordId) {
        return this.wordRepository.findByIdAndDeletedAtIsNull(wordId)
                .map(word -> {
                    word.setDeletedAt(LocalDateTime.now());
                    return ResponseDto.<WordDto>builder()
                            .success(true)
                            .message("ok")
                            .data(this.wordMapper.toDto(word))
                            .build();


                }).orElse(ResponseDto.<WordDto>builder()
                        .code(-1)
                        .message(String.format("not found of %d :: id",wordId))
                        .build());
    }

    public ResponseDto<List<WordDto>> getAll() {

        List<Word> list=this.wordRepository.findAll();
        if (list.isEmpty()){
            return ResponseDto.<List<WordDto>>builder()
                    .code(-1)
                    .message("error")
                    .build();
        }
        return ResponseDto.<List<WordDto>>builder()
                .success(true)
                .message("ok")
                .data(list.stream().map(this.wordMapper::toDto).toList())
                .build();
    }

    public ResponseDto<Page<WordDto>> getAllPage(Integer page, Integer size) {
        Page<Word> wordPage=this.wordRepository.findAllByDeletedAtIsNull(PageRequest.of(page,size));
        if (wordPage.isEmpty()){
            return ResponseDto.<Page<WordDto>>builder()
                    .code(-1)
                    .message("error")
                    .build();
        }
        return ResponseDto.<Page<WordDto>>builder()
                .success(true)
                .message("ok")
                .data(wordPage.map(this.wordMapper::toDto))
                .build();
    }
}
