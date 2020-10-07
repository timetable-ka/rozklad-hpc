package com.example.demo.model.entity;

import lombok.*;

import javax.persistence.*;


@Table(name = "room")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
