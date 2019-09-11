package com.company.demo;

import java.util.ArrayList;
import com.company.demo.enums.DfaState;
import com.company.demo.enums.TokenType;

public class Main {
    public static String code = "age >= 45";
    public static ArrayList<Token> arrayList = new ArrayList();

    public static void main(String[] args) {
        int codeLen = code.length();
        Token token = new Token();

        for(int i = 0; i < codeLen; i++) {
            char ch = code.charAt(i);
            if (token.getState() == DfaState.Initial) {
                if(isAlpha(ch)) {
                    token.setState(DfaState.Id);
                    token.setType(TokenType.Identifier);
                    token.appendToken(ch);
                } else if (isDigit(ch)) {
                    token.setState(DfaState.IntLiteral);
                    token.setType(TokenType.IntLiteral);
                    token.appendToken(ch);
                } else if (ch == '>') {
                    token.setState(DfaState.GT);
                    token.setType(TokenType.GT);
                    token.appendToken(ch);
                }
            } else {
                switch (token.getState()) {
                    case Initial:
                        break;
                    case Id:
                        if (isAlpha(ch) || isDigit(ch)) {
                            token.appendToken(ch);
                        } else {
                            token = initToken(token);
                        }
                        break;
                    case GT:
                        if(ch == '=') {
                            token.setType(TokenType.GE);
                            token.setState(DfaState.GE);
                            token.appendToken(ch);
                        } else {
                            token = initToken(token);
                        }
                        break;
                    case GE:
                        token = initToken(token);
                        break;
                    case IntLiteral:
                        if (isDigit(ch)) {
                            token.appendToken(ch);
                        } else {
                            token = initToken(token);
                        }
                        break;
                }
            }
        }

        arrayList.add(token);

        for(Token i:arrayList) {
            System.out.println(i.getType() + ":" + i.getToken());
        }
    }

    private static boolean isAlpha(char ch) {
        return Character.isLetter(ch);
    }

    private static boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }

    private static Token initToken(Token token) {
        arrayList.add(token);
        Token newToken = new Token();
        return newToken;
    }
}
