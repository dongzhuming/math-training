package org.molecule.tools.mathtraining.web;

import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.IOUtils;
import org.molecule.tools.mathtraining.config.QuizConfig;
import org.molecule.tools.mathtraining.domain.SingleSignEquationQuestion;
import org.molecule.tools.mathtraining.service.ExcelService;
import org.molecule.tools.mathtraining.service.QuestionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author Dong Zhuming
 */
@RestController
@RequiredArgsConstructor
public class DownloadController {

    private final ExcelService excelService;
    private final QuizConfig quizConfig;
    private final QuestionService questionService;

    @RequestMapping("/download")
    public String downloadFile(@RequestParam(defaultValue = "40") Integer count, HttpServletResponse response) throws Exception {
        final List<SingleSignEquationQuestion> questions = questionService.generateSingleSignWithBatch(count);
        // excel输出至临是目录
        quizConfig.setOutputFile(null);
        final File file = excelService.downloadDocument(questions, quizConfig);
        // 设置强制下载不打开
        response.setContentType("application/force-download");
        // 设置文件名
        response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());
        try (FileInputStream is = new FileInputStream(file);
             OutputStream os = response.getOutputStream()) {
            IOUtils.copy(is, os);
            return "succeed";
        } catch (Exception e) {
            return "failed：" + e.getMessage();
        }
    }
}
