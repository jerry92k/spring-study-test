package com.jerry.springtest.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

@WebFilter(urlPatterns = {"/markets/v2"})
public class LogFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
		IOException,
		ServletException {
		// reponse를 ContentCachingResponseWrapper 객체로 래핑
		ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper(
			(HttpServletResponse)response);

		chain.doFilter(request, httpServletResponse);
	}
}
