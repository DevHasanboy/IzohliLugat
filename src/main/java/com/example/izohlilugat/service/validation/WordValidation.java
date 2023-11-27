package com.example.izohlilugat.service.validation;

import com.example.izohlilugat.dto.ErrorDto;
import com.example.izohlilugat.dto.WordDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WordValidation {

    public List<ErrorDto> validate (WordDto dto){
        List<ErrorDto> list=new ArrayList<>();
        if (StringUtils.isBlank(dto.getLabel())){
            list.add(new ErrorDto("label","label cannot be null or empty"));
        }if (StringUtils.isBlank(dto.getTranscription())){
            list.add(new ErrorDto("transcription","transcription cannot be null or empty"));
        }
        if (StringUtils.isBlank(dto.getAudioId().toString())){
            list.add(new ErrorDto("audioId","audioId cannot be null or empty"));
        }
        return list;
    }
}
