package com.example.demo.model.repo;


import com.example.demo.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findByTimeTableGroupName(String name);
    List<Teacher> findByTimeTable2GroupName(String name);
}
