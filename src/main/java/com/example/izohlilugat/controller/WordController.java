package com.example.izohlilugat.controller;

import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.dto.WordDto;
import com.example.izohlilugat.service.WordService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("word")
@RequiredArgsConstructor
public class WordController {
    private final WordService wordService;


    @Operation(
            tags = "create"
    )
    @PostMapping
    public ResponseDto<WordDto> create(@RequestBody  @Valid WordDto dto){
        return this.wordService.create(dto);
    }


    @Operation(
            tags = "get"
    )
    @GetMapping("/{id}")
    public ResponseDto<WordDto> get(@PathVariable ("id") Integer wordId){
        return this.wordService.get(wordId);
    }


    @Operation(
            tags = "update"
    )
    @PutMapping("/{id}")
    public ResponseDto<WordDto> update(@RequestBody WordDto dto,@PathVariable ("id") Integer wordId){
        return this.wordService.update(dto,wordId);
    }

    @Operation(
            tags = "delete"
    )
    @DeleteMapping("/{id}")
    public ResponseDto<WordDto> delete(@PathVariable ("id") Integer wordId){
        return this.wordService.delete(wordId);
    }

    @Operation(
            tags = "get-all"
    )
    @GetMapping("/get-all")
    public ResponseDto<List<WordDto>> getAll(){
        return this.wordService.getAll();
    }


    @Operation(
            tags = "get-all-page"
    )
    @GetMapping("/get-all-page")
    public ResponseDto<Page<WordDto>> getAllPage(@RequestParam Integer page, @RequestParam Integer size){
        return this.wordService.getAllPage(page,size);
    }
}
