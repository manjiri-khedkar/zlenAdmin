package com.zlenadmin.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class CallableService implements Callable<String> {
	private static final Logger log = LoggerFactory.getLogger(CallableService.class);
	private String uri;
	private String input;
	private String method;
	private String contentType;
	public CallableService() {}
	
	public CallableService(String uri, String input) {
		super();
		this.uri = uri;
		this.input = input;
		this.method="POST";
		this.contentType="application/xml";
	}

	public CallableService(String uri, String input, String method, String contentType) {
		super();
		this.uri = uri;
		this.input = input;
		this.method=method;
		this.contentType=contentType;
	}

	
	@Override
	public String call() throws Exception {
		
		log.info("Inside call method - Start");
		
		URL url = new URL(this.uri);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(method);
		
		//
		if (contentType!=null){
			conn.setRequestProperty("Content-Type",contentType );
		}
		
		
		if (input != null) {
			conn.setDoOutput(true);
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(60000);
			OutputStream os = conn.getOutputStream();
			String message = input;
			message.replace("\\n", "");
			message = message.replace("\\t", "");
			message = message.replace("\\", "");
			if (contentType != null && contentType.equalsIgnoreCase("application/xml")) {
				message = message.trim().replaceFirst("^([\\W]+)<", "<");
				message = message.substring(0, message.lastIndexOf(">") + 1);
			}
			os.write(message.getBytes());
			os.flush();
		}
		
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
		String output = "";
		String displayOutput = "";
		
		while ((output = br.readLine()) != null) {
			displayOutput +=  output;
		}
		System.out.println(displayOutput);

		conn.disconnect();
		log.info("Inside call method - End");
		return displayOutput;
	}
	
	
}
