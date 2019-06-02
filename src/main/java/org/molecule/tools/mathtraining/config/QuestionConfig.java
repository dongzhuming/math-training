package org.molecule.tools.mathtraining.config;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dong Zhuming
 */
@Configuration
@ConfigurationProperties(prefix = "question")
@Data
public class QuestionConfig {
    private Integer blankMode;
    private String blankSlotSymbol;

}
