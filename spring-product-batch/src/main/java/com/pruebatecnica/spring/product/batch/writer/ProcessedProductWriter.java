package com.pruebatecnica.spring.product.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pruebatecnica.spring.product.batch.dao.ProductDao;
import com.pruebatecnica.spring.commons.repository.models.entity.Product;
@Component
public class ProcessedProductWriter implements ItemWriter<Product> {

	@Autowired
	private ProductDao productDao;

	public ProcessedProductWriter(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public void write(List<? extends Product> items) throws Exception {
		productDao.saveAll(items);
	}

}