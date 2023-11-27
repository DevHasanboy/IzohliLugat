package com.example.izohlilugat.test;

import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.dto.WordDto;
import com.example.izohlilugat.model.Word;
import com.example.izohlilugat.repository.WordRepository;
import com.example.izohlilugat.repository.WordTypeRepository;
import com.example.izohlilugat.service.WordService;
import com.example.izohlilugat.service.mapper.WordMapper;
import com.example.izohlilugat.service.validation.WordTypeValidation;
import com.example.izohlilugat.service.validation.WordValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestWord {

    private WordService wordService;
    private WordMapper wordMapper;
    private WordRepository wordRepository;
    private WordValidation wordValidation;

    @BeforeEach
    void init(){
        this.wordMapper= Mockito.mock(WordMapper.class);
        this.wordRepository=Mockito.mock(WordRepository.class);
        this.wordValidation=Mockito.mock(WordValidation.class);

        this.wordService=new WordService(wordMapper,wordRepository,wordValidation);
    }

    @Test
    void createPositive(){
        when(this.wordMapper.toEntity(any()))
                .thenReturn(Word.builder()
                        .id(1)
                        .audioId(2)
                        .build());

        when(this.wordMapper.toDto(any()))
                .thenReturn(WordDto.builder()
                        .audioId(7)
                        .id(7)
                        .build());
        ResponseDto<WordDto> response=this.wordService.create(any());
        Assertions.assertEquals(response.getData().getId(),7);
        Assertions.assertEquals(response.getData().getAudioId(),7);
        Assertions.assertNotNull(response.getData());
        Assertions.assertTrue(response.isSuccess());

        verify(this.wordMapper,times(1)).toDto(any());
        verify(this.wordMapper,times(1)).toEntity(any());
    }

    @Test
    void createException(){
        when(this.wordMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<WordDto> response=this.wordService.create(any());
        Assertions.assertNull(response.getData());
        Assertions.assertFalse(response.isSuccess());



    }
    @Test
    void getPositive(){
        when(this.wordRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(Word .builder()
                                .audioId(1)
                                .id(2)
                               .build()));

        when(this.wordMapper.toDto(any()))
                .thenReturn(WordDto.builder()
                        .audioId(7)
                        .id(7)
                        .build());
        ResponseDto<WordDto> response=this.wordService.get(any());
        Assertions.assertEquals(response.getData().getId(),7);
        Assertions.assertEquals(response.getData().getAudioId(),7);
        Assertions.assertNotNull(response.getData());
        Assertions.assertTrue(response.isSuccess());

        verify(this.wordMapper,times(1)).toDto(any());
        verify(this.wordRepository,times(1)).findByIdAndDeletedAtIsNull(any());

    }

    @Test
    void getNegative(){
        when(this.wordRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<WordDto> response=this.wordService.get(any());
        Assertions.assertNull(response.getData());
        Assertions.assertFalse(response.isSuccess());
    }

    @Test
    void updatePositive(){

        when(this.wordRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(Word .builder()
                        .audioId(1)
                        .id(2)
                        .build()));

        when(this.wordMapper.toDto(any()))
                .thenReturn(WordDto.builder()
                        .audioId(7)
                        .id(7)
                        .build());
        ResponseDto<WordDto> response=this.wordService.update( new WordDto(),any());
        Assertions.assertEquals(response.getData().getId(),7);
        Assertions.assertEquals(response.getData().getAudioId(),7);
        Assertions.assertNotNull(response.getData());
        Assertions.assertTrue(response.isSuccess());

        verify(this.wordMapper,times(1)).toDto(any());
        verify(this.wordRepository,times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void updateNegative(){

        when(this.wordRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<WordDto> response=this.wordService.update(new WordDto(),any());
        Assertions.assertNull(response.getData());
        Assertions.assertFalse(response.isSuccess());
    }

    @Test
    void updateException(){

        when(this.wordMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<WordDto> response=this.wordService.update(new WordDto(),any());
        Assertions.assertNull(response.getData());
        Assertions.assertFalse(response.isSuccess());
    }

    @Test
    void deletePositive(){

        when(this.wordRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(Word .builder()
                        .audioId(1)
                        .id(2)
                        .build()));

        when(this.wordMapper.toDto(any()))
                .thenReturn(WordDto.builder()
                        .audioId(7)
                        .id(7)
                        .build());
        ResponseDto<WordDto> response=this.wordService.delete( any());
        Assertions.assertEquals(response.getData().getId(),7);
        Assertions.assertEquals(response.getData().getAudioId(),7);
        Assertions.assertNotNull(response.getData());
        Assertions.assertTrue(response.isSuccess());

        verify(this.wordMapper,times(1)).toDto(any());
        verify(this.wordRepository,times(1)).findByIdAndDeletedAtIsNull(any());
    }
    @Test
    void deleteNegative(){

        when(this.wordRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<WordDto> response=this.wordService.delete(any());
        Assertions.assertNull(response.getData());
        Assertions.assertFalse(response.isSuccess());
    }
}
