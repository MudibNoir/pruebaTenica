package com.pruebatecnica.spring.item.server.models;

import com.pruebatecnica.spring.commons.repository.models.entity.Product;

public class Item {

	private Product product;
	private Integer quantity;

	public Item() {
		super();
	}

	public Item(Product product, Integer quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Double getTotal( ) {
		return product.getProductPrice() * quantity.doubleValue();
	}

}
