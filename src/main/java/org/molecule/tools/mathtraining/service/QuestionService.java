package org.molecule.tools.mathtraining.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.molecule.tools.mathtraining.config.EquationDefinitionConfig;
import org.molecule.tools.mathtraining.config.ExerciseConfig;
import org.molecule.tools.mathtraining.config.QuestionConfig;
import org.molecule.tools.mathtraining.domain.SingleSignEquationQuestion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Dong Zhuming
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {

    private final QuestionConfig questionConfig;
    private final EquationDefinitionConfig equationDefinitionConfig;

    public SingleSignEquationQuestion generateSingleSign() {
        SingleSignEquationGenerator generator = new SingleSignEquationGenerator(equationDefinitionConfig);
        return new SingleSignEquationQuestion(generator.generate(), questionConfig);
    }

    public List<SingleSignEquationQuestion> generateSingleSignWithBatch(int count) {
        SingleSignEquationGenerator generator = new SingleSignEquationGenerator(equationDefinitionConfig);
        return IntStream.rangeClosed(1, count).boxed()
                .map(i -> generator.generate())
                .map(e -> new SingleSignEquationQuestion(generator.generate(), questionConfig))
                .collect(Collectors.toList());
    }

    public SingleSignEquationQuestion generateSingleSign(ExerciseConfig config) {
        if (config == null) {
            log.warn("没有找到配置信息，使用应用默认配置");
            return generateSingleSign();
        }
        SingleSignEquationGenerator generator = new SingleSignEquationGenerator(config.getEquationDefinitionConfig());
        return new SingleSignEquationQuestion(generator.generate(), config.getQuestionConfig());
    }

    public List<SingleSignEquationQuestion> generateSingleSignWithBatch(int count, ExerciseConfig config) {
        if (config == null) {
            log.warn("没有找到配置信息，使用应用默认配置");
            return generateSingleSignWithBatch(count);
        }
        SingleSignEquationGenerator generator = new SingleSignEquationGenerator(config.getEquationDefinitionConfig());
        return IntStream.rangeClosed(1, count).boxed()
                .map(i -> generator.generate())
                .map(e -> new SingleSignEquationQuestion(generator.generate(), config.getQuestionConfig()))
                .collect(Collectors.toList());
    }
}
