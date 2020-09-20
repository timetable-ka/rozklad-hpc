package com.example.demo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Day {

    MON("Понеділок", 1),
    TU("Вівторок", 2),
    WE("Середа", 3),
    TH("Четверг", 4),
    FR("Пятниця", 5),
    SA("Субота", 6),
    SU("Неділя", 7);

    private final String name;
    private final long numberDayInWeek;

    public static Day findByKey(long i) {
        Day[] testEnums = Day.values();
        for (Day testEnum : testEnums) {
            if (Long.valueOf(testEnum.getNumberDayInWeek()).equals(i)) {
                return testEnum;
            }
        }
        return null;
    }
}
