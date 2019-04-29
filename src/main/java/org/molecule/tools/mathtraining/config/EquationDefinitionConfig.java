package org.molecule.tools.mathtraining.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dong Zhuming
 */
@Configuration
@ConfigurationProperties(prefix = "equation")
@Getter
@Setter
public class EquationDefinitionConfig {

    private int blankMode;
    private Addition addition;
    private Subtraction subtraction;
    private Multiplication multiplication;
    private Division division;

    @Getter
    @Setter
    public static class Addition extends BasicType {
        private String addendRange;
    }
    @Getter
    @Setter
    public static class Subtraction extends BasicType {
        /**
         * 被减数
         */
        private String minuendRange;
        /**
         * 减数
         */
        private String subtrahendRange;
    }
    @Getter
    @Setter
    public static class Multiplication extends BasicType {
        private String multiplierRange;
    }
    @Getter
    @Setter
    public static class Division extends BasicType {
        /**
         * 被除数
         */
        private String dividendRange;
        /**
         * 除数
         */
        private String divisorRange;
    }


}
