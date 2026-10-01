package com.codegnan.app.ecommercebackend.resource;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codegnan.app.ecommercebackend.catalogue.dto.ProductRequestDto;
import com.codegnan.app.ecommercebackend.catalogue.dto.ProductResponseDto;
import com.codegnan.app.ecommercebackend.catalogue.service.ProductService;

@RestController
@RequestMapping("/rest/api/product")
public class CustomerResource {
	private ProductService productService;
	
	public CustomerResource(ProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping
	public String addProductOperation(@ModelAttribute ProductRequestDto productRequestDto) {
		var isProductAdded = productService.addProduct(productRequestDto);
		
		if (isProductAdded) {
			return "success";
		} else {
			return "failure";
		}
	}
	
	@GetMapping
	public ProductResponseDto searchProductOperation(@RequestParam int productId) {
		ProductResponseDto product = productService.searchProductById(productId);
			
		return product;
	}
	
	@GetMapping("/viewall")
	public List<ProductResponseDto> getAllProductsOperation() {
		return productService.getAllProducts();
	}
	
	//@PatchMapping
	@PutMapping
	public String updateProductNameOperation(@RequestParam int productId, @RequestParam String updatedName) {
		var isProductRenamed = productService.renameProduct(productId, updatedName);
		
		if (isProductRenamed) {
			return "success";
		} else {
			return "failure";
		}
	}
	
	@DeleteMapping
	public String removeProductOperation(@RequestParam int productId) {
		var isProductRemoved = productService.removeProduct(productId);
		
		if (isProductRemoved) {
			return "success";
		} else {
			return "failure";
		}
	}
}