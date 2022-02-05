package com.jerry.springtest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interceptor")
public class CommonContorller {

	@GetMapping("/test")
	public ResponseEntity<Void> test(){
		System.out.println("execute controller");
		return ResponseEntity.ok().build();
	}
}
