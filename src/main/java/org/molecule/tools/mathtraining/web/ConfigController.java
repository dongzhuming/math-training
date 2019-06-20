package org.molecule.tools.mathtraining.web;

import lombok.RequiredArgsConstructor;
import org.molecule.tools.mathtraining.service.ConfigurationService;
import org.molecule.tools.mathtraining.service.ExerciseService;
import org.molecule.tools.mathtraining.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Dong Zhuming
 */
@RestController
@RequestMapping("/api/config")
@RequiredArgsConstructor
public class ConfigController {

    private final ConfigurationService configurationService;

    @PostMapping("/flush")
    public ResponseEntity flush() {
        configurationService.writeCurrentIntoDB();
        return ResponseEntity.ok("OK");
    }

}
