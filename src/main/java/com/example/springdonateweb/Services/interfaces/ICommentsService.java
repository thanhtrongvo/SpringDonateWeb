package com.example.springdonateweb.Services.interfaces;

import com.example.springdonateweb.Models.Dtos.Comments.CommentCreateDto;
import com.example.springdonateweb.Models.Dtos.Comments.CommentDto;
import com.example.springdonateweb.Models.Dtos.Comments.CommentUpdateDto;

import java.util.List;

public interface ICommentsService {
    List<CommentDto> findAll();
    CommentDto findById(int id);
    CommentDto create(CommentCreateDto commentCreateDto);
    CommentDto update(CommentUpdateDto commentUpdateDto);
    void delete(int id);
}
