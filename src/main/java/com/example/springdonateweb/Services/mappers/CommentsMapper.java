package com.example.springdonateweb.Services.mappers;

import com.example.springdonateweb.Models.Dtos.Comments.CommentAddDto;
import com.example.springdonateweb.Models.Dtos.Comments.CommentCreateDto;
import com.example.springdonateweb.Models.Dtos.Comments.CommentDto;
import com.example.springdonateweb.Models.Dtos.Comments.CommentUpdateDto;
import com.example.springdonateweb.Models.Entities.CommentsEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CommentsMapper {
    CommentsEntity toEntity(CommentCreateDto commentCreateDto);
    CommentsEntity toEntity(CommentUpdateDto commentUpdateDto);
    CommentDto toDto(CommentsEntity commentsEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CommentsEntity partialUpdate(CommentUpdateDto commentUpdateDto, @MappingTarget CommentsEntity commentsEntity);
}
