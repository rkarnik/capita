package com.capita.service.impl;

import com.capita.configuration.Braces;
import com.capita.configuration.Operation;
import com.capita.service.CalculatorService;
import com.capita.service.InputOutputService;
import com.capita.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class CalculatorServiceImpl implements CalculatorService {

    @Autowired
    InputOutputService inputOutputService;

    @Autowired
    ValidationService validationService;

    @Override
    public Double evaluateExpression(String expression) {
        Deque<Double> operandStack = new ArrayDeque<>();
        Deque<Character> operatorStack = new ArrayDeque<>();
        expression.chars().mapToObj(ch -> (char) ch).forEach(token -> {
            // push digit on to operand stack
            if (token >= '0' && token <= '9')
                operandStack.push(Double.valueOf(Character.getNumericValue(token)));

            else if (token == Braces.OPEN_PARENTHESES.getBrace())
                operatorStack.push(token);

            else if (token == Braces.CLOSED_PARENTHESES.getBrace()) {
                while (operatorStack.peek() != Braces.OPEN_PARENTHESES.getBrace())
                    operandStack.push(performOperation(operatorStack.pop(), operandStack.pop(), operandStack.pop()));
                operatorStack.pop();
            } else if (token == Operation.ADDITION.getOperator() || token == Operation.SUBTRACTION.getOperator() ||
                    token == Operation.MULTIPLICATION.getOperator() || token == Operation.DIVISION.getOperator() || token == Operation.POWER.getOperator()) {
                while (!operatorStack.isEmpty() && hasPrecedence(token, operatorStack.peek()))
                    operandStack.push(performOperation(operatorStack.pop(), operandStack.pop(), operandStack.pop()));
                operatorStack.push(token);
            }
        });

        while (!operatorStack.isEmpty())
            operandStack.push(performOperation(operatorStack.pop(), operandStack.pop(), operandStack.pop()));

        return operandStack.pop();
    }

    @Override
    public void startCalculator() {
        List<String> inputList = inputOutputService.loadInput();
        List<String> resultList = new ArrayList<>();
        for(String inputExpression : inputList) {
            inputExpression = inputExpression.replaceAll("\\s+","");
            if(!validationService.isParenthesesBalanced(inputExpression) || validationService.hasConsecutiveOperators(inputExpression)
                    || !validationService.isValidCharAroundOperand(inputExpression))
                resultList.add("INVALID EXPRESSION");
            else
                resultList.add(String.valueOf(evaluateExpression(inputExpression)));
        }
        inputOutputService.showResult(resultList);
    }

    private boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/' || op1 == '^') && (op2 == '+' || op2 == '-'))
            return false;
        if ((op1 == '^') && (op2 == '+' || op2 == '-' || op2 == '*' || op2 == '/'))
            return false;
        else
            return true;
    }

    // A utility method to apply an operator 'op' on operands 'a'
    // and 'b'. Return the result.
    private double performOperation(char op, double b, double a) {
        double result = 0;
        switch (op) {
            case '+': result = a + b;
                break;
            case '-': result = a - b;
                break;
            case '*': result = a * b;
                break;
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                result = a / b;
                break;
            case '^': result = Math.pow(a, b);
                break;
        }
        return result;
    }


}
