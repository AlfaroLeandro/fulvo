package com.example.fulvo.comunicacionAPI;

public class ConstToken {
    public static String token_refresh;
    public static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        ConstToken.token = token;
    }

    public static String getToken_refresh() {
        return token_refresh;
    }

    public static void setToken_refresh(String token_refresh) {
        ConstToken.token_refresh = token_refresh;
    }
}
