package com.company.demo;
import com.company.demo.enums.*;

public class Token {
    private DfaState state = DfaState.Initial;
    private TokenType type;
    private String token = "";

    public void Token() {}

    public DfaState getState() {
        return this.state;
    }

    public void setState(DfaState state) {
        this.state = state;
    }

    public TokenType getType() {
        return this.type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void appendToken(char ch) {
        this.token += ch;
    }
}
