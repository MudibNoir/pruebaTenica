package com.pruebatecnica.spring.product.batch.dao;

import org.springframework.data.repository.CrudRepository;

import com.pruebatecnica.spring.commons.repository.models.entity.Product;

public interface ProductDao extends CrudRepository<Product, Long>{

}
