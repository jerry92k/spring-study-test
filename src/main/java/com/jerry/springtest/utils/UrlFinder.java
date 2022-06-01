package com.jerry.springtest.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import org.apache.http.protocol.HttpCoreContext;

public class UrlFinder {

	public static String findOriginalUrl(String urlStr){
		String convertToHttpUrl = urlStr.replace("https", "http");
		HttpURLConnection con = null;
		try {
			URL url = new URL(convertToHttpUrl);
			con = (HttpURLConnection) url.openConnection();
			setConnectionProperties(con);
			con.connect();
			//con.getInputStream();
			int resCode = con.getResponseCode();
			System.out.println("resCode : "+resCode);
			if (resCode == HttpURLConnection.HTTP_SEE_OTHER
				|| resCode == HttpURLConnection.HTTP_MOVED_PERM
				|| resCode == HttpURLConnection.HTTP_MOVED_TEMP) {
				String Location = con.getHeaderField("Location");
				System.out.println("Location : "+ Location);
				if (Location.startsWith("/")) {
					Location = "http://" + url.getHost() + Location;
				}
				return findOriginalUrl(Location);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(con!=null){
				con.disconnect();
			}
		}

		return convertToHttpUrl;
	}

	public static String findOriginalUrlWithHttpClient(String urlStr) throws IOException {
		String convertToHttpUrl = urlStr;
			// .replace("https", "http");

		CloseableHttpClient client = HttpClientBuilder.create().disableRedirectHandling().build();
		HttpGet request = new HttpGet(convertToHttpUrl);

		HttpClientContext context = HttpClientContext.create();

		try {
			HttpResponse response = client.execute(request,context);
			List<URI> redirectLocations = context.getRedirectLocations();

			HttpHost currentHost = (HttpHost)context.getAttribute(HttpCoreContext.HTTP_TARGET_HOST);

			URI location = URIUtils.resolve(request.getURI(), currentHost, redirectLocations);
			System.out.println("Final HTTP location: " + location.toASCIIString());
			// HttpUriRequest currentRequest = (HttpUriRequest) context.getAttribute(HttpCoreContext.HTTP_REQUEST);

			// String currentUrl = (currentRequest.getURI().isAbsolute()) ? currentRequest.getURI().toString() : (currentHost.toURI() + currentRequest.getURI());
			// System.out.println("currentUrl : "+currentUrl);
			// String convertToHttpCurrentUrl = currentUrl.replace("https", "http");
			// if(convertToHttpCurrentUrl.equals(convertToHttpUrl)){
			// 	return convertToHttpUrl;
			// }

			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println("resCode : "+statusCode);


			// Header[] allHeaders = response.getAllHeaders();
			// Optional<Header> redirectHeader = Arrays.stream(allHeaders)
			// 	.filter(header -> header.getName().equals("Location") || header.getName()
			// 		.equals("Access-Control-Allow-Origin"))
			// 	.findAny();
			//
			// if(redirectHeader.isEmpty()){
			// 	return convertToHttpUrl;
			// }
			//
			// String redirectUrl = redirectHeader.get().getValue();
			// System.out.println("redirectUrl :"+redirectUrl);
			// if(redirectUrl.startsWith("/")){
			// 	redirectUrl = "http://"+ request.getURI().getHost()+redirectUrl;
			// }

			return convertToHttpUrl;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			client.close();
		}

		return convertToHttpUrl;
	}

	private static void setConnectionProperties(HttpURLConnection con) {
		con.setInstanceFollowRedirects(false);
		con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
		con.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
		con.addRequestProperty("Referer", "https://www.google.com/");
	}
}
