package com.krakus.bizimsemt.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemViewController {

	@GetMapping("/items")
	public String home() {
		return "items";
	}

}
