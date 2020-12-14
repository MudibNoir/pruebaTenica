package com.pruebatecnica.spring.item.server.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebatecnica.spring.item.server.client.ProductClientRest;
import com.pruebatecnica.spring.item.server.models.Item;

@Service
public class ItemServiceFeign implements ItemService{
	
	@Autowired
	private ProductClientRest feignClient;

	@Override
	public List<Item> findAll() {
		return feignClient.list().stream().map(p -> new Item(p,1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer quantity) {
		return new Item(feignClient.detail(id), quantity);
	}

}
