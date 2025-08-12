package br.com.felipe.blog.dto;

public class CommentRequestDTO {

    private final String content;
    private final Long postId;

    public CommentRequestDTO(String content, Long postId) {
        this.content = content;
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public Long getPostId() {
        return postId;
    }

}
