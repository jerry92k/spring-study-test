package com.jerry.springtest.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {
		try {
			response.getWriter();
		} catch (IllegalStateException ex) {
			log.error("OutputStream 사용 못하게 제한됨. \n Error message {}", ex.getMessage());
		}
	}
}
