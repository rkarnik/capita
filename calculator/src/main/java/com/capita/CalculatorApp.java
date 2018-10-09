package com.capita;

import com.capita.configuration.CalculatorAppConfig;
import com.capita.service.impl.CalculatorServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class CalculatorApp {


    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CalculatorAppConfig.class);
        CalculatorServiceImpl calculatorService = context.getBean(CalculatorServiceImpl.class);
        calculatorService.startCalculator();
        //close the context
        ((AnnotationConfigApplicationContext) context).close();
    }
}
