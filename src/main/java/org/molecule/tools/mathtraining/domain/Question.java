package org.molecule.tools.mathtraining.domain;

/**
 * @author Dong Zhuming
 */
public interface Question {
    /**
     * @return 等式字符串
     */
    String formulaString();

    QuestionVO toViewObject();

    String encode();
}
