package com.example.izohlilugat.service.validation;

import com.example.izohlilugat.dto.ErrorDto;
import com.example.izohlilugat.dto.NoteDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NoteValidation {

    public List<ErrorDto> validate (NoteDto dto){
        List<ErrorDto> list=new ArrayList<>();
        if (StringUtils.isBlank(dto.getDescription())){
            list.add(new ErrorDto("description","description cannot be null or empty"));
        }if (StringUtils.isBlank(dto.getWordId().toString())){
            list.add(new ErrorDto("wordId","wordId cannot be null or empty"));
        }if (StringUtils.isBlank(dto.getTitle())){
            list.add(new ErrorDto("title","title cannot be null or empty"));
        }
        return list;
    }
}
