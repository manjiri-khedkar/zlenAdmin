package com.zlenadmin.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.concurrent.Callable;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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
	private HashMap<String, String> headers;
	public CallableService() {}
	
	public CallableService(String uri, String input) {
		super();
		this.uri = uri;
		this.input = input;
		this.method="POST";
		this.contentType="application/xml";
	}

	public CallableService(String uri, String input, String method, String contentType,HashMap<String, String> headers) {
		super();
		this.uri = uri;
		this.input = input;
		this.method=method;
		this.contentType=contentType;
		this.headers=headers;
	}

	
	@Override
	public String call() throws Exception {
		
		try {
		    SSLContext ssl_ctx = SSLContext.getInstance("TLS");
		        TrustManager[ ] trust_mgr = new TrustManager[ ] {
		    new X509TrustManager() {
		       public X509Certificate[ ] getAcceptedIssuers() { return null; }
		       public void checkClientTrusted(X509Certificate[ ] certs, String t) { }
		       public void checkServerTrusted(X509Certificate[ ] certs, String t) { }
		     }
		  };
		        ssl_ctx.init(null,                // key manager
		                     trust_mgr,           // trust manager
		                     new SecureRandom()); // random number generator
		        HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
		    } catch(NoSuchAlgorithmException e) {
		        e.printStackTrace();
		    } catch(KeyManagementException e) {
		        e.printStackTrace();
		    }
		log.info("Inside call method - Start");
		
		URL url = new URL(this.uri);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(method);
		
		if (headers!=null && headers.size()>0) {
			for (String key: headers.keySet()) {
				conn.setRequestProperty(key,headers.get(key));
			}
		}
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
