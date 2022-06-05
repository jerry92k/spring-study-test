package com.jerry.springtest.utils;

import java.io.IOException;

import java.lang.reflect.Method;
import java.net.URL;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.javascript.host.Location;

@SpringBootTest
@AutoConfigureWebTestClient
public class HtmlUnitTest {

	private HtmlUnitService htmlUnitService = new HtmlUnitService();

	/**
	 *    * shorten url : https://bit.ly/3yYslyO
	 *    * middle url : http://kdhproject.tistory.com/
	 *    * final url : https://blog.projectdh.link
	 */

	@Test
	void test() throws IOException {
		// String testUrl = "https://jerry92k.tistory.com/85";
		String testUrl = "https://bit.ly/3yYslyO";
		URL url = htmlUnitService.findUrl(testUrl);
		System.out.println("URL : "+url);
	}

	@Test
	void test2(){
		Class webClientClass = WebClient.class;
		Method methlist[] = webClientClass.getDeclaredMethods();
		for(Method method : methlist){
			if(!method.getName().equals("loadWebResponseFromWebConnection")) continue;
			System.out.println("method Name: "+method.getName());
			Class<?>[] parameterTypes = method.getParameterTypes();

			for(Class<?> parameterType : parameterTypes){
				System.out.println("parameterTypes : "+parameterType);
			}
		}
	}

	@Test
	void test3(){
		Class locationClass = Location.class;
		Method methlist[] = locationClass.getDeclaredMethods();
		for(Method method : methlist){
			if(!method.getName().equals("setHref")) continue;
			System.out.println("method Name: "+method.getName());
			Class<?>[] parameterTypes = method.getParameterTypes();

			for(Class<?> parameterType : parameterTypes){
				System.out.println("parameterTypes : "+parameterType);
			}
		}
	}

}
