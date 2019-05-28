package com.maijia.QR.jsbridge;


public interface WebViewJavascriptBridge {
	
	public void send(String data);
	public void send(String data, CallBackFunction responseCallback);
	
	

}
