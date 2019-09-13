package com.company.demo;

import java.util.ArrayList;
import com.company.demo.enums.DfaState;
import com.company.demo.enums.TokenType;

public class Main {
    public static String code = "2 + 3 * 5";
    public static ArrayList<Token> arrayList = new ArrayList();

    public static void main(String[] args) {
        int codeLen = code.length();
        Token token = new Token();

        for(int i = 0; i < codeLen; i++) {
            char ch = code.charAt(i);
            if (token.getState() == DfaState.Initial) {
                if(isAlpha(ch)) {
                    if(ch == 'i') {
                        token.setState(DfaState.Id_int1);
                    } else {
                        token.setState(DfaState.Id);
                    }

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
                } else if (ch == '=') {
                    token.setState(DfaState.GE);
                    token.setType(TokenType.GE);
                    token.appendToken(ch);
                } else if (ch == '+') {
                    token.setState(DfaState.Plus);
                    token.setType(TokenType.Plus);
                    token.appendToken(ch);
                } else if (ch == '-') {
                    token.setState(DfaState.Minus);
                    token.setType(TokenType.Minus);
                    token.appendToken(ch);
                } else if (ch == '*') {
                    token.setState(DfaState.Star);
                    token.setType(TokenType.Star);
                    token.appendToken(ch);
                } else if (ch == '/') {
                    token.setState(DfaState.Slash);
                    token.setType(TokenType.Slash);
                    token.appendToken(ch);
                }
            } else {
                switch (token.getState()) {
                    case Id_int1:
                        if (ch == 'n') {
                            token.setState(DfaState.Id_int2);
                            token.appendToken(ch);
                        } else if(isDigit(ch) || isAlpha(ch)) {
                            token.setState(DfaState.Id);
                            token.appendToken(ch);
                        } else {
                            token = initToken(token);
                        }
                        break;
                    case Id_int2:
                        if (ch == 't') {
                            token.setState(DfaState.Id_int3);
                            token.appendToken(ch);
                        } else if(isDigit(ch) || isAlpha(ch)) {
                            token.setState(DfaState.Id);
                            token.appendToken(ch);
                        } else {
                            token = initToken(token);
                        }
                        break;
                    case Id_int3:
                        if(isBlank(ch)) {
                            token.setType(TokenType.Int);
                            token = initToken(token);
                        } else {
                            token.setState(DfaState.Id);
                            token.appendToken(ch);
                        }
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
                    case Plus:
                    case Minus:
                    case Star:
                    case Slash:
                        token = initToken(token);
                        break;
                    case IntLiteral:
                        if (isDigit(ch)) {
                            token.appendToken(ch);
                        } else {
                            token = initToken(token);
                        }
                        break;
                    default:
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

    private static boolean isBlank(char ch) {
        return Character.isSpaceChar(ch);
    }

    private static Token initToken(Token token) {
        arrayList.add(token);
        Token newToken = new Token();
        return newToken;
    }
}
