package com.jerry.springtest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

public class CommonControllerTest extends ControllerTest{

	@Autowired
	private MockMvc mockMvc;

	@Test
	void 다중_인터셉터_적용시_순서() throws Exception {
		/*
		- 기본 : WebMvcConfigurer 설정에 등록되는 순서
		- 임의 제어 : WebMvcConfigurer 설정에서 등록할때 order() 에 값을 줌으로써 순서 제어 가능 (값이 작을수록 우선순위)
		- ex) 순서가 AInterceptor -> BInterceptor인 경우,
		 A preHandler -> B preHandler -> Controller 실행 -> B postHandler -> A postHandler
		  -> B afterCompletion -> A afterCompletion (afterCompletion은 post가 먼저 끝난 것부터 호출됨)
		 */

		mockMvc.perform(get("/interceptor/test")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(print());
	}
}
