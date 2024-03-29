package com.ani.ccyl.leg.commons.utils;


import com.ani.ccyl.leg.commons.dto.QuestionDto;
import com.ani.ccyl.leg.commons.dto.TotalAwardsDto;
import com.ani.ccyl.leg.commons.enums.AwardTypeEnum;
import com.ani.ccyl.leg.commons.enums.ExceptionEnum;
import com.ani.ccyl.leg.commons.enums.QuestionTypeEnum;
import com.ani.ccyl.leg.commons.exception.ParseExcelException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
            List<QuestionDto> questions = new ArrayList<>();
            Sheet sheet = getWorkBook(path).getSheetAt(0);
            if(QuestionTypeEnum.CHOICE.getCode().equals(type.getCode())) {
                Iterator<Row> iterator = sheet.iterator();
                while (iterator.hasNext()) {
                    Row row = iterator.next();
                    QuestionDto questionDto = new QuestionDto();
                    questionDto.setContent(row.getCell(0).getStringCellValue());
                    questionDto.setOptionOne(row.getCell(1).getStringCellValue());
                    questionDto.setOptionTwo(row.getCell(2).getStringCellValue());
                    questionDto.setOptionThree(row.getCell(3).getStringCellValue());
                    questionDto.setAnswer(row.getCell(4).getStringCellValue().trim());
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
            } else if (QuestionTypeEnum.NINETEENCHOICE.getCode().equals(type.getCode())){
                Iterator<Row> iterator = sheet.iterator();
                while (iterator.hasNext()) {
                    Row row = iterator.next();
                    QuestionDto questionDto = new QuestionDto();
                    questionDto.setContent(row.getCell(0).getStringCellValue());
                    questionDto.setOptionOne(row.getCell(1).getStringCellValue());
                    questionDto.setOptionTwo(row.getCell(2).getStringCellValue());
                    questionDto.setOptionThree(row.getCell(3).getStringCellValue());
                    questionDto.setAnswer(row.getCell(4).getStringCellValue().trim());
                    questionDto.setType(type);
                    questionDto.setFileId(fileId);
                    questions.add(questionDto);
                }
            } else if (QuestionTypeEnum.NINETEENJUDGEMENT.getCode().equals(type.getCode())){
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
    public static List<TotalAwardsDto> readAwardsFromExcel(String path,AwardTypeEnum type) {
        List<TotalAwardsDto> awardsDtos = new ArrayList<>();
        try {
            Sheet sheet = getWorkBook(path).getSheetAt(0);
            if(sheet != null)
            for(Row row:sheet) {
                String cell1 = row.getCell(0).getStringCellValue();
                String cell2 = row.getCell(1).getStringCellValue();
                if(!StringUtils.isEmpty(cell1)) {
                    TotalAwardsDto totalAwardsDto = new TotalAwardsDto();
                    totalAwardsDto.setType(type);
                    totalAwardsDto.setProdId(cell1);
                    totalAwardsDto.setCodeSecret(cell2);
                    awardsDtos.add(totalAwardsDto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ParseExcelException(e.getMessage(),ExceptionEnum.PARSE_EXCEL_ERROR);
        }
        return awardsDtos;
    }

    private static Workbook getWorkBook(String path) throws IOException, InvalidFormatException {
        Workbook workbook = null;

        try {
            InputStream inputStream = new FileInputStream(path);
            workbook = new HSSFWorkbook(inputStream);
        }catch (Exception e) {
            InputStream inputStream = new FileInputStream(path);
            workbook = new XSSFWorkbook(OPCPackage.open(inputStream));
        }
        return workbook;
    }
}
