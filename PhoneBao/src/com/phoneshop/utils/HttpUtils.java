package com.phoneshop.utils;


import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;


public class HttpUtils {
	public static String url = "192.168.103.1:8080";


	public HttpClient getNewHttpClient() {
		HttpClient client;
		// ���ò���
		HttpParams params = new BasicHttpParams();
		// ���ô����ַ���
		HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
		// ���ӳ�ʱ
		HttpConnectionParams.setConnectionTimeout(params, 20*1000);
		// ��Ӧ��ʱ
		HttpConnectionParams.setSoTimeout(params, 20*1000);
		// ����Э��
		SchemeRegistry schreg = new SchemeRegistry();
		schreg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		ClientConnectionManager conman = new ThreadSafeClientConnManager(params, schreg);
		client = new DefaultHttpClient(conman, params);
		return client;
	}

}
