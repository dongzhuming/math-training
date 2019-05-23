package org.molecule.tools.mathtraining.service;

import lombok.RequiredArgsConstructor;
import org.molecule.tools.mathtraining.config.EquationDefinitionConfig;
import org.molecule.tools.mathtraining.config.QuestionConfig;
import org.molecule.tools.mathtraining.domain.SingleSignEquation;
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
public class QuestionService {
    private final QuestionConfig questionConfig;
    private final EquationDefinitionConfig equationDefinitionConfig;

    public SingleSignEquationQuestion generateSingleSign() {
        SingleSignEquationGenerator generator = new SingleSignEquationGenerator(equationDefinitionConfig);
        return new SingleSignEquationQuestion(generator.generate(), questionConfig);
    }

    public List<SingleSignEquationQuestion> generateSingleSignWithBatch(int count) {
        if(count == 1) {
            return List.of(generateSingleSign());
        }
        SingleSignEquationGenerator generator = new SingleSignEquationGenerator(equationDefinitionConfig);
        return IntStream.rangeClosed(1, count).boxed()
                .map(i-> generator.generate())
                .map(e -> new SingleSignEquationQuestion(generator.generate(), questionConfig))
                .collect(Collectors.toList());
    }
}
