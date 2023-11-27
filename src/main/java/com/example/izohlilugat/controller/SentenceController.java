package com.example.izohlilugat.controller;


import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.dto.SentenceDto;

import com.example.izohlilugat.service.SentenceService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("senten")
@RequiredArgsConstructor
public class SentenceController {

    private final SentenceService sentenceService;


    @Operation(
            tags = "create"
    )
    @PostMapping
    public ResponseDto<SentenceDto> create(@RequestBody @Valid SentenceDto dto){
        return this.sentenceService.create(dto);
    }

    @Operation(
            tags = "get"
    )
    @GetMapping("/{id}")
    public ResponseDto<SentenceDto> get(@PathVariable ("id") Integer sentenceId){
        return this.sentenceService.get(sentenceId);
    }

    @Operation(
            tags = "update"
    )
    @PutMapping("/{id}")
    public ResponseDto<SentenceDto> update(@RequestBody SentenceDto dto,@PathVariable ("id") Integer sentenceId){
        return this.sentenceService.update(dto,sentenceId);
    }

    @Operation(
            tags = "delete"
    )
    @DeleteMapping("/{id}")
    public ResponseDto<SentenceDto> delete(@PathVariable ("id") Integer sentenceId){
        return this.sentenceService.delete(sentenceId);
    }

    @Operation(
            tags = "get-all"
    )
    @GetMapping("/get-all")
    public ResponseDto<List<SentenceDto>> getAll(){
        return this.sentenceService.getAll();
    }


    @Operation(
            tags = "get-all-page"
    )
    @GetMapping("/get-all-page")
    public ResponseDto<Page<SentenceDto>> getAllPage(@RequestParam Integer page, @RequestParam Integer size){
        return this.sentenceService.getAllPage(page,size);
    }
}
