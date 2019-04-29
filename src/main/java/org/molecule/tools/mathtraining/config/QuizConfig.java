package org.molecule.tools.mathtraining.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dong Zhuming
 */
@Data
@Configuration
@ConfigurationProperties(prefix="quiz")
public class QuizConfig {
    private String sourceTemplate;
    private String outputFile;
    private Integer questionsPerSheet;
    private String beginCells;
    private String sheetNameFormat;
    private Integer rowsPerColumn;
}
