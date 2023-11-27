package com.example.izohlilugat.controller;

import com.example.izohlilugat.dto.CategoryDto;
import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categ")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @Operation(
            tags = "create"
    )
    @PostMapping
    public ResponseDto<CategoryDto> create(@RequestBody @Valid CategoryDto dto){
        return this.categoryService.create(dto);
    }

    @Operation(
            tags = "get"
    )
    @GetMapping("/{id}")
    public ResponseDto<CategoryDto> get(@PathVariable ("id") Integer categoryId){
        return this.categoryService.get(categoryId);
    }
    @Operation(
            tags = "update"
    )
    @PutMapping("/{id}")
    public ResponseDto<CategoryDto> update(@RequestBody CategoryDto dto,@PathVariable ("id") Integer categoryId){
        return this.categoryService.update(dto,categoryId);
    }

    @Operation(
            tags = "delete"
    )

    @DeleteMapping("/{id}")
    public ResponseDto<CategoryDto> delete(@PathVariable ("id") Integer categoryId){
        return this.categoryService.delete(categoryId);
    }

    @Operation(
            tags = "get-all"
    )
    @GetMapping("/get-all")
    public ResponseDto<List<CategoryDto>> getAll(){
        return this.categoryService.getAll();
    }

    @Operation(
            tags = "get-all-page"
    )
    @GetMapping("/get-all-page")
    public ResponseDto<Page<CategoryDto>> getAllPage(@RequestParam Integer page, @RequestParam Integer size){
        return this.categoryService.getAllPage(page,size);
    }
}