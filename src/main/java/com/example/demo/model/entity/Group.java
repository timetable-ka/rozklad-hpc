package com.example.demo.model.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "college_group")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Group {

	@Id
	private Long id;
	private String name;

}
