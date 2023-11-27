package com.example.izohlilugat.service.validation;

import com.example.izohlilugat.dto.ErrorDto;
import com.example.izohlilugat.dto.WordTypeDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class WordTypeValidation {

    public List<ErrorDto>  validate (WordTypeDto dto){
        List<ErrorDto> list=new ArrayList<>();
        if (StringUtils.isBlank(dto.getWordId().toString())){
            list.add(new ErrorDto("wordId","wordId cannot be null or empty"));
        }if (StringUtils.isBlank(dto.getWordSenId().toString())){
            list.add(new ErrorDto("wordInSenId","wordInSenId cannot be null or empty"));
        }
        return list;
    }
}
