package org.molecule.tools.mathtraining.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.molecule.tools.mathtraining.config.QuizConfig;
import org.molecule.tools.mathtraining.domain.Question;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Dong Zhuming
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ExcelService {

    private final QuizConfig quizConfig;

    public void createDocument(List<? extends Question> questions) throws IOException {
        generate(questions, quizConfig);
    }

    public File downloadDocument(List<? extends Question> questions, QuizConfig _quizConfig) throws IOException {
        return generate(questions, _quizConfig);
    }

    private File generate(List<? extends Question> questions, QuizConfig _quizConfig) throws IOException {
        Queue<? extends Question> queue = new LinkedList<>(questions);
        final String sourceTemplate = _quizConfig.getSourceTemplate();

        log.info("打开模板文件[{}]", sourceTemplate);
        InputStream inputStream = new ClassPathResource(sourceTemplate).getInputStream();

        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
            Sheet sheet0 = workbook.getSheetAt(0);
            workbook.setSheetName(0, String.format(_quizConfig.getSheetNameFormat(), 1));
            int pages = (int) Math.ceil((double) questions.size() / _quizConfig.getQuestionsPerSheet());
            Stream.concat(
                    Stream.of(sheet0),
                    IntStream.range(1, pages).boxed()
                            .map(page -> {
                                Sheet sheet = workbook.cloneSheet(0);
                                workbook.setSheetName(page, String.format(_quizConfig.getSheetNameFormat(), page + 1));
                                return sheet;
                            }))
                    .forEach(sheet -> {
                        for (int i = 0; i < _quizConfig.getQuestionsPerSheet() && !queue.isEmpty(); i++) {
                            setValueToSheet(sheet, queue.poll(), _quizConfig, i);
                        }
                    });

            File outputFile = StringUtils.isBlank(_quizConfig.getOutputFile()) ?
                    File.createTempFile("training-", ".xlsx") :
                    new File(_quizConfig.getOutputFile());
            try (FileOutputStream os = new FileOutputStream(outputFile)) {
                workbook.write(os);
                log.info("文件已输出至[{}]", outputFile.getAbsolutePath());
            }
            return outputFile;
        }
    }

    private void setValueToSheet(Sheet sheet, Question question, QuizConfig quizConfig, int position) {
        Assert.notNull(sheet, "sheet不应为空");
        Assert.notNull(question, "question不应为空");
        int rowPerColumn = quizConfig.getRowsPerColumn();
        String[] beginCells = quizConfig.getBeginCells().split(",");
        int currentColumn = position / rowPerColumn;
        int currentRow = position % rowPerColumn;
        int columnIndex = beginCells[currentColumn].toUpperCase().charAt(0) - 'A';
        int rowIndex = Integer.valueOf(beginCells[currentColumn].substring(1)) + currentRow - 1;
        sheet.getRow(rowIndex).getCell(columnIndex).setCellValue(question.formulaString());
    }
}
