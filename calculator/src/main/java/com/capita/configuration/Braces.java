package com.capita.configuration;

public enum Braces {

    CLOSED_PARENTHESES(')'),
    OPEN_PARENTHESES('(');

    private final char brace;

    Braces(char brace) {
        this.brace = brace;
    }
    public char getBrace() {
        return brace;
    }
}
