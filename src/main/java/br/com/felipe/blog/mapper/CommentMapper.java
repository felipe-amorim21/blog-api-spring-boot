package br.com.felipe.blog.mapper;

import br.com.felipe.blog.dto.CommentRequestDTO;
import br.com.felipe.blog.dto.CommentResponseDTO;
import br.com.felipe.blog.entity.Comment;
import br.com.felipe.blog.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentResponseDTO toResponseDTO(Comment comment) {
        return new CommentResponseDTO(
                comment.getId(),
                comment.getContent(),
                comment.getPost().getId(),
                comment.getCreatedAt(),
                comment.getUpdatedAt());
    }

    public Comment toEntity(CommentRequestDTO commentRequestDTO, Post post) {
        return new Comment(
                commentRequestDTO.getContent(),
                post);
    }
}
