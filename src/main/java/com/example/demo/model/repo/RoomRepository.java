package com.example.demo.model.repo;


import com.example.demo.model.entity.Lesson;
import com.example.demo.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByName(String name);
}
