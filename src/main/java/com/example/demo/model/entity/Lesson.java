package com.example.demo.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "lesson")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Lesson {

    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "lesson", cascade = {CascadeType.ALL})
    private Set<TimeTable> timeTable = new HashSet<>();
}
