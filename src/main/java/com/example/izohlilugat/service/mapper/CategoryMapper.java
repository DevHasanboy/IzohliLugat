package com.example.izohlilugat.service.mapper;

import com.example.izohlilugat.dto.CategoryDto;
import com.example.izohlilugat.model.Category;

import org.mapstruct.*;


@Mapper(componentModel = "spring")
public abstract class CategoryMapper {

    protected WordMapper wordMapper;

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    public  abstract Category toEntity(CategoryDto dto);

    public  abstract CategoryDto toDto(Category category);

    @Mapping(target = "words", expression = "java(category.getWords().stream().map(this.wordMapper::toDto).toList())")
    public  abstract CategoryDto toDtoWithWord(Category category);


  /*  public void view(){
        Category category=new Category();
        CategoryDto dto=new CategoryDto();
        dto.setWords(category.getWords().stream().map(this.wordMapper::toDto).toList());
    }*/

    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public  abstract void toUpdateFromDto(@MappingTarget Category category, CategoryDto dto);
}
