package com.ani.ccyl.leg.commons.utils;


import com.ani.ccyl.leg.commons.dto.QuestionDto;
import com.ani.ccyl.leg.commons.enums.ExceptionEnum;
import com.ani.ccyl.leg.commons.exception.ParseExcelException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by lihui on 17-12-13.
 */
public class ExcelUtil {
    public static QuestionDto readFromExcel(String path) {
        try {
            QuestionDto question = new QuestionDto();
            InputStream inputStream = new FileInputStream(path);
            Workbook workbook = new HSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row:sheet) {
            }
            return question;
        } catch(Exception e) {
            e.printStackTrace();
            throw new ParseExcelException(e.getMessage(), ExceptionEnum.PARSE_EXCEL_ERROR);
        }
    }
}
