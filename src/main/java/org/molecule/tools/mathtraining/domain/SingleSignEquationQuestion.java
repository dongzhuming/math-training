package org.molecule.tools.mathtraining.domain;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.molecule.tools.mathtraining.config.Constants;
import org.molecule.tools.mathtraining.config.QuestionConfig;

import java.util.Random;

/**
 * @author Dong Zhuming
 */
@Getter
public class SingleSignEquationQuestion implements Question {
    /**
     * 填空位置，1-第一位（如被减数），2-第二位，3-最右边
     */
    private Integer blankSlot;
    private SingleSignEquation equation;
    private String blankSlotSymbol;

    public SingleSignEquationQuestion(SingleSignEquation equation, QuestionConfig config) {
        this.equation = equation;
        blankSlot = config.getBlankMode() == 1 ? new Random().nextInt(2) + 1 : 3;
        blankSlotSymbol = StringUtils.isNotEmpty(config.getBlankSlotSymbol()) ? config.getBlankSlotSymbol() : Constants.BLANK_SLOT_SYMBOL;
    }

    @Override
    public String toString() {
        return formulaString();
    }

    public void print() {
        System.out.println(formulaString());
    }

    @Override
    public String formulaString() {
        return (blankSlot == 1 ? blankSlotSymbol : equation.getFirstItem())
                + equation.getType().getSign()
                + (blankSlot == 2 ? blankSlotSymbol : equation.getSecondItem())
                + '='
                + (blankSlot == 3 ? blankSlotSymbol : equation.getResultItem());
    }

    @Override
    public QuestionVO toViewObject() {
        return QuestionVO.builder()
                .title(this.formulaString())
                .answer(equation.getItem(blankSlot))
                .build();
    }

}
