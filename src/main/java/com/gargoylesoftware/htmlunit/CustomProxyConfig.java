package com.gargoylesoftware.htmlunit;

public class CustomProxyConfig {

	private ProxyConfig proxyConfig;

	public CustomProxyConfig(ProxyConfig proxyConfig) {
		this.proxyConfig = proxyConfig;
	}

	public String getProxyAutoConfigContent() {
		return proxyConfig.getProxyAutoConfigContent();
	}

	public void setProxyAutoConfigContent(final String proxyAutoConfigContent) {
		proxyConfig.setProxyAutoConfigContent(proxyAutoConfigContent);
	}

	public boolean shouldBypassProxy(final String hostname) {
		return proxyConfig.shouldBypassProxy(hostname);
	}

	public String getProxyAutoConfigUrl() {
		return proxyConfig.getProxyAutoConfigUrl();
	}

	public String getProxyHost() {
		return proxyConfig.getProxyHost();
	}

	public int getProxyPort() {
		return proxyConfig.getProxyPort();
	}

	public String getProxyScheme() {
		return proxyConfig.getProxyScheme();
	}

	public boolean isSocksProxy(){
		return proxyConfig.isSocksProxy();
	}
}
