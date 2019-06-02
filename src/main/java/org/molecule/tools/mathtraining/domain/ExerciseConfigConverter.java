package org.molecule.tools.mathtraining.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeCreator;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.extern.slf4j.Slf4j;
import org.molecule.tools.mathtraining.config.EquationDefinitionConfig;
import org.molecule.tools.mathtraining.config.ExerciseConfig;
import org.molecule.tools.mathtraining.config.QuestionConfig;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.List;

/**
 * @author Dong Zhuming
 */
@Slf4j
public class ExerciseConfigConverter implements AttributeConverter<ExerciseConfig, String> {

    @Override
    public String convertToDatabaseColumn(ExerciseConfig attribute) {
        PropertyFilter filter = (source, name, value) -> {
            return List.of("addendRange",
                    "minuendRange",
                    "subtrahendRange",
                    "multiplierRange",
                    "dividendRange",
                    "divisorRange",
                    "weight",
                    "resultRange",
                    "question",
                    "equation",
                    "blankMode",
                    "blankSlotSymbol",
                    "blankMode", "addition", "subtraction", "division", "multiplication").contains(name);
        };
        try {
            return JSON.toJSONString(attribute, filter);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

    }

    @Override
    public ExerciseConfig convertToEntityAttribute(String dbData) {
        return JSON.parseObject(dbData, ExerciseConfig.class);
    }
}