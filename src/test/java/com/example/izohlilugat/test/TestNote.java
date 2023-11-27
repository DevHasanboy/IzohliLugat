package com.example.izohlilugat.test;

import com.example.izohlilugat.dto.NoteDto;
import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.model.Note;
import com.example.izohlilugat.repository.NoteRepository;
import com.example.izohlilugat.service.NoteService;
import com.example.izohlilugat.service.mapper.NoteMapper;
import com.example.izohlilugat.service.validation.NoteValidation;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class TestNote {

    private NoteMapper noteMapper;
    private NoteService noteService;
    private NoteValidation noteValidation;
    private NoteRepository noteRepository;

    @BeforeEach
    void init(){

        this.noteMapper=Mockito.mock(NoteMapper.class);
        this.noteRepository=Mockito.mock(NoteRepository.class);
        this.noteValidation=Mockito.mock(NoteValidation.class);

        this.noteService=new NoteService(noteRepository,noteMapper,noteValidation);
    }

    @Test
    void createPositive(){
        when(this.noteMapper.toEntity(any()))
                .thenReturn(Note.builder()
                        .id(1)
                        .wordId(3)
                        .build());

        when(this.noteMapper.toDto(any()))
                .thenReturn(NoteDto.builder()
                        .id(8)
                        .wordId(9)
                        .build());
        ResponseDto<NoteDto> response=this.noteService.create(any());

        Assertions.assertNotNull(response.getData());
        Assertions.assertEquals(response.getData().getId(),8);
        Assertions.assertEquals(response.getData().getWordId(),9);
    }


    @Test
    void cretaeException(){
        when(this.noteMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);
        ResponseDto<NoteDto> response=this.noteService.create(any());

        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);
    }

    @Test
    void getPositive(){
        when(this.noteRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(Note.builder()
                                .wordId(1)
                                .id(5)
                                .build()));

        when(this.noteMapper.toDto(any()))
                .thenReturn(NoteDto.builder()
                        .id(8)
                        .wordId(9)
                        .build());
        ResponseDto<NoteDto> response=this.noteService.get(any());

        Assertions.assertNotNull(response.getData());
        Assertions.assertEquals(response.getData().getId(),8);
        Assertions.assertEquals(response.getData().getWordId(),9);
    }

    @Test
    void getNegative(){
        when(this.noteRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<NoteDto> response=this.noteService.get(any());

        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);
    }

    @Test
    void updatePositive(){
        when(this.noteRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(Note.builder()
                        .wordId(1)
                        .id(5)
                        .build()));

        when(this.noteMapper.toDto(any()))
                .thenReturn(NoteDto.builder()
                        .id(8)
                        .wordId(9)
                        .build());
        ResponseDto<NoteDto> response=this.noteService.update(new NoteDto(),any());

        Assertions.assertNotNull(response.getData());
        Assertions.assertEquals(response.getData().getId(),8);
        Assertions.assertEquals(response.getData().getWordId(),9);
    }
    @Test
    void updateNegative(){

        when(this.noteRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<NoteDto> response=this.noteService.update(new NoteDto(),any());

        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);
    }
    @Test
    void updateException(){
        when(this.noteMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<NoteDto> response=this.noteService.update(new NoteDto(),any());

        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);
    }

    @Test
    void deletePositive(){
        when(this.noteRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(Note.builder()
                        .wordId(1)
                        .id(5)
                        .build()));

        when(this.noteMapper.toDto(any()))
                .thenReturn(NoteDto.builder()
                        .id(8)
                        .wordId(9)
                        .build());
        ResponseDto<NoteDto> response=this.noteService.delete(any());

        Assertions.assertNotNull(response.getData());
        Assertions.assertEquals(response.getData().getId(),8);
        Assertions.assertEquals(response.getData().getWordId(),9);
    }
    @Test
    void deleteNegative(){
        when(this.noteRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<NoteDto> response=this.noteService.delete(any());

        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getCode(),-1);
    }
}
