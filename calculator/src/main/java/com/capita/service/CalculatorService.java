package com.capita.service;

public interface CalculatorService {

    /**
     *
     * @param inputExpression mathematical expression to be evaluated
     * @return   value of evaluated expression
     */
    Double evaluateExpression(String inputExpression);

    void startCalculator();
}
