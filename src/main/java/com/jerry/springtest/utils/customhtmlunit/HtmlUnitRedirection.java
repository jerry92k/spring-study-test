package com.jerry.springtest.utils.customhtmlunit;

import java.net.URL;

public class HtmlUnitRedirection {

	private int redirectCount;

	// 마지막으로 호출한 url
	private URL currentUrl;

	public HtmlUnitRedirection(URL startUrl) {
		redirectCount=0;
		currentUrl=startUrl;
	}

	private void increaseRedirectCount(){
		if(isReachMaxRedirect()){
			throw new IllegalStateException("리다이렉션 횟수를 초과했습니다.");
		}
		this.redirectCount++;
	}

	public Boolean isReachMaxRedirect(){
		return CustomWebClient.maxRedirect == redirectCount;
	}

	/**
	 * @param redirectedUrl redirection을 실행하는 url
	 * 새롭게 redirection이 실행된 것이면 currentUrl은 redirection이 정상 실행 완료된 것이므로 lastUrl로 변경한다.
	 * 오류 발생시엔 마지막 lastUrl을 리턴한다.
	 */
	public void doRedirection(URL redirectedUrl) {
		increaseRedirectCount();
		currentUrl=redirectedUrl;
	}

	public URL getCurrentUrl() {
		return currentUrl;
	}
}
