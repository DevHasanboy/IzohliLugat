package com.example.izohlilugat.service.validation;

import com.example.izohlilugat.dto.ErrorDto;
import com.example.izohlilugat.dto.WordInSentenceDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WordInSentenceValidation {

    public List<ErrorDto> validate (WordInSentenceDto dto){
        List<ErrorDto> list=new ArrayList<>();
        if (StringUtils.isBlank(String.valueOf(dto.getOrderId()))){
            list.add(new ErrorDto("orderId","orderId cannot be null or empty"));
        }if (StringUtils.isBlank(dto.getWordId().toString())){
            list.add(new ErrorDto("wordId ","wordId cannot be null or empty"));
        }if (StringUtils.isBlank(dto.getSentenceId().toString())){
            list.add(new ErrorDto("sentence","sentence cannot be null or empty"));
        }
        return list;
    }
}
