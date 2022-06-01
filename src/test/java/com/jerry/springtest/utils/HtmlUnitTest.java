package com.jerry.springtest.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.parser.HTMLParserListener;
import com.gargoylesoftware.htmlunit.javascript.DefaultJavaScriptErrorListener;

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
		webClient.waitForBackgroundJavaScript(3_000);
		webClient.setJavaScriptTimeout(3_000);
		webClient.setJavaScriptErrorListener(new MyJSErrorListener());
		webClient.setIncorrectnessListener((arg0, arg1) -> {
			// TODO Auto-generated method stub
		});
		webClient.setHTMLParserListener(new HTMLParserListener() {

			@Override
			public void error(String message, URL url, String html, int line, int column, String key) {

			}

			@Override
			public void warning(String message, URL url, String html, int line, int column, String key) {

			}
		});
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
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);

		HtmlPage page = webClient.getPage("https://www.facebook.com/ads/library/?id=286238429359299");
		URL url = page.getUrl();
		System.out.println("final url : "+url);

		HtmlPage page2 = webClient.getPage("http://kdhproject.tistory.com");
		URL url2 = page2.getUrl();
		System.out.println("final url2 : "+url2);

	}

	public class MyJSErrorListener extends DefaultJavaScriptErrorListener {
		@Override
		public void scriptException(HtmlPage page, ScriptException scriptException) {
		}

		@Override
		public void timeoutError(HtmlPage page, long allowedTime, long executionTime) {
		}

		@Override
		public void malformedScriptURL(HtmlPage page, String url, MalformedURLException malformedURLException) {

		}

		@Override
		public void loadScriptError(HtmlPage page, URL scriptUrl, Exception exception) {

		}

		@Override
		public void warn(String message, String sourceName, int line, String lineSource, int lineOffset) {

		}
	}

}
