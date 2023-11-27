package com.example.izohlilugat.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeDto {

    private Integer id;
    private String name;
    private int orderId;

    private List<WordTypeDto> wordTypes;

    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime updatedAt;
}
