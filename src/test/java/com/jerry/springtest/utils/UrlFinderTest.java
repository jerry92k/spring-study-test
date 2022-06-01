package com.jerry.springtest.utils;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class UrlFinderTest {

	@Test
	void test(){
		/**
		 *    * shorten url : https://bit.ly/3yYslyO
		 *    * middle url : http://kdhproject.tistory.com/
		 *    * final url : https://blog.projectdh.link
		 */
		String testUrl = "https://bit.ly/3yYslyO";
		String originalUrl = UrlFinder.findOriginalUrl(testUrl);
		System.out.println("original URL : "+originalUrl);
	}
	
	@Test
	void test2() throws IOException {
		String testUrl = "https://kdhproject.tistory.com";
		String originalUrl = UrlFinder.findOriginalUrlWithHttpClient(testUrl);
		System.out.println("original URL : "+originalUrl);
	}
}
