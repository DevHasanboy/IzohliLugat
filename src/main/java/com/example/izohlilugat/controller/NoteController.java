package com.example.izohlilugat.controller;


import com.example.izohlilugat.dto.NoteDto;
import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @Operation(
            tags = "create"
    )
    @PostMapping
    public ResponseDto<NoteDto> create(@RequestBody  @Valid NoteDto dto){
        return this.noteService.create(dto);
    }

    @Operation(
            tags = "get"
    )
    @GetMapping("/{id}")
    public ResponseDto<NoteDto> get(@PathVariable ("id") Integer noteId){
        return this.noteService.get(noteId);
    }


    @Operation(
            tags = "update"
    )
    @PutMapping("/{id}")
    public ResponseDto<NoteDto> update(@RequestBody NoteDto dto,@PathVariable ("id") Integer noteId){
        return this.noteService.update(dto,noteId);
    }

    @Operation(
            tags = "delete"
    )
    @DeleteMapping("/{id}")
    public ResponseDto<NoteDto> delete(@PathVariable ("id") Integer noteId){
        return this.noteService.delete(noteId);
    }

    @Operation(
            tags = "get-all"
    )
    @GetMapping("/get-all")
    public ResponseDto<List<NoteDto>> getAll(){
        return this.noteService.getAll();
    }


    @Operation(
            tags = "get-all-page"
    )
    @GetMapping("/get-all-page")
    public ResponseDto<Page<NoteDto>> getAllPage(@RequestParam Integer page, @RequestParam Integer size){
        return this.noteService.getAllPage(page,size);
    }
}
