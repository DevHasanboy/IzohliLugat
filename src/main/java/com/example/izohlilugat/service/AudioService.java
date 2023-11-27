package com.example.izohlilugat.service;

import com.example.izohlilugat.dto.AudioDto;
import com.example.izohlilugat.dto.ResponseDto;
import com.example.izohlilugat.model.Audio;
import com.example.izohlilugat.repository.AudioRepository;
import com.example.izohlilugat.service.mapper.AudioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AudioService {

    private final AudioMapper audioMapper;
    private final AudioRepository audioRepository;
    public ResponseDto<AudioDto> upload(MultipartFile file) {

        try {
            return ResponseDto.<AudioDto>builder()
                    .success(true)
                    .message("ok")
                    .data(this.audioMapper.toDto(audioRepository.save(
                            Audio.builder()
                                    .path(savefile(file))
                                    .status(true)
                                    .ext(Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1])
                                    .createdAt(LocalDateTime.now())
                                    .build()
                    )))
                    .build();
        } catch (IOException e) {
            return ResponseDto.<AudioDto>builder()
                    .code(-2)
                    .message(String.format("file while saving error message %s ::"+ e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<AudioDto> dowload(Integer audioId) {
        try {
            Optional<Audio> optionalAudio=this.audioRepository.findByIdAndDeletedAtIsNull(audioId);
                if (optionalAudio.isEmpty()){
                    return ResponseDto.<AudioDto>builder()
                            .code(-1)
                            .message(String.format("not found of id  %d ::",audioId))
                            .build();
                }

                AudioDto dto=this.audioMapper.toDto(optionalAudio.get());
                return ResponseDto.<AudioDto>builder()
                        .success(true)
                        .message("ok")
                        .data(dto)
                        .build();


        }catch (Exception e){
            return ResponseDto.<AudioDto>builder()
                    .code(-2)
                    .message("audio  not  found")
                    .build();
        }
    }

    public ResponseDto<AudioDto> update(MultipartFile file, Integer audioId) {
        try {
            Optional<Audio> optionalAudio = this.audioRepository.findByIdAndDeletedAtIsNull(audioId);
                if (optionalAudio.isEmpty()) {
                    return ResponseDto.<AudioDto>builder()
                            .code(-1)
                            .message(String.format("not found of id  %d ::", audioId))
                            .build();
                }

                Files.delete(Path.of(optionalAudio.get().getPath()));
                AudioDto dto= AudioDto.builder()
                        .updatedAt(LocalDateTime.now())
                        .status(true)
                        .ext(Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1])
                        .path(savefile(file))
                        .build();
                this.audioRepository.save(optionalAudio.get());
                this.audioMapper.toUpdateFromDto(optionalAudio.get(),dto);
                return ResponseDto.<AudioDto>builder()
                        .success(true)
                        .message("ok")
                        .data(this.audioMapper.toDto(
                                optionalAudio.get()
                        ))
                        .build();

        }catch (Exception e){
            return ResponseDto.<AudioDto>builder()
                    .code(-2)
                    .message("audio  not  found")
                    .build();
        }
    }

    public ResponseDto<AudioDto> delete(Integer audioId) {
        Optional<Audio> optionalAudio=this.audioRepository.findByIdAndDeletedAtIsNull(audioId);
        if (optionalAudio.isEmpty()){
            return ResponseDto.<AudioDto>builder()
                    .code(-1)
                    .message("not fund audio")
                    .build();
        }
        File file=new File(optionalAudio.get().getPath());
        if (file.exists()){
            file.delete();
            return ResponseDto.<AudioDto>builder()
                    .message("ok")
                    .success(true)
                    .data(this.audioMapper.toDto(this.audioRepository.save(Audio.builder()
                                    .status(false)
                                    .deletedAt(LocalDateTime.now())
                            .build())))
                    .build();
        }else {
            return ResponseDto.<AudioDto>builder()
                    .code(-1)
                    .message("error")
                    .build();
        }
    }


    private String savefile(MultipartFile file) throws IOException {
        String  folder=String.format("upload%s", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));

        File filPath=new File(folder);
        if (!filPath.exists()){
            filPath.mkdirs();
        }

        String fileName=String.format("%s/%s",folder, UUID.randomUUID());
        Files.copy(file.getInputStream(), Path.of(fileName));
        return fileName;
    }

}
