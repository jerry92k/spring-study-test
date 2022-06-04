package com.jerry.springtest.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.parser.HTMLParserListener;
import com.gargoylesoftware.htmlunit.javascript.DefaultJavaScriptErrorListener;
import com.gargoylesoftware.htmlunit.javascript.SilentJavaScriptErrorListener;
import com.jerry.springtest.utils.customhtmlunit.CustomWebClient;

public class HtmlUnitService {

	private WebClient webClient;

	public HtmlUnitService() {
		initWebClient(new CustomWebClient(BrowserVersion.CHROME));
	}

	public URL findUrl(String url) throws IOException {
		return webClient.getPage(url).getUrl();
	}

	void initWebClient(WebClient webClientImp) {
		webClient = webClientImp;
		// webClient.setAjaxController(new );
		// webClient.setAjaxController(new AsyncControll());
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setDownloadImages(false);
		// webClient.getOptions().setHistoryPageCacheLimit(1);
		// webClient.getOptions().setHistorySizeLimit(1);

		webClient.getCookieManager().setCookiesEnabled(true);
		webClient.getCache().setMaxSize(10);

		webClient.getOptions().setTimeout(50_000); // default : 9초
		webClient.waitForBackgroundJavaScript(3_000);
		webClient.setJavaScriptTimeout(3_000);
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

	public static class MyJSErrorListener extends DefaultJavaScriptErrorListener {
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
