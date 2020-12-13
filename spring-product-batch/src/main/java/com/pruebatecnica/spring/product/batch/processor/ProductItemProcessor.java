package com.pruebatecnica.spring.product.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.pruebatecnica.spring.commons.repository.models.entity.Product;



public class ProductItemProcessor implements ItemProcessor<Product, Product>{
	
	private static final Logger log = LoggerFactory.getLogger(ProductItemProcessor.class);


	@Override
	public Product process(Product item) throws Exception {
		String name = item.getProductName().toUpperCase();
		String description = item.getProductDescription().toUpperCase();
		Double price = item.getProductPrice();
		Integer units = item.getProductUnits();
		
		Product producto = new Product(name, description, price, units);
		
		log.info("Convirtiendo ("+item+") a ("+producto+") ");
		
		return producto;
	}
	

}
