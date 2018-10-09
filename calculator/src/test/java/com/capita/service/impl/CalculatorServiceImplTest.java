package com.capita.service.impl;

import com.capita.configuration.CalculatorAppConfig;
import com.capita.service.CalculatorService;
import com.capita.service.InputOutputService;
import com.capita.service.ValidationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CalculatorAppConfig.class, loader = AnnotationConfigContextLoader.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
public class CalculatorServiceImplTest {

    @InjectMocks
    @Autowired
    CalculatorService calculatorService;

    @Mock
    InputOutputService inputOutputService;

    @Mock
    ValidationService validationService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testEvaluateExpression1() {
        Assert.assertEquals((double) 158.0, (double) calculatorService.evaluateExpression("7+(6*5^2+3-4/2)"), 0.0);
    }

    @Test
    public void testEvaluateExpression2() {
        Assert.assertEquals((double) -3, (double) calculatorService.evaluateExpression("(8*5/8)-(3/1)-5"), 0.0);
    }

    @Test
    public void testStartCalculatorWithValidExpression() {
        List<String> inputList = new ArrayList<>();
        List<String> resultList = new ArrayList<>();
        resultList.add("4.0");
        inputList.add("6+7-9");
        when(inputOutputService.loadInput()).thenReturn(inputList);
        when(validationService.isParenthesesBalanced(anyString())).thenReturn(true);
        when(validationService.hasConsecutiveOperators(anyString())).thenReturn(false);
        when(validationService.isValidCharAroundOperand(anyString())).thenReturn(true);
        when(inputOutputService.showResult(resultList)).thenReturn(resultList);
        calculatorService.startCalculator();
        verify(inputOutputService, times(1)).loadInput();
        verify(validationService, times(1)).isParenthesesBalanced(anyString());
        verify(validationService, times(1)).hasConsecutiveOperators(anyString());
        verify(validationService, times(1)).isValidCharAroundOperand(anyString());
        verify(inputOutputService, times(1)).showResult(resultList);
    }



    @Test
    public void testStartCalculatorWithInvalidCharAroundOperand() {
       List<String> inputList = new ArrayList<>();
       List<String> resultList = new ArrayList<>();
       resultList.add("INVALID EXPRESSION");
       inputList.add("67-9");
        when(inputOutputService.loadInput()).thenReturn(inputList);
        when(validationService.isParenthesesBalanced(anyString())).thenReturn(true);
        when(validationService.hasConsecutiveOperators(anyString())).thenReturn(false);
        when(validationService.isValidCharAroundOperand(anyString())).thenReturn(false);
        when(inputOutputService.showResult(resultList)).thenReturn(resultList);
        calculatorService.startCalculator();
        Assert.assertEquals("INVALID EXPRESSION",resultList.get(0));
        verify(inputOutputService, times(1)).loadInput();
        verify(validationService, times(1)).isParenthesesBalanced(anyString());
        verify(validationService, times(1)).hasConsecutiveOperators(anyString());
        verify(validationService, times(1)).isValidCharAroundOperand(anyString());
        verify(inputOutputService, times(1)).showResult(resultList);
    }

    @Test
    public void testStartCalculatorWithUnBalancedParentheses() {
        List<String> inputList = new ArrayList<>();
        List<String> resultList = new ArrayList<>();
        resultList.add("INVALID EXPRESSION");
        inputList.add("67-9");
        when(inputOutputService.loadInput()).thenReturn(inputList);
        when(validationService.isParenthesesBalanced(anyString())).thenReturn(false);
        when(inputOutputService.showResult(resultList)).thenReturn(resultList);
        calculatorService.startCalculator();
        Assert.assertEquals("INVALID EXPRESSION",resultList.get(0));
        verify(inputOutputService, times(1)).loadInput();
        verify(validationService, times(1)).isParenthesesBalanced(anyString());
        verify(validationService, never()).hasConsecutiveOperators(anyString());
        verify(validationService, never()).isValidCharAroundOperand(anyString());
        verify(inputOutputService, times(1)).showResult(resultList);
    }
    @Test
    public void testStartCalculatorWithConsecutiveOperator() {
        List<String> inputList = new ArrayList<>();
        List<String> resultList = new ArrayList<>();
        resultList.add("INVALID EXPRESSION");
        inputList.add("6*-7-9");
        when(inputOutputService.loadInput()).thenReturn(inputList);
        when(validationService.isParenthesesBalanced(anyString())).thenReturn(true);
        when(validationService.hasConsecutiveOperators(anyString())).thenReturn(true);
        when(inputOutputService.showResult(resultList)).thenReturn(resultList);
        calculatorService.startCalculator();
        Assert.assertEquals("INVALID EXPRESSION",resultList.get(0));
        verify(inputOutputService, times(1)).loadInput();
        verify(validationService, times(1)).isParenthesesBalanced(anyString());
        verify(validationService, times(1)).hasConsecutiveOperators(anyString());
        verify(validationService, never()).isValidCharAroundOperand(anyString());
        verify(inputOutputService, times(1)).showResult(resultList);
    }

}