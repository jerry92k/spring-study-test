package com.jerry.springtest.utils;

import java.io.IOException;
import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@SpringBootTest
@AutoConfigureWebTestClient
public class HtmlUnitTest {

	// @Autowired
	private WebClient webClient;

	@BeforeEach
	void setUp(){
		webClient = new WebClient();
		webClient = new WebClient(BrowserVersion.CHROME);
		// webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getCookieManager().setCookiesEnabled(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setTimeout(50_000); // default : 9초
		webClient.getCache().setMaxSize(4);
	}

	@AfterEach
	void close(){
		webClient.close();
	}

	@Test
	void htmlUnitTest(){

	}

	@Test
	void test() throws IOException {
		// https://www.facebook.com/ads/library/?id=286238429359299
		// http://www.sears.com/search=little tikes&Little Tikes?filter=Brand&keywordSearch=false&vName=Toys+%26+Games&catalogId=12605&catPrediction=false&previousSort=ORIGINAL_SORT_ORDER&viewItems=50&storeId=10153&adCell=W3
		// HtmlPage page = webClient.getPage("http://kdhproject.tistory.com");

		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
		java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);

		HtmlPage page = webClient.getPage("https://www.facebook.com/ads/library/?id=286238429359299");
		URL url = page.getUrl();
		System.out.println("final url : "+url);

		HtmlPage page2 = webClient.getPage("http://kdhproject.tistory.com");
		URL url2 = page2.getUrl();
		System.out.println("final url2 : "+url2);

	}

	@Test
	void test2(){
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");

		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://bit.ly/3yYslyO");

		String currentUrl = driver.getCurrentUrl();
		// String currentUrl = browser.getCurrentUrl();
		System.out.println(currentUrl);
	}

}
