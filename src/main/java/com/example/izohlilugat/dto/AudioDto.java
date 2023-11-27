package com.example.izohlilugat.dto;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AudioDto {
    private Integer id;
    private String path;
    private String ext;//ext -> extension
    private Boolean status;
    private byte[] data;
    private List<WordDto> words;

    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime updatedAt;
}
