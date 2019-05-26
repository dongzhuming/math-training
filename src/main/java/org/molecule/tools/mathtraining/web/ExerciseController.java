package org.molecule.tools.mathtraining.web;

import lombok.RequiredArgsConstructor;
import org.molecule.tools.mathtraining.service.ExerciseService;
import org.molecule.tools.mathtraining.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dong Zhuming
 */
@RestController
@RequestMapping("/api/exercise")
@RequiredArgsConstructor
public class ExerciseController {

    private final QuestionService questionService;
    private final ExerciseService exerciseService;

    @PostMapping("/start")
    public ResponseEntity startExercise(@RequestParam int count) {
        return ResponseEntity.ok(exerciseService.start(count));
    }

    @PostMapping("/finish")
    public ResponseEntity finishExercise(@RequestParam Long exerciseId, @RequestParam Integer totalCount) {
        exerciseService.finish(exerciseId, totalCount);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/wrong")
    public ResponseEntity recordWrong(@RequestParam Long exerciseId, @RequestParam String questionCode, @RequestParam Integer penaltyCount) {
        return ResponseEntity.ok(exerciseService.recordWrong(exerciseId, questionCode, penaltyCount));
    }
}
