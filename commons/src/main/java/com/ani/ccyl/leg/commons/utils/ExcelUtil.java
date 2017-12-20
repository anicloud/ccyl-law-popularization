package com.ani.ccyl.leg.commons.utils;


import com.ani.ccyl.leg.commons.dto.QuestionDto;
import com.ani.ccyl.leg.commons.enums.ExceptionEnum;
import com.ani.ccyl.leg.commons.enums.QuestionTypeEnum;
import com.ani.ccyl.leg.commons.exception.ParseExcelException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lihui on 17-12-13.
 */
public class ExcelUtil {
    public static List<QuestionDto> readFromExcel(QuestionTypeEnum type, String path, Integer fileId) {
        try {
            InputStream inputStream = new FileInputStream(path);
            List<QuestionDto> questions = new ArrayList<>();
            Workbook workbook;
            if(path.substring(path.lastIndexOf(".")).equals("xls"))
                workbook = new HSSFWorkbook(inputStream);
            else
                workbook = new XSSFWorkbook(OPCPackage.open(inputStream));
            Sheet sheet = workbook.getSheetAt(0);
            if(QuestionTypeEnum.CHOICE.getCode().equals(type.getCode())) {
                Iterator<Row> iterator = sheet.iterator();
                iterator.next();
                while (iterator.hasNext()) {
                    Row row = iterator.next();
                    QuestionDto questionDto = new QuestionDto();
                    questionDto.setQuestionNo((int)row.getCell(0).getNumericCellValue());
                    questionDto.setContent(row.getCell(1).getStringCellValue());
                    questionDto.setOptionOne(row.getCell(2).getStringCellValue());
                    questionDto.setOptionTwo(row.getCell(3).getStringCellValue());
                    questionDto.setOptionThree(row.getCell(4).getStringCellValue());
                    questionDto.setAnswer(row.getCell(5).getStringCellValue().trim());
                    questionDto.setType(type);
                    questionDto.setFileId(fileId);
                    questions.add(questionDto);
                }
            } else if(QuestionTypeEnum.JUDGEMENT.getCode().equals(type.getCode())) {
                for (Row row : sheet) {
                    QuestionDto questionDto = new QuestionDto();
                    questionDto.setContent(row.getCell(0).getStringCellValue());
                    questionDto.setOptionOne("Y");
                    questionDto.setOptionTwo("N");
                    questionDto.setAnswer(row.getCell(1).getStringCellValue().trim());
                    questionDto.setType(type);
                    questionDto.setFileId(fileId);
                    questions.add(questionDto);
                }
            }
            return questions;
        } catch(Exception e) {
            e.printStackTrace();
            throw new ParseExcelException(e.getMessage(), ExceptionEnum.PARSE_EXCEL_ERROR);
        }
    }
}
