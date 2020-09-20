package com.example.demo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum LessonTime {

    FIRST("08:30","09:50", 1),
    SECOND("10:00","11:20", 2),
    THIRD("11:50","13:10", 3),
    FOURTH("13:20","14:40", 4),
    FIFTH("14:50","16:10", 4);

    private final String start;
    private final String end;
    private  final long numberDayInWeek;

    public static LessonTime findByKey(long i) {
        LessonTime[] testEnums = LessonTime.values();
        for (LessonTime testEnum : testEnums) {
            if (Long.valueOf(testEnum.getNumberDayInWeek()).equals(i)) {
                return testEnum;
            }
        }
        return null;
    }
}
