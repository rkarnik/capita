package com.capita.configuration;

public enum Operation {
    ADDITION('+'),
    SUBTRACTION('-'),
    MULTIPLICATION('*'),
    DIVISION('/'),
    POWER('^');

    private final char operator;

    Operation(char operator) {
        this.operator = operator;
    }

    public char getOperator() {
        return operator;
    }
}
