package org.molecule.tools.mathtraining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author dongzhuming
 */
@SpringBootApplication
@EnableJpaAuditing
public class MathTrainingApplication  {

    public static void main(String[] args) {
        SpringApplication.run(MathTrainingApplication.class, args);
    }

}
