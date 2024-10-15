package com.gpc.carros.electricos.mappers;

import com.gpc.carros.electricos.model.Form;
import com.gpc.carros.electricos.model.request.FormRequest;
import org.mapstruct.Mapper;

@Mapper
public interface FormMapper {

    Form toForm(FormRequest formRequest);

}
