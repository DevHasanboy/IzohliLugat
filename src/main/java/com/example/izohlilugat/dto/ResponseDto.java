package com.example.izohlilugat.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto <T>{
    private String message;
    private boolean success;
    private int code;

    private T data;

    private List<ErrorDto> errors;

}
