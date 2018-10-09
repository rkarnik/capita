package com.capita.service.impl;

import com.capita.service.ValidationService;

import java.util.Stack;

public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean isParenthesesBalanced(String expression) {
        if (expression == null || expression.isEmpty())
            return true;

        StringBuffer parenthesesString = new StringBuffer();

        for (int j = 0; j < expression.length(); j++)
            if (expression.charAt(j) == '(' || expression.charAt(j) == ')')
                parenthesesString.append(expression.charAt(j));

        Stack<Character> stack = new Stack<>();
        // Iterate through string until empty
        for (int i = 0; i < parenthesesString.length(); i++) {
            // Push any open parentheses onto stack
            if (parenthesesString.charAt(i) == '(')
                stack.push(parenthesesString.charAt(i));
                // Check stack for corresponding closing parentheses, false if not valid
            else if (parenthesesString.charAt(i) == ')' && !stack.empty() && stack.peek() == '(')
                stack.pop();
            else
                return false;
        }
        // return true if no open parentheses left in stack
        return stack.empty();
    }

    @Override
    public boolean hasConsecutiveOperators(String expression) {
        for (int i = 0; i < expression.length(); i++)
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-' ||
                    expression.charAt(i) == '*' || expression.charAt(i) == '/'
                    || expression.charAt(i) == '^') {
                if (isValidCharAfterOperator(i, expression) && isValidCharBeforeOperator(i, expression))
                    continue;
                else
                    return true;
            }
        return false;
    }

    @Override
    public boolean isValidCharAroundOperand(String expression) {
        for (int i = 0; i < expression.length(); i++)
            if (Character.isDigit(expression.charAt(i))) {
                if (isValidCharAfterOperand(i, expression) && isValidCharBeforeOperand(i, expression))
                    continue;
                else
                    return false;
            }
        return true;
    }

    private boolean isValidCharAfterOperator(int i, String expression) {
        if (i == expression.length() - 1)
            return false;
        if (Character.isDigit(expression.charAt(i + 1)) || expression.charAt(i + 1) == '(')
            return true;
        return false;
    }

    private boolean isValidCharBeforeOperator(int i, String expression) {
        if (i == 0)
            return false;
        if (Character.isDigit(expression.charAt(i - 1)) || expression.charAt(i - 1) == ')')
            return true;
        return false;
    }

    private boolean isValidCharAfterOperand(int i, String expression) {
        if (i == expression.length() - 1)
            return true;
        if (Character.isDigit(expression.charAt(i + 1)) || expression.charAt(i + 1) == '(')
            return false;
        return true;
    }

    private boolean isValidCharBeforeOperand(int i, String expression) {
        if (i == 0)
            return true;
        if (Character.isDigit(expression.charAt(i - 1)) || expression.charAt(i - 1) == ')')
            return false;
        return true;
    }

}
