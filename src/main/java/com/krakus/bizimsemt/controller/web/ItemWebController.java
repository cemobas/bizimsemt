package com.krakus.bizimsemt.controller.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemWebController {

	@GetMapping("/items")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String home() {
		return "items";
	}

}
