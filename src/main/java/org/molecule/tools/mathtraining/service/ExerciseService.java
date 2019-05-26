package org.molecule.tools.mathtraining.service;

import lombok.RequiredArgsConstructor;
import org.molecule.tools.mathtraining.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Dong Zhuming
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Throwable.class)
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final QuestionRepository questionRepository;
    private final QuestionService questionService;

    public Map start(Integer initialCount) {
        final ExercisePO exercisePO = new ExercisePO();
        exercisePO.setStartTime(LocalDateTime.now());
        exercisePO.setInitialCount(initialCount);
        exercisePO.setTotalCount(0);
        exercisePO.setWrongCount(0);
        exerciseRepository.save(exercisePO);
        final List<QuestionVO> questions = createQuestions(initialCount);
        questions.forEach(question -> questionRepository.save(QuestionPO.create(question)));
        return Map.of("id", exercisePO.getId(), "questions", questions);
    }

    public void finish(Long exerciseId, Integer totalCount) {
        final ExercisePO exercisePO = exerciseRepository.getOne(exerciseId);
        exercisePO.setFinishTime(LocalDateTime.now());
        exercisePO.setTotalCount(totalCount);
        exerciseRepository.save(exercisePO);
    }

    public List<QuestionVO> recordWrong(Long exerciseId, String questionCode, Integer penaltyCount) {
        final ExercisePO exercisePO = exerciseRepository.getOne(exerciseId);
        exercisePO.addWrong();
        questionRepository.findFirstByCode(questionCode)
                .ifPresent(question -> {
                    question.addWrong();
                    questionRepository.save(question);
                });

        final List<QuestionVO> questions = createQuestions(penaltyCount);
        questions.forEach(question -> questionRepository.save(QuestionPO.create(question)));
        return questions;
    }

    private List<QuestionVO> createQuestions(Integer count) {
        return questionService.generateSingleSignWithBatch(count)
                .stream()
                .map(SingleSignEquationQuestion::toViewObject)
                .collect(Collectors.toList());
    }
}
