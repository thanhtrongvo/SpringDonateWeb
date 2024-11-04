package com.example.springdonateweb.Services.interfaces;

import com.example.springdonateweb.Models.Dtos.Comments.CommentCreateDto;
import com.example.springdonateweb.Models.Dtos.Comments.CommentResponseDto;
import com.example.springdonateweb.Models.Dtos.Comments.CommentUpdateDto;

import java.util.List;

public interface ICommentsService {
    List<CommentResponseDto> findAll();
    CommentResponseDto findById(int id);
    CommentResponseDto create(CommentCreateDto commentCreateDto);
    CommentResponseDto update(int id, CommentUpdateDto commentUpdateDto);
    void delete(int id);
}
