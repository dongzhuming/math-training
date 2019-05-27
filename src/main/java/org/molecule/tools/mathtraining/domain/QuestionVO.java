package org.molecule.tools.mathtraining.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Question的视图对象
 * @author Dong Zhuming
 */
@Builder
@Getter
@EqualsAndHashCode
@ToString
public class QuestionVO {

    private String code;
    private String title;
    private String answer;
    private String[] items;

}
