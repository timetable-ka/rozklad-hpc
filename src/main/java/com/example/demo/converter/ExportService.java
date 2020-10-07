package com.example.demo.converter;

import com.example.demo.model.service.TimeTableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExportService {

    private final TimeTableService timeTableService;


    public void convertExcel() {
        XSSFWorkbook myExcelBook = getSheets();

        Stream.of(1).forEach(course -> {
//        Stream.of(1, 2, 3, 4).forEach(course -> {
            log.info(course + "////////////////////////////");
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
                        log.info("Before exit rownum = " + rownum + " firstColumn = " + firstColumn);
                        return;
                    }
                }
                String message = ++count + " " + cell.getStringCellValue();
                log.info(message);

                int columnIndex = cell.getColumnIndex();
                int rowIndex = cell.getRowIndex() - 2;

                for (int i = 1; i <= 20; i++) {
                    openOneGroup(i, sheet, columnIndex, rowIndex + 4 * i, cell.getStringCellValue());
                }

                firstColumn += 3;
            }
        }
    }

    private void openOneGroup(int i, XSSFSheet sheet, int columnIndex, int rowIndex, String group) {
        String[] groupInfo = group.replace("(", "")
                .replace(")", "")
                .replace(". ", ".")
                .split(" ");

        log.info("=-=-=-=-=-=");
        String row1 = printRow(i, sheet, columnIndex, rowIndex - 1);
        String row2 = printRow(i, sheet, columnIndex, rowIndex);
        String row3 = printRow(i, sheet, columnIndex, rowIndex + 1);
        String row4 = printRow(i, sheet, columnIndex, rowIndex + 2);

        // todo have case:
        //  1, 2
        //  3, 4
        //  2, 3
        //  2, 3, 4
        //  1, 2, 3, 4

        if (row1 != null && row2 != null) {
            if (row3 != null && row4 != null) {
                // 1, 2, 3, 4
                // create 1,2 lessons
                timeTableService.createNewLessons(row1, i, 1, row2, null, groupInfo[1],  groupInfo[0]);
                timeTableService.createNewLessons(row2, i, 2, row3, null, groupInfo[1],  groupInfo[0]);
                return;
            }
            // 1, 2
            // create 1 lessons
            timeTableService.createNewLessons(row1, i, 1, row2, null, groupInfo[1],  groupInfo[0]);
            return;
        } else if (row2 != null && row3 != null) {
            if (row4 != null) {
                timeTableService.createNewLessons(row2, i, 1, row3, row4, groupInfo[1],  groupInfo[0]);
                timeTableService.createNewLessons(row2, i, 2, row3, row4, groupInfo[1],  groupInfo[0]);
                // 2, 3, 4
                // create 1,2 lessons
                return;
            }
            // 2, 3
            // create 1,2 lessons
            timeTableService.createNewLessons(row2, i, 1, row3, null, groupInfo[1],  groupInfo[0]);
            timeTableService.createNewLessons(row2, i, 2, row3, null, groupInfo[1],  groupInfo[0]);
            return;
        } else if (row3 != null && row4 != null) {
            // 3, 4
            // create 2 lessons
            timeTableService.createNewLessons(row3, i, 2, row4, null, groupInfo[1],  groupInfo[0]);
            return;
        } else if (row1 == null && row2 == null && row3 == null && row4 == null) {
            log.info("NULL");
        } else {
            throw new RuntimeException();
        }
    }

    private String printRow(int i, XSSFSheet sheet, int columnIndex, int rowIndex) {
        String result = null;

        if (columnIndex == 28) {
            --columnIndex;
        }
        XSSFCell cell = sheet.getRow(rowIndex).getCell(columnIndex); // 1
        if (cell == null || !CellType.STRING.equals(cell.getCellType())) {
            log.info(i + " ---" + " rowIndex = " + rowIndex + 1 + ", columnIndex = " + columnIndex + 1);
        } else {
            log.info(cell.getStringCellValue());
            result = cell.getStringCellValue();
        }

        return result;
    }
}
