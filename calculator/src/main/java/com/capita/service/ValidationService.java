package com.capita.service;

public interface ValidationService {

    /**
     *
     * @param expression
     * @return true if parentheses are balanced else return false
     */
    boolean isParenthesesBalanced(String expression);

    /**
     *
     * @param expression
     * @return true if there are consecutive operators or else return false
     */
    boolean hasConsecutiveOperators(String expression);

    /**
     *
     * @param expression
     * @return check if there is a valid character after and before operand
     */
    boolean isValidCharAroundOperand(String expression);

}
