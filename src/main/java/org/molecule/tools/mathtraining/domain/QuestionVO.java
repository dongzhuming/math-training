package org.molecule.tools.mathtraining.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * 问题的视图对象
 * @author Dong Zhuming
 */
@Builder
@Getter
public class QuestionVO {

    private String title;
    private String answer;

}
