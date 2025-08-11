package br.com.felipe.blog.mapper;

import br.com.felipe.blog.dto.CommentResponseDTO;
import br.com.felipe.blog.dto.PostRequestDTO;
import br.com.felipe.blog.dto.PostResponseDTO;
import br.com.felipe.blog.entity.Post;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostMapper {

    public PostResponseDTO toResponseDTO(Post post){
        List<CommentResponseDTO> comments = post.getComments().stream()
                .map(comment -> new CommentResponseDTO(
                        comment.getId(),
                        comment.getContent(),
                        comment.getCreatedAt(),
                        comment.getUpdatedAt()
                )).toList();

        return new PostResponseDTO(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                comments
        );
    }

    public Post toEntity(PostRequestDTO postRequestDTO){
        return new Post(postRequestDTO.getTitle(), postRequestDTO.getContent());
    }
}
