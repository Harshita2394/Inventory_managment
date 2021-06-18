package com.project.inventorymanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.inventorymanagement.domain.Article;
import com.project.inventorymanagement.domain.Product;
import com.project.inventorymanagement.dto.ArticleDto;
import com.project.inventorymanagement.dto.ProductArticlesDto;
import com.project.inventorymanagement.dto.ProductDto;
import com.project.inventorymanagement.mapper.MapperClass;
import com.project.inventorymanagement.repository.ArticleRespository;
import com.project.inventorymanagement.repository.ProductRepository;

@Service
public class InventoryManagementService {

	@Autowired
	private ArticleRespository articleRespository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private MapperClass mapperClass;

	public void saveArticle(ArticleDto articleDto) {
		articleRespository.save(mapperClass.articleDtoToArticle(articleDto));
	}

	public void saveProduct(ProductDto productDto) {
		productRepository.save(mapperClass.productDtoToProduct(productDto));
	}

	public List<ProductDto> getAllProducts() {
		List<ProductDto> productDtos = new ArrayList<>();
		List<Product> products = productRepository.findAll();
		if (!products.isEmpty()) {
			products.stream().forEach(x -> {
				productDtos.add(mapperClass.productToProductDto(x));
			});
		}

		return productDtos;

	}

	@Transactional
	public void sellProduct(ProductDto productDto) {
		List<Long> articleIds = productDto.getArticles().stream().map(ProductArticlesDto::getArticleId)
				.collect(Collectors.toList());
		
		//Reduce the stock quantity of each article in inventory
		List<Article> articles = articleRespository.findAllById(articleIds);
		if (!articles.isEmpty()) {
			articles.stream().forEach(x -> {
				Optional<ProductArticlesDto> productArticle = productDto.getArticles().stream()
						.filter(n -> n.getArticleId().equals(x.getArticleId())).findFirst();
				if (productArticle.isPresent()) {
					int articleQuantity = productArticle.get().getQuantity();
					int currentStock = x.getStock();
					x.setStock(Math.subtractExact(currentStock, articleQuantity));
				}
			});
			articleRespository.saveAll(articles);
		}
		
		//Remove the product.
		productRepository.delete(mapperClass.productDtoToProduct(productDto));
	}
}
