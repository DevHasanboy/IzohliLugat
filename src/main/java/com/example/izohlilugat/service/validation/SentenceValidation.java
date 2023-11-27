package com.example.izohlilugat.service.validation;

import com.example.izohlilugat.dto.ErrorDto;
import com.example.izohlilugat.dto.SentenceDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SentenceValidation {

    public List<ErrorDto> validate (SentenceDto dto){
        List<ErrorDto> list=new ArrayList<>();
        if (StringUtils.isBlank(dto.getContent())){
            list.add(new ErrorDto("content","content cannot be null or empty"));
        }
        return list;
    }
}
