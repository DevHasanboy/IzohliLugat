package com.example.izohlilugat.exception;

import com.example.izohlilugat.dto.ErrorDto;
import com.example.izohlilugat.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class MethodArgumentNotValidException {


    @ExceptionHandler
    public ResponseEntity<ResponseDto<Void>> methodArgumentNotValidException(org.springframework.web.bind.MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(ResponseDto.<Void>builder()
                .code(-3)
                .message("validation error")
                .errors(e.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(fieldError -> {
                            String field = fieldError.getField();
                            String message = fieldError.getDefaultMessage();
                            String rejectionValue = String.valueOf(fieldError.getRejectedValue());
                            return new ErrorDto(field, String.format("message :: %s,rejection :: %s", message, rejectionValue));
                        }).toList())

                .build());
    }

}
