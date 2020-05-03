package com.krakus.bizimsemt.controller.business;

import com.krakus.bizimsemt.aspect.Loggable;
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

	@Loggable
	@PostMapping("/save")
	public ResponseEntity<ServiceResponse<Item>> addItem(@RequestBody Item item) {
		if(null == item) {
			ServiceResponse<Item> response = new ServiceResponse<>("failure", item);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			itemStore.add(item);
			ServiceResponse<Item> response = new ServiceResponse<>("success", item);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@Loggable
	@GetMapping("/getAll")
	public ResponseEntity<Object> getAllItems() {
		ServiceResponse<List<Item>> response = new ServiceResponse<>("success", itemStore);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
