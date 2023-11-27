package com.example.izohlilugat.service;

import com.example.izohlilugat.dto.ErrorDto;
import com.example.izohlilugat.dto.NoteDto;
import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.model.Note;
import com.example.izohlilugat.repository.NoteRepository;
import com.example.izohlilugat.service.mapper.NoteMapper;
import com.example.izohlilugat.service.validation.NoteValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;
    private final NoteValidation validation;


    public ResponseDto<NoteDto> create(NoteDto dto) {
        List<ErrorDto> errors=this.validation.validate(dto);
        if (!errors.isEmpty()){
            return ResponseDto.<NoteDto>builder()
                    .code(-3)
                    .message("error")
                    .errors(errors)
                    .build();
        }

        try {
            Note word=this.noteMapper.toEntity(dto);
            word.setCreatedAt(LocalDateTime.now());
            this.noteRepository.save(word);
            return ResponseDto.<NoteDto>builder()
                    .success(true)
                    .message("ok")
                    .data(this.noteMapper.toDto(word))
                    .build();
        }catch (Exception e){

            return ResponseDto.<NoteDto>builder()
                    .code(-1)
                    .message(String.format("while is error %s :: saving",e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<NoteDto> get(Integer noteId) {
        return this.noteRepository.findByIdAndDeletedAtIsNull(noteId)
                .map(word -> {
                    return ResponseDto.<NoteDto>builder()
                            .success(true)
                            .message("ok")
                            .data(this.noteMapper.toDto(word))
                            .build();
                }).orElse(ResponseDto.<NoteDto>builder()
                        .code(-1)
                        .message(String.format("not found of %d :: id",noteId))
                        .build());
    }

    public ResponseDto<NoteDto> update(NoteDto dto, Integer noteId) {
        List<ErrorDto> errors=this.validation.validate(dto);
        if (!errors.isEmpty()){
            return ResponseDto.<NoteDto>builder()
                    .code(-3)
                    .message("error")
                    .errors(errors)
                    .build();
        }

        try {
            return this.noteRepository.findByIdAndDeletedAtIsNull(noteId)
                    .map(word -> {
                        this.noteMapper.toUpdateFromDto(word,dto);
                        word.setUpdatedAt(LocalDateTime.now());
                        this.noteRepository.save(word);
                        return ResponseDto.<NoteDto>builder()
                                .success(true)
                                .message("ok")
                                .data(this.noteMapper.toDto(word))
                                .build();


                    }).orElse(ResponseDto.<NoteDto>builder()
                            .code(-1)
                            .message(String.format("not found of %d :: id",noteId))
                            .build());
        }catch (Exception e){

            return ResponseDto.<NoteDto>builder()
                    .code(-1)
                    .message(String.format("while is error %s :: updating",e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<NoteDto> delete(Integer noteId) {
        return this.noteRepository.findByIdAndDeletedAtIsNull(noteId)
                .map(word -> {
                    word.setDeletedAt(LocalDateTime.now());
                    return ResponseDto.<NoteDto>builder()
                            .success(true)
                            .message("ok")
                            .data(this.noteMapper.toDto(word))
                            .build();


                }).orElse(ResponseDto.<NoteDto>builder()
                        .code(-1)
                        .message(String.format("not found of %d :: id",noteId))
                        .build());
    }

    public ResponseDto<List<NoteDto>> getAll() {

        List<Note> list=new ArrayList<>();
        if (list.isEmpty()){
            return ResponseDto.<List<NoteDto>>builder()
                    .code(-1)
                    .message("error")
                    .build();
        }
        return ResponseDto.<List<NoteDto>>builder()
                .success(true)
                .message("ok")
                .data(list.stream().map(this.noteMapper::toDto).toList())
                .build();
    }

    public ResponseDto<Page<NoteDto>> getAllPage(Integer page, Integer size) {
        Page<Note> wordPage=this.noteRepository.findAllByDeletedAtIsNull(PageRequest.of(page,size));
        if (wordPage.isEmpty()){
            return ResponseDto.<Page<NoteDto>>builder()
                    .code(-1)
                    .message("error")
                    .build();
        }
        return ResponseDto.<Page<NoteDto>>builder()
                .success(true)
                .message("ok")
                .data(wordPage.map(this.noteMapper::toDto))
                .build();
    }
}
