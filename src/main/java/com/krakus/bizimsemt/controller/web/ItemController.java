package com.krakus.bizimsemt.controller.web;

import com.krakus.bizimsemt.domain.Item;
import com.krakus.bizimsemt.model.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {

	List<Item> itemStore = new ArrayList<>();

	@PostMapping("/saveItem")
	public ResponseEntity<Object> addItem(@RequestBody Item item) {
		itemStore.add(item);
		ServiceResponse<Item> response = new ServiceResponse<>("success", item);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@GetMapping("/getItems")
	public ResponseEntity<Object> getAllItems() {
		ServiceResponse<List<Item>> response = new ServiceResponse<>("success", itemStore);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
