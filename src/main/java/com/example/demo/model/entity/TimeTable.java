package com.example.demo.model.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Table(name = "timetable")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "teacher_id_optional")
    private Teacher teacherSecond;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "lesson_room_id")
    private Room room;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "lesson_room_id_optional")
    private Room roomSecond;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;



    @Column(name = "lesson_number")
    private long lessonNumber;
    @Column(name = "day_number")
    private long dayNumber;
    @Column(name = "lesson_week")
    private long lessonWeek;

}
