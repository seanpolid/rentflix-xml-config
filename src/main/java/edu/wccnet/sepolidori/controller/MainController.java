package edu.wccnet.sepolidori.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping({"/home", "/"})
	public String home() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		return "login";
	}
}
