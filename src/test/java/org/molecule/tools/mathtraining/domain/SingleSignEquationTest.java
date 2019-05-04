package org.molecule.tools.mathtraining.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * @author Dong Zhuming
 */
@RunWith(JUnit4.class)
public class SingleSignEquationTest {

    @Test
    public void testAddition() {
        final SingleSignEquation equation = new SingleSignEquation(10, 10, EquationType.ADDITION);
        assertEquals(20, (int) equation.getResultItem());
    }

    @Test
    public void testToString() {
        final SingleSignEquation equation = new SingleSignEquation(10, 20, EquationType.ADDITION);
        assertEquals("10+20=30", equation.toString());
    }
}