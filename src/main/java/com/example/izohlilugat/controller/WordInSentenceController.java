package com.example.izohlilugat.controller;


import com.example.izohlilugat.dto.ResponseDto;

import com.example.izohlilugat.dto.WordInSentenceDto;
import com.example.izohlilugat.service.WordInSentenceService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("word-in-sent")
@RequiredArgsConstructor
public class WordInSentenceController {

    private final WordInSentenceService wordInSentenceService;


    @Operation(
            tags = "create"
    )
    @PostMapping
    public ResponseDto<WordInSentenceDto> create(@RequestBody  @Valid WordInSentenceDto dto){
        return this.wordInSentenceService.create(dto);
    }

    @Operation(
            tags = "get"
    )
    @GetMapping("/{id}")
    public ResponseDto<WordInSentenceDto> get(@PathVariable ("id") Integer wordId){
        return this.wordInSentenceService.get(wordId);
    }

    @Operation(
            tags = "update"
    )
    @PutMapping("/{id}")
    public ResponseDto<WordInSentenceDto> update(@RequestBody WordInSentenceDto dto,@PathVariable ("id") Integer wordId){
        return this.wordInSentenceService.update(dto,wordId);
    }

    @Operation(
            tags = "delete"
    )
    @DeleteMapping("/{id}")
    public ResponseDto<WordInSentenceDto> delete(@PathVariable ("id") Integer wordId){
        return this.wordInSentenceService.delete(wordId);
    }

    @Operation(
            tags = "get-all"
    )
    @GetMapping("/get-all")
    public ResponseDto<List<WordInSentenceDto>> getAll(){
        return this.wordInSentenceService.getAll();
    }

    @Operation(
            tags = "get-all-page"
    )
    @GetMapping("/get-all-page")
    public ResponseDto<Page<WordInSentenceDto>> getAllPage(@RequestParam Integer page, @RequestParam Integer size){
        return this.wordInSentenceService.getAllPage(page,size);
    }
}
