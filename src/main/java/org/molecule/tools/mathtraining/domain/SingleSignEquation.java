package org.molecule.tools.mathtraining.domain;

import lombok.Getter;

/**
 * 单符号算式
 *
 * @author Dong Zhuming
 */
@Getter
public class SingleSignEquation implements Equation {

    private Integer firstFigure;
    private Integer secondFigure;
    private EquationType type;
    private Integer result;

    public SingleSignEquation(Integer figure1, Integer figure2, EquationType type) {
        this.firstFigure = figure1;
        this.secondFigure = figure2;
        this.type = type;
    }

    @Override
    public synchronized Integer getResult() {
        if(result != null) {
            return result;
        }
        switch (type) {
            case ADDITION:
                result = firstFigure + secondFigure;
                break;
            case SUBTRACTION:
                result =  firstFigure - secondFigure;
                break;
            case MULTIPLICATION:
                result =  firstFigure * secondFigure;
                break;
            case DIVISION:
                result =  firstFigure / secondFigure;
                break;
            default:
                return null;
        }
        return result;
    }
    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return firstFigure + type.getSign() + secondFigure + '=' + getResult();
    }
}
