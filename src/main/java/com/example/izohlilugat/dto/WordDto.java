package com.example.izohlilugat.dto;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WordDto {
    private Integer id;
    private String label;
    private String transcription;
    private Integer categoryId;
    private Integer audioId;
    private Integer numView;
    private Integer numLike;
    private Integer numShare;

    private List<DayWordDto> dayWords;
    private List<WordInSentenceDto> wordInSentences;
    private List<NoteDto> notes;
    private List<WordTypeDto> wordTypes;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
