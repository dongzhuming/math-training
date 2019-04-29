package org.molecule.tools.mathtraining.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.molecule.tools.mathtraining.config.QuestionConfig;

import static org.junit.Assert.*;

/**
 * @author Dong Zhuming
 */
@RunWith(JUnit4.class)
public class SingleSignEquationQuestionTest {

    @Test
    public void testConstructor() {
        final SingleSignEquation equation = new SingleSignEquation(10, 15, EquationType.ADDITION);
        QuestionConfig config = new QuestionConfig();
        config.setBlankMode(0);
        final SingleSignEquationQuestion question = new SingleSignEquationQuestion(equation, config);
        assertEquals(3, (int)question.getBlankSlot());
    }

    @Test
    public void testToString() {
        final SingleSignEquation equation = new SingleSignEquation(26, 15, EquationType.SUBTRACTION);
        QuestionConfig config = new QuestionConfig();
        config.setBlankMode(1);
        final SingleSignEquationQuestion question = new SingleSignEquationQuestion(equation, config);
        Integer blankSlot = question.getBlankSlot();
        String result = question.toString();
        if(blankSlot == 1) {
            assertEquals("____-15=11", result);
        } else if(blankSlot == 2) {
            assertEquals("26-____=11", result);
        } else if(blankSlot == 3) {
            assertEquals("26-15=____", result);
        } else {
            fail("填空位数出现错误：" + blankSlot);
        }
    }

    @Test
    public void testToStringWithBlankSlotSymbol() {
        final SingleSignEquation equation = new SingleSignEquation(3, 7, EquationType.MULTIPLICATION);
        QuestionConfig config = new QuestionConfig();
        config.setBlankMode(1);
        config.setBlankSlotSymbol("?");
        final SingleSignEquationQuestion question = new SingleSignEquationQuestion(equation, config);
        Integer blankSlot = question.getBlankSlot();
        String result = question.toString();
        if(blankSlot == 1) {
            assertEquals("?×7=21", result);
        } else if(blankSlot == 2) {
            assertEquals("3×?=21", result);
        } else if(blankSlot == 3) {
            assertEquals("3×7=?", result);
        } else {
            fail("填空位数出现错误：" + blankSlot);
        }
    }
}