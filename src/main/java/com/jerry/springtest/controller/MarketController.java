package com.jerry.springtest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jerry.springtest.dto.MarketDto;

@RestController
@RequestMapping("/markets")
public class MarketController {

	@GetMapping
	public ResponseEntity<MarketDto> findMarkets(){
		MarketDto marketDto = new MarketDto("수지 롯데몰");
		return ResponseEntity.ok(marketDto);
	}

	@GetMapping("/v2")
	public ResponseEntity<MarketDto> findMarketsWithFilter(){
		MarketDto marketDto = new MarketDto("수지 롯데몰 filter");
		return ResponseEntity.ok(marketDto);
	}
}
