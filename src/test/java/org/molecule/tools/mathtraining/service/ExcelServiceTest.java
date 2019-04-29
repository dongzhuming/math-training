package org.molecule.tools.mathtraining.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.molecule.tools.mathtraining.domain.SingleSignEquationQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

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
        File file = excelService.createDocument(questions);
        assertTrue(file.exists());
    }

}