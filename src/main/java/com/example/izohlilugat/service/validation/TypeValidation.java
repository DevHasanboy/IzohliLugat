package com.example.izohlilugat.service.validation;

import com.example.izohlilugat.dto.ErrorDto;
import com.example.izohlilugat.dto.TypeDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TypeValidation {

    public List<ErrorDto> validate (TypeDto dto){
        List<ErrorDto> list=new ArrayList<>();
        if (StringUtils.isBlank(dto.getName())){
            list.add(new ErrorDto("name","name cannot be null or empty"));
        }if (StringUtils.isBlank(String.valueOf(dto.getOrderId()))){
            list.add(new ErrorDto("orderId","orderId cannot be null or empty"));
        }
        return list;
    }
}
