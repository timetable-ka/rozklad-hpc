package com.example.demo.converter;

import com.example.demo.json.group.GroupService;
import com.example.demo.json.lessons.LessonService;
import com.example.demo.json.teacher.TeacherService;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class ExportService {

    public static final int NUMBER_OF_GROUP = 14;
    public static final int NUMBER_OF_COURSE = 4;


    public void convertExcel() {
        Map<Integer, Params> courseExcelParams =
                Map.of(
                        1, new Params(new HorizontValue(6, 29), new VerticalValue(6, 25)),
                        2, new Params(new HorizontValue(1, 2), new VerticalValue(6, 25)),
                        3, new Params(new HorizontValue(1, 2), new VerticalValue(6, 25)),
                        4, new Params(new HorizontValue(1, 2), new VerticalValue(6, 25))

                );

        Stream.iterate(1, x -> x + 1)
                .limit(NUMBER_OF_COURSE)
                .forEach(course -> {
                    System.err.println("/" + course + "///////////////////////////////////////////////////");
                    XSSFWorkbook myExcelBook = getSheets();
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
            XSSFSheet myExcelSheet = myExcelBook.getSheet(course + " курс");

            System.err.println();
            Stream.iterate(8, x -> x + 1)
                    .limit(20)
                    .forEach(integer -> printSerial(myExcelSheet, integer));
        }
    }


    private void printSerial(XSSFSheet myExcelSheet, int i) {
        XSSFRow row = myExcelSheet.getRow(i);
        printFirstLesson(row.getCell(1));
        Stream.iterate(2, x -> x + 2)
                .limit(13)
                .map(row::getCell)
                .forEach(this::printFirstLesson);
    }

    private void printFirstLesson(XSSFCell xssfCell) {
        if (xssfCell == null) {
            return;
        }
        if (CellType.NUMERIC.equals(xssfCell.getCellType())) {
            return;
        }

        String rawValue = xssfCell.getStringCellValue();
        String[] lines = rawValue.split("\\r?\\n");
        System.err.println(Arrays.asList(lines).toString());
    }

    private void buildFile(GroupService groupService, LessonService lessonService, TeacherService teacherService, ConfigurableApplicationContext ctx) {
        teacherService.buildTeacherInfoByName();
        lessonService.buildTeacherInfoForEveryTeacher();
        lessonService.buildLessonInfoByName();
        groupService.buildGroupInfoById();
        groupService.buildGroupInfoByName();
        groupService.buildGroupSearchJson();
    }

    public static class Params {

        private final HorizontValue horizontValue;
        private final VerticalValue verticalValue;

        public Params(HorizontValue horizontValue, VerticalValue verticalValue) {
            this.horizontValue = horizontValue;
            this.verticalValue = verticalValue;
        }

        public HorizontValue getHorizontValue() {
            return horizontValue;
        }

        public VerticalValue getVerticalValue() {
            return verticalValue;
        }
    }


    public static class HorizontValue extends AbstractParam {
        public HorizontValue(Integer from, Integer to) {
            super(from, to);
        }
    }

    public static class VerticalValue extends AbstractParam {
        public VerticalValue(Integer from, Integer to) {
            super(from, to);
        }
    }

    private static abstract class AbstractParam {

        private final Integer from;
        private final Integer to;

        private AbstractParam(Integer from, Integer to) {
            this.from = from;
            this.to = to;
        }

        public Integer getFrom() {
            return from;
        }

        public Integer getTo() {
            return to;
        }
    }
}
