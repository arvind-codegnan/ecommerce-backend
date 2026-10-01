package com.codegnan.app.ecommercebackend.catalogue.service;

import java.util.List;

import com.codegnan.app.ecommercebackend.catalogue.dto.ProductRequestDto;
import com.codegnan.app.ecommercebackend.catalogue.dto.ProductResponseDto;

public interface ProductService {
	boolean addProduct(ProductRequestDto productRequestDto);
	
	ProductResponseDto searchProductById(int productId);
	
	List<ProductResponseDto> getAllProducts();
	
	boolean renameProduct(int productId, String updatedName);
	
	boolean removeProduct(int productId);
}