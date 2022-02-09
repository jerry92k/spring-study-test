package com.jerry.springtest.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

class MarketControllerTest extends ControllerTest{

	@Autowired
	private MockMvc mockMvc;

	@Test
	void 인터셉터에서_response_로깅하려할경우_mockMvc의_경우_제대로_테스트안됨() throws Exception {
		mockMvc.perform(get("/markets")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(print());
		// 실행결과, LoggingInterceptor의 로깅하는 부분에 response 객체가 org.springframework.mock.web.MockHttpServletResponse@6debf1b8 로 모킹된 것을 볼 수 있음
	}

}
