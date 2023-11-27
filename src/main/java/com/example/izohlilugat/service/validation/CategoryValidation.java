package com.example.izohlilugat.service.validation;

import com.example.izohlilugat.dto.CategoryDto;
import com.example.izohlilugat.dto.ErrorDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryValidation {

    public List<ErrorDto> validate(CategoryDto dto){
        List<ErrorDto> list=new ArrayList<>();
        if (StringUtils.isBlank(dto.getName())){
            list.add(new ErrorDto("name","name cannot be null or empty"));
        }if (StringUtils.isBlank(dto.getDescription())){
            list.add(new ErrorDto("description","description cannot be null or empty"));
        }
        return list;
    }
}
