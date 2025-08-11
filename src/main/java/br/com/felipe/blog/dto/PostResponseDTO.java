package br.com.felipe.blog.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PostResponseDTO {

    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final List<CommentResponseDTO> comments;

    public PostResponseDTO(Long id, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt, List<CommentResponseDTO> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent(){
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<CommentResponseDTO> getComments() {
        return comments;
    }
}
