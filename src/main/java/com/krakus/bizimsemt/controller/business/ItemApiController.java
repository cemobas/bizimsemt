package com.krakus.bizimsemt.controller.business;

import com.krakus.bizimsemt.domain.Item;
import com.krakus.bizimsemt.model.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="items")
public class ItemApiController {

	List<Item> itemStore = new ArrayList<>();

	@PostMapping("/save")
	public ResponseEntity<Object> addItem(@RequestBody Item item) {
		itemStore.add(item);
		ServiceResponse<Item> response = new ServiceResponse<>("success", item);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<Object> getAllItems() {
		ServiceResponse<List<Item>> response = new ServiceResponse<>("success", itemStore);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}