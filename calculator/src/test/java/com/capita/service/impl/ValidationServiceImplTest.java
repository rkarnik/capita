package com.capita.service.impl;

import com.capita.configuration.CalculatorAppConfig;
import com.capita.service.CalculatorService;
import com.capita.service.ValidationService;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CalculatorAppConfig.class, loader = AnnotationConfigContextLoader.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
public class ValidationServiceImplTest  {

    @InjectMocks
    @Autowired
    ValidationService validationService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testIsParenthesesBalancedWithValidParentheses() {
        Assert.assertTrue(validationService.isParenthesesBalanced("(7+(6*5)^(2+3)-4/2)"));
    }

    @Test
    public void testIsParenthesesBalancedWithEmptyParenthes() {
        Assert.assertTrue(validationService.isParenthesesBalanced("(7+(6*5)^(2+3)-4/2)"));
    }

    @Test
    public void testIsParenthesesBalancedWithInValidParentheses() {
        Assert.assertFalse(validationService.isParenthesesBalanced("7+(6*5^((2+3)-4/2)"));
    }

    @Test
    public void testIsParenthesesBalancedWithInValidParentheses2() {
        Assert.assertFalse(validationService.isParenthesesBalanced("7+(6*5))^((2+3)-4/2)"));
    }
    @Test
    public void testHasConsecutiveOperators() {
        Assert.assertFalse(validationService.hasConsecutiveOperators("7+(6*5)^(2+3)-4/2"));
    }

    @Test
    public void testHasConsecutiveOperators2() {
        Assert.assertTrue(validationService.hasConsecutiveOperators("7+(6*5)-^(2+3)-4/2"));
    }

    @Test
    public void testHasConsecutiveOperators3() {
        Assert.assertTrue(validationService.hasConsecutiveOperators("7(+6)"));
    }
    @Test
    public void testHasConsecutiveOperatorsWithOperatorAtStartOfExpression() {
        Assert.assertTrue(validationService.hasConsecutiveOperators("+7-6"));
    }

    @Test
    public void testHasConsecutiveOperatorsWithOperatorAtEndOfExpression() {
        Assert.assertTrue(validationService.hasConsecutiveOperators("7-6+"));
    }

    @Test
    public void testIsValidCharAroundOperand() {
        Assert.assertTrue(validationService.isValidCharAroundOperand("(7-6)"));
    }

    @Test
    public void testIsValidCharAroundOperandWithClosingBraceBeforeOperand() {
        Assert.assertFalse(validationService.isValidCharAroundOperand("(7-2)6"));
    }

    @Test
    public void testIsValidCharAroundOperandWithDigitBeforeOperand() {
        Assert.assertFalse(validationService.isValidCharAroundOperand("27-6"));
    }
    @Test
    public void testIsValidCharAroundOperandWithOpeningBraceAfterOperand() {
        Assert.assertFalse(validationService.isValidCharAroundOperand("7-2(6-1)"));
    }

    @Test
    public void testIsValidCharAroundOperandWithDigitAfterOperand() {
        Assert.assertFalse(validationService.isValidCharAroundOperand("27-6"));
    }

}