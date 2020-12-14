package com.pruebatecnica.spring.item.server.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pruebatecnica.spring.commons.repository.models.entity.Product;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductClientRest {
	
	@GetMapping("/productos/listar")
	public List<Product> list();
	
	@GetMapping("productos/ver/{id}")
	public Product detail(@PathVariable Long id);

}
