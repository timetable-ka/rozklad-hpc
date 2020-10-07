package com.example.demo.converter;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.stream.Stream;

@Component
public class ExportService {

    private static final Logger logger = LoggerFactory.getLogger(ExportService.class);

    public void convertExcel() {
        XSSFWorkbook myExcelBook = getSheets();

        Stream.of(1).forEach(course -> {
//        Stream.of(1, 2, 3, 4).forEach(course -> {
            logger.info(course + "////////////////////////////");
            processSheet(myExcelBook, course);
        });

    }

    private XSSFWorkbook getSheets() {
        XSSFWorkbook myExcelBook = null;
        try {
            File file = ResourceUtils.getFile("classpath:rozklad-source/rozklad.xlsx");
            myExcelBook = new XSSFWorkbook(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myExcelBook;
    }

    private void processSheet(XSSFWorkbook myExcelBook, Integer course) {
        if (myExcelBook != null) {
            XSSFSheet sheet = myExcelBook.getSheetAt(course - 1);

            int firstColumn = 4;
            int count = 0;
            while (true) {
                int rownum = 7;
                XSSFCell cell = sheet.getRow(rownum - 1).getCell(firstColumn - 1);
                if (cell == null || !CellType.STRING.equals(cell.getCellType())) {
                    cell = sheet.getRow(rownum - 1).getCell(firstColumn);
                    if (cell == null || !CellType.STRING.equals(cell.getCellType())) {
                        logger.info("Before exit rownum = " + rownum + " firstColumn = " + firstColumn);
                        return;
                    }
                }
                String message = ++count + " " + cell.getStringCellValue();
                logger.info(message);

                int columnIndex = cell.getColumnIndex();
                int rowIndex = cell.getRowIndex() - 2;

                for (int i = 1; i <= 20; i++) {
                    openOneGroup(i, sheet, columnIndex, rowIndex + 4 * i);
                }

                firstColumn += 3;
            }
        }
    }

    private void openOneGroup(int i, XSSFSheet sheet, int columnIndex, int rowIndex) {
        logger.info("=-=-=-=-=-=");
        printRow(i, sheet, columnIndex, rowIndex - 1);
        printRow(i, sheet, columnIndex, rowIndex);
        printRow(i, sheet, columnIndex, rowIndex + 1);
        printRow(i, sheet, columnIndex, rowIndex + 2);
    }

    private String printRow(int i, XSSFSheet sheet, int columnIndex, int rowIndex) {
        String result;

        if (columnIndex == 28) {
            --columnIndex;
        }
        XSSFCell cell = sheet.getRow(rowIndex).getCell(columnIndex); // 1
        if (cell == null || !CellType.STRING.equals(cell.getCellType())) {
            result  = i + " ---" + " rowIndex = " + rowIndex + 1 + ", columnIndex = " + columnIndex + 1;
        } else {
            result = cell.getStringCellValue();
        }

        logger.info(result);
        return result;
    }
}
