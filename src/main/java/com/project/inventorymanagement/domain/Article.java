package com.project.inventorymanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ARTICLE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

	@Id
	@Column(name = "ID")
	private Long articleId;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name ="STOCK")
	private int stock;
	
}
