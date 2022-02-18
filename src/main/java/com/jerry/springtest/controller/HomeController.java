package com.jerry.springtest.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

	@GetMapping
	public String getHome(HttpServletResponse response) throws IOException {
		response.sendRedirect("/home/moved");
		return "ok";
	}

	@GetMapping("/moved")
	public String getMoved(Model model) {
		return "moved";
	}
}
