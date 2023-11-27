package com.example.izohlilugat.controller;

import com.example.izohlilugat.dto.DayWordDto;
import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.service.DayWordService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dayword")
@RequiredArgsConstructor
public class DayWordController {

    private final DayWordService dayWordService;


    @Operation(
            tags = "create"
    )
    @PostMapping
    public ResponseDto<DayWordDto> create(@RequestBody  @Valid DayWordDto dto){
        return this.dayWordService.create(dto);
    }

    @Operation(
            tags = "get"
    )
    @GetMapping("/{id}")
    public ResponseDto<DayWordDto> get(@PathVariable ("id") Integer wordId){
        return this.dayWordService.get(wordId);
    }

    @Operation(
            tags = "update"
    )
    @PutMapping("/{id}")
    public ResponseDto<DayWordDto> update(@RequestBody DayWordDto dto,@PathVariable ("id") Integer wordId){
        return this.dayWordService.update(dto,wordId);
    }

    @Operation(
            tags = "delete"
    )
    @DeleteMapping("/{id}")
    public ResponseDto<DayWordDto> delete(@PathVariable ("id") Integer wordId){
        return this.dayWordService.delete(wordId);
    }

    @Operation(
            tags = "get-all"
    )
    @GetMapping("/get-all")
    public ResponseDto<List<DayWordDto>> getAll(){
        return this.dayWordService.getAll();
    }


    @Operation(
            tags = "get-all-page"
    )
    @GetMapping("/get-all-page")
    public ResponseDto<Page<DayWordDto>> getAllPage(@RequestParam Integer page, @RequestParam Integer size){
        return this.dayWordService.getAllPage(page,size);
    }
}