package org.molecule.tools.mathtraining.service;

import lombok.RequiredArgsConstructor;
import org.molecule.tools.mathtraining.config.EquationDefinitionConfig;
import org.molecule.tools.mathtraining.config.ExerciseConfig;
import org.molecule.tools.mathtraining.config.QuestionConfig;
import org.molecule.tools.mathtraining.domain.ConfigurationPO;
import org.molecule.tools.mathtraining.domain.ConfigurationRepository;
import org.springframework.stereotype.Service;

/**
 * @author Dong Zhuming
 */
@Service
@RequiredArgsConstructor
public class ConfigurationService {
    private final EquationDefinitionConfig equationDefinitionConfig;
    private final QuestionConfig questionConfig;
    private final ConfigurationRepository configurationRepository;

    public void writeCurrentIntoDB() {
        final ConfigurationPO configurationPO = new ConfigurationPO();
        configurationPO.setProperties(new ExerciseConfig(questionConfig, equationDefinitionConfig));
        configurationRepository.save(configurationPO);
    }
}
