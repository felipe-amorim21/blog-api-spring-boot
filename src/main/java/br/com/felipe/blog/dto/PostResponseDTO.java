package br.com.felipe.blog.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PostResponseDTO {

    private final Long id;
    private final String title;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final List<CommentResponseDTO> comments;

    public PostResponseDTO(Long id, String title, LocalDateTime createdAt, LocalDateTime updatedAt, List<CommentResponseDTO> comments) {
        this.id = id;
        this.title = title;
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
