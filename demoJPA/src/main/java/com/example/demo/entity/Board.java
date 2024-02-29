package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "board")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	
	@Id
	private int seq;

	private String id;
	private String title;
	private String content;
	private String regdate;
	private int cnt;
	private int root;
	private int step;
	private int indent;
} // class
