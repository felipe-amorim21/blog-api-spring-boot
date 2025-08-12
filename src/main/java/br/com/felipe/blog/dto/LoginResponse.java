package br.com.felipe.blog.dto;

public class LoginResponse {
    private final String token;
    private final long expiresIn;

    public LoginResponse(String token, long expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }

    public String getToken(){
        return token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }
}
