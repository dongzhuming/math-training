package org.molecule.tools.mathtraining;

import lombok.RequiredArgsConstructor;
import org.molecule.tools.mathtraining.config.EquationDefinitionConfig;
import org.molecule.tools.mathtraining.config.ExerciseConfig;
import org.molecule.tools.mathtraining.config.QuestionConfig;
import org.molecule.tools.mathtraining.domain.ConfigurationPO;
import org.molecule.tools.mathtraining.domain.ConfigurationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author dongzhuming
 */
@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
public class MathTrainingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MathTrainingApplication.class, args);
    }

}
