package org.molecule.tools.mathtraining.config;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.molecule.tools.mathtraining.config.EquationDefinitionConfig;
import org.molecule.tools.mathtraining.config.QuestionConfig;

/**
 * @author Dong Zhuming
 */
@AllArgsConstructor
@Data
public class ExerciseConfig {

    @JSONField(name = "question")
    private final QuestionConfig questionConfig;
    @JSONField(name = "equation")
    private final EquationDefinitionConfig equationDefinitionConfig;

}