package com.capita.configuration;

import com.capita.service.CalculatorService;
import com.capita.service.InputOutputService;
import com.capita.service.ValidationService;
import com.capita.service.impl.CalculatorServiceImpl;
import com.capita.service.impl.InputOutputServiceImpl;
import com.capita.service.impl.ValidationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculatorAppConfig {

    @Bean
    public InputOutputService inputOutputService() {
        return new InputOutputServiceImpl();
    }

    @Bean
    public CalculatorService calculationService() {
        return new CalculatorServiceImpl();
    }

    @Bean
    public ValidationService validationService() {
        return new ValidationServiceImpl();
    }

}
