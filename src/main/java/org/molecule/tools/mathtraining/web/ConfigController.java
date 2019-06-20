package org.molecule.tools.mathtraining.web;

import lombok.RequiredArgsConstructor;
import org.molecule.tools.mathtraining.service.ConfigurationService;
import org.molecule.tools.mathtraining.service.ExerciseService;
import org.molecule.tools.mathtraining.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Dong Zhuming
 */
@RestController
@RequestMapping("/api/config")
@RequiredArgsConstructor
public class ConfigController {

    private final ConfigurationService configurationService;

    @GetMapping("/flush")
    public ResponseEntity flush() {
        configurationService.writeCurrentIntoDB();
        return ResponseEntity.ok("OK");
    }

}
