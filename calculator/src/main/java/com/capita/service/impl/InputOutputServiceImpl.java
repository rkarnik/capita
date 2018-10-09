package com.capita.service.impl;

import com.capita.exceptions.InvalidInputException;
import com.capita.service.InputOutputService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class InputOutputServiceImpl implements InputOutputService {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public List<String> loadInput() {
        System.out.println("Enter number of expressions to be evaluated in first line, followed by the expressions");
        int numberOfExpressions = Integer.parseInt(scanner.nextLine().trim());

        if (numberOfExpressions > 100 || numberOfExpressions < 1)
            throw new InvalidInputException("Number of expression to be evaluated should be 1 and 100");

        List<String> inputExpressionList = new ArrayList<String>();

        for (int i = 0; i < numberOfExpressions; i++)
            inputExpressionList.add(scanner.nextLine());

        return inputExpressionList;
    }

    @Override
    public List<String> showResult(List<String> resultList) {
        for (int i = 0; i < resultList.size(); i++)
            System.out.println("CASE #" + (i + 1) + ": " + resultList.get(i));
        return resultList;
    }
}
