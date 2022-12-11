package edu.wccnet.sepolidori.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class APIController {
	
	@GetMapping("/test")
	public String test() {
		return "Test";
	}
}
