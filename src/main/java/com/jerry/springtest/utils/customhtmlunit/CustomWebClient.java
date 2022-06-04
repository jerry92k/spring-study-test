package com.jerry.springtest.utils.customhtmlunit;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.WebResponseData;
import com.gargoylesoftware.htmlunit.util.UrlUtils;

public class CustomWebClient extends WebClient {

	public CustomWebClient() {
		super();
	}

	public CustomWebClient(BrowserVersion browserVersion) {
		super(browserVersion);
	}

	@Override
	public WebResponse loadWebResponse(final WebRequest webRequest) throws IOException {
		String protocol = webRequest.getUrl().getProtocol();

		if(List.of(UrlUtils.ABOUT,"file","data").contains(protocol)){
			return new WebResponse(new WebResponseData("none".getBytes(StandardCharsets.UTF_8), 200, "OK",
				Collections.emptyList()) ,webRequest,0);
		}
		Class webClientClass = WebClient.class;

		Class partypes[] = new Class[2];
		partypes[0] = com.gargoylesoftware.htmlunit.WebRequest.class;
		partypes[1] = Integer.TYPE;
		try {
			Method meth = webClientClass.getDeclaredMethod("loadWebResponseFromWebConnection", partypes);
			meth.setAccessible(true);
			Object result = meth.invoke(this, webRequest, 20);
			return (WebResponse) result;
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}


}
