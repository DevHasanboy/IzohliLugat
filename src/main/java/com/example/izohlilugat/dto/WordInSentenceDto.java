package com.example.izohlilugat.dto;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WordInSentenceDto {

    private Integer id;
    private Integer wordId;
    private Integer sentenceId;
    private int orderId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
