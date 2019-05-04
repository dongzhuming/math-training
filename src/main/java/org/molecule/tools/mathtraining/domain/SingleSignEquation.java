package org.molecule.tools.mathtraining.domain;

import lombok.Getter;

/**
 * 单符号算式
 *
 * @author Dong Zhuming
 */
@Getter
public class SingleSignEquation implements Equation {

    private Integer firstItem;
    private Integer secondItem;
    private EquationType type;
    private Integer resultItem;

    public SingleSignEquation(Integer figure1, Integer figure2, EquationType type) {
        this.firstItem = figure1;
        this.secondItem = figure2;
        this.type = type;
    }

    @Override
    public synchronized Integer getResultItem() {
        if(resultItem != null) {
            return resultItem;
        }
        switch (type) {
            case ADDITION:
                resultItem = firstItem + secondItem;
                break;
            case SUBTRACTION:
                resultItem =  firstItem - secondItem;
                break;
            case MULTIPLICATION:
                resultItem =  firstItem * secondItem;
                break;
            case DIVISION:
                resultItem =  firstItem / secondItem;
                break;
            default:
                return null;
        }
        return resultItem;
    }

    @Override
    public String getItem(int index) {
        if(index == 1) {
            return String.valueOf(this.firstItem);
        } else if (index == 2) {
            return String.valueOf(this.secondItem);
        } else {
            return String.valueOf(this.resultItem);
        }
    }

    @Override
    public String toString() {
        return firstItem + type.getSign() + secondItem + '=' + this.getResultItem();
    }
}
