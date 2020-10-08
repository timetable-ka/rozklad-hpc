package com.example.demo.model.repo;


import com.example.demo.model.entity.Group;
import com.example.demo.model.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    Optional<Lesson> findByName(String name);
}
