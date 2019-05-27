package org.molecule.tools.mathtraining.util;

import org.junit.Test;

import static junit.framework.TestCase.assertNotSame;

/**
 * @author Dong Zhuming
 */
public class SnowFlakeIdGeneratorTest {

    @Test
    public void generate() {
        final SnowFlakeIdGenerator snowFlakeIdGenerator = new SnowFlakeIdGenerator();
        Long id1 = (Long)snowFlakeIdGenerator.generate(null, null);
        Long id2 = (Long)snowFlakeIdGenerator.generate(null, null);
        assertNotSame(id1, id2);
    }
}