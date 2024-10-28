package com.example.springdonateweb.Services;

import com.example.springdonateweb.Models.Dtos.Comments.CommentCreateDto;
import com.example.springdonateweb.Models.Dtos.Comments.CommentDto;
import com.example.springdonateweb.Models.Dtos.Comments.CommentUpdateDto;
import com.example.springdonateweb.Models.Entities.CommentsEntity;
import com.example.springdonateweb.Repositories.CommentsRepository;
import com.example.springdonateweb.Services.interfaces.ICommentsService;
import com.example.springdonateweb.Services.mappers.CommentsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentsService implements ICommentsService {

    private final CommentsRepository commentsRepository;
    private final CommentsMapper commentsMapper;

    @Override
    public List<CommentDto> findAll() {
        return commentsRepository.findAll().stream()
                .map(commentsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto findById(int id) {
        Optional<CommentsEntity> comment = commentsRepository.findById(id);
        return comment.map(commentsMapper::toDto).orElse(null);
    }

    @Override
    public CommentDto create(CommentCreateDto commentCreateDto) {
        CommentsEntity comment = commentsMapper.toEntity(commentCreateDto);
        CommentsEntity savedComment = commentsRepository.save(comment);
        return commentsMapper.toDto(savedComment);
    }

    @Override
    public CommentDto update(CommentUpdateDto commentUpdateDto) {
        Optional<CommentsEntity> comment = commentsRepository.findById(commentUpdateDto.getCommentId());
        return comment
                .map(c -> {
                    CommentsEntity updatedComment = commentsMapper.partialUpdate(commentUpdateDto, c);
                    CommentsEntity result = commentsRepository.save(updatedComment);
                    return commentsMapper.toDto(result);
                })
                .orElse(null);
    }

    @Override
    public void delete(int id) {
        commentsRepository.deleteById(id);
    }
}
