package org.molecule.tools.mathtraining.service;

import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.util.Pair;
import org.molecule.tools.mathtraining.config.BasicType;
import org.molecule.tools.mathtraining.config.EquationDefinitionConfig;
import org.molecule.tools.mathtraining.config.EquationDefinitionConfig.Addition;
import org.molecule.tools.mathtraining.config.EquationDefinitionConfig.Division;
import org.molecule.tools.mathtraining.config.EquationDefinitionConfig.Multiplication;
import org.molecule.tools.mathtraining.config.EquationDefinitionConfig.Subtraction;
import org.molecule.tools.mathtraining.domain.EquationType;
import org.molecule.tools.mathtraining.domain.Range;
import org.molecule.tools.mathtraining.domain.SingleSignEquation;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author Dong Zhuming
 */

public class SingleSignEquationGenerator {

    private Addition addition;
    private Subtraction subtraction;
    private Multiplication multiplication;
    private Division division;

    public SingleSignEquationGenerator(EquationDefinitionConfig config) {
        addition = config.getAddition();
        subtraction = config.getSubtraction();
        multiplication = config.getMultiplication();
        division = config.getDivision();
    }

    public SingleSignEquation generate() {
        BasicType type = chooseTypeByWeight();
        BasicTypeDecorator decorator = new BasicTypeDecorator(type);
        SingleSignEquation equation;
        do {
            Integer figure1 = randomInRange(decorator.firstRange);
            Integer figure2 = randomInRange(decorator.secondRange);
            equation = new SingleSignEquation(figure1, figure2, decorator.getEquationType());
        } while (!decorator.resultRange.includes(equation.getResult()));
        return equation;
    }

    private Integer randomInRange(Range range) {
        return new Random().nextInt(range.getEnd() - range.getBegin() + 1) + range.getBegin();
    }

    private BasicType chooseTypeByWeight() {
        final List<Pair<BasicType, Double>> pairs = Stream.of(addition, subtraction, multiplication, division)
                .map(type -> new Pair<>(type, Double.valueOf(type.getWeight())))
                .collect(toList());
        return new EnumeratedDistribution<>(pairs).sample();
    }

    private class BasicTypeDecorator {
        EquationType equationType;
        Range resultRange;
        Range firstRange;
        Range secondRange;

        BasicTypeDecorator(BasicType type) {
            if (type instanceof Addition) {
                equationType = EquationType.ADDITION;
                firstRange = secondRange = Range.of(((Addition) type).getAddendRange());
            } else if (type instanceof Subtraction) {
                equationType = EquationType.SUBTRACTION;
                firstRange = Range.of(((Subtraction) type).getMinuendRange());
                secondRange = Range.of(((Subtraction) type).getSubtrahendRange());
            } else if (type instanceof Multiplication) {
                equationType = EquationType.MULTIPLICATION;
                firstRange = secondRange = Range.of(((Multiplication) type).getMultiplierRange());
            } else if (type instanceof Division) {
                equationType = EquationType.DIVISION;
                firstRange = Range.of(((Division) type).getDividendRange());
                secondRange = Range.of(((Division) type).getDivisorRange());
            } else {
                throw new RuntimeException("未实现的算式" + type.getClass().getName());
            }
            resultRange = Range.of(type.getResultRange());
        }

        EquationType getEquationType() {
            return equationType;
        }
    }
}
