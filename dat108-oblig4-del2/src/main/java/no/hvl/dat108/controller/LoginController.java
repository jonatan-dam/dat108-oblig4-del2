package no.hvl.dat108.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/innlogging")
public class LoginController {
	
	@GetMapping
	public String hentLoginSkjema() {
		return "innlogging";
	}
}
