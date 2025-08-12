package br.com.felipe.blog.dto;

public class CommentUpdateRequestDTO {
    
    private final String content;

    public CommentUpdateRequestDTO(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
