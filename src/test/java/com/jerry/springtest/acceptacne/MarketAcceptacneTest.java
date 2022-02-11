package com.jerry.springtest.acceptacne;

import org.junit.jupiter.api.Test;

import com.jerry.springtest.dto.MarketDto;

import io.restassured.RestAssured;

public class MarketAcceptacneTest extends AcceptanceTest {

	private static final String MARKET_PATH = "/markets";

	@Test
	void 인터셉터에서_response_body_로깅시_데이터_없음() {
		RestAssured
			.given().log().all()
			.when().get(MARKET_PATH+"/v1")
			.then().log().all()
			.extract().as(MarketDto.class);
	}

	@Test
	void 필터에서_response_래핑후_response_참조시_body_참조가능() {
		RestAssured
			.given().log().all()
			.when().get(MARKET_PATH + "/v2")
			.then().log().all()
			.extract().as(MarketDto.class);
	}
}
