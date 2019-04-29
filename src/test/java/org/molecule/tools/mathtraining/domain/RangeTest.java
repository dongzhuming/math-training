package org.molecule.tools.mathtraining.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * @author Dong Zhuming
 */
@RunWith(JUnit4.class)
public class RangeTest {
    @Test
    public void testContractor() {
        Range range = Range.of("10-29");
        assertEquals(10, (int)range.getBegin());
        assertEquals(29, (int)range.getEnd());
    }

    @Test
    public void testIncludes() {
        Range range = Range.of("10-29");
        assertTrue(range.includes(10));
        assertTrue(range.includes(15));
        assertTrue(range.includes(29));
    }

    @Test
    public void testNotIncludes() {
        Range range = Range.of("10-29");
        assertFalse(range.includes(9));
        assertFalse(range.includes(49));
    }

    @Test(expected = Exception.class)
    public void testIllegalValue1() {
        Range.of("10-=29");
    }
    @Test(expected = Exception.class)
    public void testIllegalValue2() {
        Range.of("10");
    }
    @Test(expected = Exception.class)
    public void testIllegalValue3() {
        Range.of("10-10");
    }
    @Test(expected = Exception.class)
    public void testIllegalValue4() {
        Range.of("10-20-30");
    }
}