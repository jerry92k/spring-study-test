package com.jerry.springtest.utils;

import java.io.IOException;

import java.net.URL;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;

import com.gargoylesoftware.htmlunit.html.parser.HTMLParserListener;
import com.gargoylesoftware.htmlunit.javascript.SilentJavaScriptErrorListener;
import com.jerry.springtest.utils.customhtmlunit.CustomWebClient;

public class HtmlUnitService {

	private CustomWebClient webClient;

	public HtmlUnitService() {
		initWebClient(new CustomWebClient(BrowserVersion.CHROME));
	}

	public URL findUrl(String url) throws IOException {
		return findUrl(new URL(url));
	}

	public URL findUrl(URL url) {
		return webClient.findOriginUrl(url);
	}

	void initWebClient(CustomWebClient webClientImp) {
		webClient = webClientImp;
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setDownloadImages(false);

		webClient.getCookieManager().setCookiesEnabled(true);
		webClient.getCache().setMaxSize(10);

		webClient.getOptions().setTimeout(50_000); // default : 9초
		webClient.waitForBackgroundJavaScript(3_000);
		webClient.setJavaScriptTimeout(10_000);
		webClient.setJavaScriptErrorListener(new SilentJavaScriptErrorListener());
		webClient.setHTMLParserListener(new CustomHTMLParserListener());
		webClient.setIncorrectnessListener((arg0, arg1) -> {
		});

		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
	}

	public static class CustomHTMLParserListener implements HTMLParserListener {
		@Override
		public void error(String message, URL url, String html, int line, int column, String key) {

		}

		@Override
		public void warning(String message, URL url, String html, int line, int column, String key) {

		}
	}
}
