package br.com.gabrielbobrov.beyondwork.infrastructure.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class PublicController {
	
	@GetMapping
	public String login() {
		return "/login";
	}

}
