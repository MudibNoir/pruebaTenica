package com.pruebatecnica.spring.item.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.spring.item.server.models.Item;
import com.pruebatecnica.spring.item.server.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping("/listar")
	public List<Item> list() {
		return itemService.findAll();
	}
	
	@GetMapping("/ver/{id}/cantidad/{quantity}")
	public Item details(@PathVariable Long id, @PathVariable Integer quantity) {
		return itemService.findById(id, quantity);
	}

}
