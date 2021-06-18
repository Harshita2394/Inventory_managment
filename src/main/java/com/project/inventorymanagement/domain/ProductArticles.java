package com.project.inventorymanagement.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="PRODUCT_ARTICLE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductArticles {

	@Id
	@Column(name="ARTICLE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false, name = "ARTICLE_ID", insertable = false, updatable = false)
	private Long articleId;
	
	@OneToOne
	@JoinColumn(name = "ARTICLE_ID")
	@JsonIgnore
	private Article article;
	
	@Column(name = "QUANTITY")
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "ID")
	@JsonIgnore
	private Product product;
	
	
	
	
	 
}
