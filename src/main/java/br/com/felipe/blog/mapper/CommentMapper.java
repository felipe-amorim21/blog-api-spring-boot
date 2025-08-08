package br.com.felipe.blog.mapper;

import br.com.felipe.blog.dto.CommentRequestDTO;
import br.com.felipe.blog.entity.Comment;
import br.com.felipe.blog.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentRequestDTO toCommentRequestDTO(Comment comment){
        return new CommentRequestDTO(
                comment.getContent()
        );
    }

    public Comment toEntity(CommentRequestDTO commentRequestDTO, Post post){
        return new Comment(
                commentRequestDTO.getContent(),
                post
        );
    }
}
