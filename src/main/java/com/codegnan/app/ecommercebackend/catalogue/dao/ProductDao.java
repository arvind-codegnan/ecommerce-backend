package com.codegnan.app.ecommercebackend.catalogue.dao;

import java.util.List;

import com.codegnan.app.ecommercebackend.catalogue.dto.ProductRequestDto;
import com.codegnan.app.ecommercebackend.catalogue.dto.ProductResponseDto;

public interface ProductDao {
	boolean save(ProductRequestDto productRequestDto);
	
	ProductResponseDto findById(int productId);
	
	List<ProductResponseDto> findAll();
	
	boolean updateName(int productId, String updatedName);
	
	boolean delete(int productId);
}