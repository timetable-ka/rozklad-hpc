package com.example.demo.model.repo;


import com.example.demo.model.entity.Group;
import com.example.demo.model.entity.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {
}
