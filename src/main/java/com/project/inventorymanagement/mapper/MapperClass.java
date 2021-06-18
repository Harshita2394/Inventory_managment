package com.project.inventorymanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.project.inventorymanagement.domain.Article;
import com.project.inventorymanagement.domain.Product;
import com.project.inventorymanagement.dto.ArticleDto;
import com.project.inventorymanagement.dto.ProductDto;

@Mapper(componentModel = "spring",
	uses = {})
public interface MapperClass {
	
	public static final MapperClass INSTANCE  = Mappers.getMapper(MapperClass.class);
	
	public Article articleDtoToArticle(ArticleDto articleDto);
	
	@Mapping(target = "articles.article", ignore = true)
	public Product productDtoToProduct(ProductDto productDto);
	
	@Mapping(target = "articles.article", ignore = true)
	public ProductDto productToProductDto(Product product);
	

}
