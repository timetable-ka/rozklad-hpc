package com.example.demo.model.repo;


import com.example.demo.model.entity.Group;
import com.example.demo.model.entity.Lesson;
import com.example.demo.model.entity.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {
    List<TimeTable> findByTeacherId(Long name);
    List<TimeTable> findByTeacherSecondId(Long name);


}
