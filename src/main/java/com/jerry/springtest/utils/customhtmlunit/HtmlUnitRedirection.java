package com.jerry.springtest.utils.customhtmlunit;

import com.jerry.springtest.utils.HtmlUnitService;

public class HtmlUnitRedirection {

	private int redirectCount;

	public HtmlUnitRedirection() {
		redirectCount=0;
	}

	public void increaseRedirectCount(){
		if(isReachMaxRedirect()){
			throw new IllegalStateException("리다이렉션 횟수를 초과했습니다.");
		}
		this.redirectCount++;
	}

	public Boolean isReachMaxRedirect(){
		return CustomWebClient.maxRedirect == redirectCount;
	}
}
