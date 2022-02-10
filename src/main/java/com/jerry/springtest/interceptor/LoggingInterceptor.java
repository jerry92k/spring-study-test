package com.jerry.springtest.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingResponseWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		Exception {
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {

		ContentCachingResponseWrapper responseWrapper = getResponseWrapper(response);
		String responseBody = getResponseBody(responseWrapper);

		log.info("body {} ", responseBody);
		// 클라이언트로 전달 전 실제 response 객체에 copy
		responseWrapper.copyBodyToResponse();
	}

	private String getResponseBody(ContentCachingResponseWrapper responseWrapper) {
		return new String(responseWrapper.getContentAsByteArray());
	}

	private ContentCachingResponseWrapper getResponseWrapper(HttpServletResponse response) {
		if (response instanceof ContentCachingResponseWrapper) {
			return (ContentCachingResponseWrapper)response;
		}
		return new ContentCachingResponseWrapper(response);
	}
}
