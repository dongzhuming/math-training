package org.molecule.tools.mathtraining.web;

import lombok.RequiredArgsConstructor;
import org.molecule.tools.mathtraining.domain.QuestionVO;
import org.molecule.tools.mathtraining.domain.SingleSignEquationQuestion;
import org.molecule.tools.mathtraining.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dong Zhuming
 */
@RestController
@RequestMapping("/exercise")
@RequiredArgsConstructor
public class ExerciseController {


    private final QuestionService questionService;

    @GetMapping("")
    public List<QuestionVO> startExercise(@RequestParam int count) {
        return questionService.generateSingleSignWithBatch(count).stream()
                .map(SingleSignEquationQuestion::toViewObject)
                .collect(Collectors.toList());
    }
}
