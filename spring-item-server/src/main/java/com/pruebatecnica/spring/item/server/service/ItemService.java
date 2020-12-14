package com.pruebatecnica.spring.item.server.service;

import java.util.List;

import com.pruebatecnica.spring.item.server.models.Item;

public interface ItemService {
	
	public List<Item> findAll();
	public Item findById(Long id, Integer quantity);

}
