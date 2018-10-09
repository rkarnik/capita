package com.capita.service;

import java.util.List;

public interface InputOutputService {

    /**
     *
     * @return list of mathematical expressions to be evaluated
     */
    List<String> loadInput();

    /**
     *
     * @param resultList list containing result of evaluation of each expression
     * @return list containing result of evaluation of each expression
     */
    List<String> showResult(List<String> resultList);
}
