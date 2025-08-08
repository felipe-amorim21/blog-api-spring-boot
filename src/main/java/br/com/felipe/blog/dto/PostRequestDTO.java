package br.com.felipe.blog.dto;

public class PostRequestDTO {

    private final String title;
    private final String content;

    public PostRequestDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
