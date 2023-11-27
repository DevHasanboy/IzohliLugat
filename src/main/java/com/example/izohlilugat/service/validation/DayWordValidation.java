package com.example.izohlilugat.service.validation;

import com.example.izohlilugat.dto.DayWordDto;
import com.example.izohlilugat.dto.ErrorDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DayWordValidation {

    public List<ErrorDto> validate (DayWordDto dto){

        List<ErrorDto> list=new ArrayList<>();
        if (StringUtils.isBlank(dto.getWordId().toString())){
            list.add(new ErrorDto("wordId","wordId cannot be null or empty"));
        }
        return list;
    }
}
