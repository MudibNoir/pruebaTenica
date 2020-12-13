package com.pruebatecnica.spring.product.batch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.spring.commons.repository.models.entity.Product;
import com.pruebatecnica.spring.product.batch.services.IProductService;


@RestController
@RequestMapping("/productos")
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	@GetMapping("/listar")
	public List<Product> list() {
		return productService.findAll();
	}
	
	@GetMapping("/ver/{id}")
	public Product item(@PathVariable Long id) {
		Product product = productService.findById(id);
		return product;
	}

}