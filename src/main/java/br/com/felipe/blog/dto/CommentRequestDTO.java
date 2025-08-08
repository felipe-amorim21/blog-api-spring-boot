package br.com.felipe.blog.dto;

public class CommentRequestDTO {

    private final String content;

    public CommentRequestDTO(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
