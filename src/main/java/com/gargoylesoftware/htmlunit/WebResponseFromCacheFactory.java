package com.gargoylesoftware.htmlunit;

public class WebResponseFromCacheFactory {

	public static WebResponseFromCache makeWebResponseFromCache(final WebResponse cachedResponse,
		final WebRequest currentRequest) {
		return new WebResponseFromCache(cachedResponse, currentRequest);
	}
}
