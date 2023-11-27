package com.example.izohlilugat.controller;

import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.dto.WordTypeDto;
import com.example.izohlilugat.service.WordTypeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wordType")
@RequiredArgsConstructor
public class WordTypeController {

    private final WordTypeService wordTypeService;



    @Operation(
            tags = "create"
    )
    @PostMapping
    public ResponseDto<WordTypeDto> create(@RequestBody  @Valid WordTypeDto dto){
        return this.wordTypeService.create(dto);
    }

    @Operation(
            tags = "get"
    )
    @GetMapping("/{id}")
    public ResponseDto<WordTypeDto> get(@PathVariable ("id") Integer wordId){
        return this.wordTypeService.get(wordId);
    }

    @Operation(
            tags = "update"
    )
    @PutMapping("/{id}")
    public ResponseDto<WordTypeDto> update(@RequestBody WordTypeDto dto,@PathVariable ("id") Integer wordId){
        return this.wordTypeService.update(dto,wordId);
    }

    @Operation(
            tags = "delete"
    )
    @DeleteMapping("/{id}")
    public ResponseDto<WordTypeDto> delete(@PathVariable ("id") Integer wordId){
        return this.wordTypeService.delete(wordId);
    }

    @Operation(
            tags = "get-all"
    )
    @GetMapping("/get-all")
    public ResponseDto<List<WordTypeDto>> getAll(){
        return this.wordTypeService.getAll();
    }

    @Operation(
            tags = "get-all-page"
    )
    @GetMapping("/get-all-page")
    public ResponseDto<Page<WordTypeDto>> getAllPage(@RequestParam Integer page, @RequestParam Integer size){
        return this.wordTypeService.getAllPage(page,size);
    }
}
