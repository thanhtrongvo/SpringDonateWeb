package com.example.springdonateweb.Services.mappers;

import com.example.springdonateweb.Models.Dtos.Blogs.BlogCreateDto;
import com.example.springdonateweb.Models.Dtos.Blogs.BlogResponseDto;
import com.example.springdonateweb.Models.Dtos.Blogs.BlogUpdateDto;
import com.example.springdonateweb.Models.Entities.BlogEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BlogMapper {

    BlogResponseDto toDto(BlogEntity entity);
    BlogEntity toEntity(BlogCreateDto blogCreateDto);
    BlogEntity toEntity(BlogUpdateDto blogUpdateDto);

    @Mappings({
            @Mapping(target = "createdAt", expression = "java(entity.getCreatedAt().toString())"),
            @Mapping(target = "updatedAt", expression = "java(entity.getUpdatedAt().toString())")
    })
    BlogResponseDto toResponseDto(BlogEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BlogEntity partialUpdate(BlogUpdateDto blogUpdateDto, @MappingTarget BlogEntity blogEntity);
}
