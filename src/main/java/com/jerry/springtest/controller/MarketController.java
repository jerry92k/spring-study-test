package com.jerry.springtest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jerry.springtest.dto.MarketDto;

@RestController
public class MarketController {

	@GetMapping("/markets")
	public ResponseEntity<MarketDto> findMarkets(){
		MarketDto marketDto = new MarketDto("수지 롯데몰");
		return ResponseEntity.ok(marketDto);
	}
}
