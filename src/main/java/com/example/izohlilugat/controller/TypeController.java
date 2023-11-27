package com.example.izohlilugat.controller;


import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.dto.TypeDto;
import com.example.izohlilugat.service.TypeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("type")
@RequiredArgsConstructor
public class TypeController {

    private final TypeService typeService;

    @Operation(
            tags = "create"
    )
    @PostMapping
    public ResponseDto<TypeDto> create(@RequestBody  @Valid TypeDto dto){
        return this.typeService.create(dto);
    }

    @Operation(
            tags = "get"
    )
    @GetMapping("/{id}")
    public ResponseDto<TypeDto> get(@PathVariable ("id") Integer typeId){
        return this.typeService.get(typeId);
    }

    @Operation(
            tags = "update"
    )
    @PutMapping("/{id}")
    public ResponseDto<TypeDto> update(@RequestBody TypeDto dto,@PathVariable ("id") Integer typeId){
        return this.typeService.update(dto,typeId);
    }

    @Operation(
            tags = "delete"
    )
    @DeleteMapping("/{id}")
    public ResponseDto<TypeDto> delete(@PathVariable ("id") Integer typeId){
        return this.typeService.delete(typeId);
    }

    @Operation(
            tags = "get-all"
    )
    @GetMapping("/get-all")
    public ResponseDto<List<TypeDto>> getAll(){
        return this.typeService.getAll();
    }


    @Operation(
            tags = "get-all-page"
    )
    @GetMapping("/get-all-page")
    public ResponseDto<Page<TypeDto>> getAllPage(@RequestParam Integer page, @RequestParam Integer size){
        return this.typeService.getAllPage(page,size);
    }
}
