package com.zerock.mallapi.dto;

public class TokenDTO {
    private String refreshToken;

    public TokenDTO() {
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
