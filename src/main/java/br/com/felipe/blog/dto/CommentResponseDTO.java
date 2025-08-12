package br.com.felipe.blog.dto;

import java.time.LocalDateTime;

public class CommentResponseDTO {

    private final Long id;
    private final String content;
    private final Long postId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    
    public CommentResponseDTO(Long id, String content, Long postId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.content = content;
        this.postId = postId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Long getPostId() {
        return postId;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
