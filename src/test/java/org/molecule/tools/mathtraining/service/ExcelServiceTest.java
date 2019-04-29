package org.molecule.tools.mathtraining.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.molecule.tools.mathtraining.config.QuizConfig;
import org.molecule.tools.mathtraining.domain.SingleSignEquationQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Dong Zhuming
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ExcelServiceTest {
    @Autowired
    private ExcelService excelService;
    @Autowired
    private QuestionService questionService;

    @Test
    public void testCreateSheet() throws Exception {
        final List<SingleSignEquationQuestion> questions = questionService.generateSingleSignWithBatch(1000);
        excelService.createDocument(questions);
    }

}