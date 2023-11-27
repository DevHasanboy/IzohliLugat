package com.example.izohlilugat.dto;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteDto {
    private Integer id;
    private Integer wordId;
    private String title;
    private String description;
    private String source;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime updatedAt ;

}
