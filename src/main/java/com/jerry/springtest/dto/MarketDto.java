package com.jerry.springtest.dto;

public class MarketDto {
	String name;

	private MarketDto() {
	}

	public MarketDto(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
