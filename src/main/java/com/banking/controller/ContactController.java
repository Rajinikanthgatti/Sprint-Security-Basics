package com.banking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

	@GetMapping("/contacts")
	public String saveEnquriyDetails(String input) {
		return "Here a query and contacts page will be displayed";
	}
}
