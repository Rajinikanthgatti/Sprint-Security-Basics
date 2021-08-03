package com.banking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

	@GetMapping("/myLoans")
	public String getLoansDetails(String input) {
		return "Loans details will be returned";
	}
}
