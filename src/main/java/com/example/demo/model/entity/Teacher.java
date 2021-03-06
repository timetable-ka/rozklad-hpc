package com.example.demo.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "teacher")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"timeTable", "timeTable2"})
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "teacher", cascade = {CascadeType.ALL})
    private Set<TimeTable> timeTable = new HashSet<>();

    @OneToMany(mappedBy = "teacherSecond", cascade = {CascadeType.ALL})
    private Set<TimeTable> timeTable2 = new HashSet<>();
}
