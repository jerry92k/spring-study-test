package com.jerry.springtest.acceptacne;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.jerry.springtest.dto.MarketDto;

import io.restassured.RestAssured;

public class MarketAcceptacneTest extends AcceptanceTest {

	private static final String MARKET_PATH = "/markets";

	@Test
	void 인터셉터에서_getWriter_호출시_에러_로그_확인() {
			RestAssured
				.given().log().all()
				.when().get(MARKET_PATH + "/v1")
				.then().log().all();
	}

	@Test
	void 필터에서_response_래핑후_response_참조시_body_참조가능() {
		MarketDto result = RestAssured
			.given().log().all()
			.when().get(MARKET_PATH + "/v2")
			.then().log().all()
			.extract().as(MarketDto.class);

		assertThat(result.getName()).isEqualTo("수지 롯데몰 filter");
	}

	@Test
	void argumentResolve_커스터마이징() {
		MarketDto result = RestAssured
			.given().log().all()
			.when().get(MARKET_PATH + "/resolve?station=pangyo")
			.then().log().all()
			.extract().as(MarketDto.class);

		// 적용되는 ArgumentResolver가 여러개인 경우, 먼저 등록된 게 적용됨
		assertThat(result.getName()).isEqualTo("pangyo+ resolved V2");
	}
}
