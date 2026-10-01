package com.codegnan.app.ecommercebackend.catalogue.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.codegnan.app.ecommercebackend.catalogue.dto.ProductRequestDto;
import com.codegnan.app.ecommercebackend.catalogue.dto.ProductResponseDto;
import com.codegnan.app.ecommercebackend.catalogue.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean save(ProductRequestDto productRequestDto) {
		var isSaved = false;

		var product = new Product();
		product.setName(productRequestDto.name());
		product.setBrand(productRequestDto.brand());
		product.setDescription(productRequestDto.description());
		product.setStatus(productRequestDto.status());
		product.setCreatedAt(LocalDateTime.now());
		product.setUpdatedAt(LocalDateTime.now());

		try {
			entityManager.persist(product);

			isSaved = true;
		} catch (RuntimeException runtimeException) {
			runtimeException.printStackTrace();
		}

		return isSaved;
	}

	@Override
	public ProductResponseDto findById(int productId) {
		ProductResponseDto productResponseDto = null;

		try {
			var product = entityManager.find(Product.class, productId);

			if (product != null) {
				productResponseDto = new ProductResponseDto(
						product.getId(), 
						product.getName(), 
						product.getBrand(), 
						product.getDescription(), 
						product.getStatus(), 
						product.getCreatedAt(), 
						product.getUpdatedAt());
			}
		} catch (RuntimeException runtimeException) {
			runtimeException.printStackTrace();
		}

		return productResponseDto;
	}
	
	@Override
	public List<ProductResponseDto> findAll() {
		List<ProductResponseDto> productDtosList = new ArrayList<>();

		try {
			var jpql = "SELECT p FROM Product p";
			
			var query = entityManager.createQuery(jpql);

			List<Product> productsList = query.getResultList();

			for (var product : productsList) {
				var productResponseDto = new ProductResponseDto(
						product.getId(), 
						product.getName(), 
						product.getBrand(),
						product.getDescription(), 
						product.getStatus(), 
						product.getCreatedAt(), 
						product.getUpdatedAt());

				productDtosList.add(productResponseDto);
			}
		} catch (RuntimeException runtimeException) {
			runtimeException.printStackTrace();
		}

		return productDtosList;
	}
	
	@Override
	public boolean updateName(int productId, String updatedName) {
		var isUpdated = false;

		try {
			var product = entityManager.find(Product.class, productId);

			if (product != null) {
				product.setName(updatedName);
			
				isUpdated = true;
			}
		} catch (RuntimeException runtimeException) {
			runtimeException.printStackTrace();
		}

		return isUpdated;
	}
	
	@Override
	public boolean delete(int productId) {
		var isDeleted = false;

		try {
			var product = entityManager.find(Product.class, productId);

			if (product != null) {
				entityManager.remove(product);
				
				isDeleted = true;
			}
		} catch (RuntimeException runtimeException) {
			runtimeException.printStackTrace();
		}

		return isDeleted;
	}
}