package com.pruebatecnica.spring.product.batch.services;

import java.util.List;

import com.pruebatecnica.spring.commons.repository.models.entity.Product;

public interface IProductService {
	
	public List<Product> findAll();
	public Product findById(Long id);

}
