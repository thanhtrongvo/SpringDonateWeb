package com.example.springdonateweb.Models.Dtos.Comments;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private int commentId;
    private Integer userId;
    private Integer programId;
    private String content;
    private Timestamp createdAt;
}
