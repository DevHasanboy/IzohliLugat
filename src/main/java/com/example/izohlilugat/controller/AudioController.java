package com.example.izohlilugat.controller;


import com.example.izohlilugat.dto.AudioDto;
import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.service.AudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("audio")
@RequiredArgsConstructor
public class AudioController {

    private final AudioService audioService;

    @PostMapping("/upload")
    public ResponseDto<AudioDto> upload(@RequestBody MultipartFile file){

        return this.audioService.upload(file);

    }

    @GetMapping("/{id}")
    public ResponseDto<AudioDto> download(@PathVariable ("id") Integer audioId){
        return this.audioService.dowload(audioId);
    }

    public ResponseDto<AudioDto> update(@RequestBody MultipartFile file,@PathVariable("id") Integer audioId){
        return this.audioService.update(file,audioId);
    }
    public ResponseDto<AudioDto> delete(@PathVariable("id") Integer audioId){
        return this.audioService.delete(audioId);
    }
}
