package com.phoneshop.business;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.phoneshop.javabean.Address;
import com.phoneshop.javabean.Order;
import com.phoneshop.javabean.ShopBriefInfo;
import com.phoneshop.utils.HttpUtils;

public class UserBusinessImp implements UserBusinessInterface {

	// �û���½�¼�
	@Override
	public void login(String user, String password) throws ConnectTimeoutException, SocketTimeoutException, ConnectException, Exception {

		// ����webservice��ַ
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/login.do";
		HttpClient client;
		HttpUtils httpUtils = new HttpUtils();
		client = httpUtils.getNewHttpClient();

		HttpPost post = new HttpPost(uri);

		// ���ô�������
		NameValuePair paraUser = new BasicNameValuePair("user", user);
		NameValuePair paraPassword = new BasicNameValuePair("password", password);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(paraUser);
		parameters.add(paraPassword);
		post.setEntity(new UrlEncodedFormEntity(parameters));

		HttpResponse response = client.execute(post);

		// webservice�������ж�
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("�������������");
		}

		// ��ȡ������ж�
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");

		if (!result.equals("success")) {
			throw new CustomException(result);
		}

	}

	// ����û������¼�
	@Override
	public void userRepeter(String user) throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception {
		// ����webservice��ַ
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/repeter.do";
		HttpClient client;
		HttpUtils httpUtils = new HttpUtils();
		client = httpUtils.getNewHttpClient();

		HttpPost post = new HttpPost(uri);

		NameValuePair paraUser = new BasicNameValuePair("user", user);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(paraUser);
		post.setEntity(new UrlEncodedFormEntity(parameters));
		HttpResponse response = client.execute(post);

		// webservice�������ж�
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("�������������");
		}

		// ��ȡ������ж�
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");

		if (!result.equals("success")) {
			throw new CustomException(result);
		}
	}

	// �û�ע���¼�
	@Override
	public void userRegeist(Map<String, String> data) throws ConnectTimeoutException, ConnectException, SocketTimeoutException, Exception {

		JSONObject json = new JSONObject();
		for (Entry<String, String> para : data.entrySet()) {
			json.put(para.getKey(), para.getValue());
		}

		// ����webservice��ַ
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/regeist.do";
		HttpClient client;
		HttpUtils httpUtils = new HttpUtils();
		client = httpUtils.getNewHttpClient();

		HttpPost post = new HttpPost(uri);
		NameValuePair para = new BasicNameValuePair("json", json.toString());
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(para);

		post.setEntity(new UrlEncodedFormEntity(parameters));
		HttpResponse response = client.execute(post);

		// webservice�������ж�
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("�������������");
		}

		// ��ȡ������ж�
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");

		if (!result.equals("success")) {
			throw new CustomException(result);
		}
	}

	@Override
	public void getOrderInfo(String userName, List<Order> data) throws ConnectException, ConnectTimeoutException, SocketTimeoutException,
			Exception {
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/getOrderInfo.do";
		HttpClient client;
		HttpUtils httpUtils = new HttpUtils();
		client = httpUtils.getNewHttpClient();

		HttpPost post = new HttpPost(uri);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		NameValuePair param = new BasicNameValuePair("userName", String.valueOf(userName));
		parameters.add(param);

		post.setEntity(new UrlEncodedFormEntity(parameters));
		HttpResponse response = client.execute(post);
		// webservice�������ж�
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("�������������");
		}

		// ��ȡ������ж�//��ȡ�Ľ��ΪJSonArray��Ϣ
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");

		// �������

		JSONArray array = new JSONArray(result);
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			data.add(new Order(obj.getInt("id"), obj.getInt("goodsID"), obj.getString("goodsName"), obj.getInt("goodsNum"), obj
					.getDouble("totlePrice"), obj.getString("orderState"), obj.getString("shopName"), obj.getString("time"), obj
					.getString("city"), obj.getString("country"), obj.getString("province"), obj.getString("street"),
					obj.getString("name"), obj.getString("phone")));
		}

	}

	@Override
	public void deleteAdress(int id) throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception {
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/deleteAdress.do";
		HttpClient client;
		HttpUtils httpUtils = new HttpUtils();
		client = httpUtils.getNewHttpClient();

		HttpPost post = new HttpPost(uri);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		NameValuePair param = new BasicNameValuePair("id", String.valueOf(id));
		parameters.add(param);

		post.setEntity(new UrlEncodedFormEntity(parameters));
		HttpResponse response = client.execute(post);
		// webservice�������ж�
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("�������������");
		}

		// ��ȡ������ж�//��ȡ�Ľ��ΪJSonArray��Ϣ
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");

		// �������
		if (!result.equals("success")) {
			throw new CustomException("ɾ��ʧ��");
		}
	}

	@Override
	public void getProvice(List<String> data) throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception {
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/getProvince.do";
		HttpClient client;
		HttpUtils httpUtils = new HttpUtils();
		client = httpUtils.getNewHttpClient();

		HttpPost post = new HttpPost(uri);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();

		post.setEntity(new UrlEncodedFormEntity(parameters));
		HttpResponse response = client.execute(post);
		// webservice�������ж�
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("�������������");
		}

		// ��ȡ������ж�//��ȡ�Ľ��ΪJSonArray��Ϣ
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");

		JSONArray array = new JSONArray(result);
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			data.add(obj.getString("str"));
		}

	}

	@Override
	public void getCity(List<String> data, String province) throws ConnectException, ConnectTimeoutException, SocketTimeoutException,
			Exception {
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/getCity.do";
		HttpClient client;
		HttpUtils httpUtils = new HttpUtils();
		client = httpUtils.getNewHttpClient();

		HttpPost post = new HttpPost(uri);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		NameValuePair param = new BasicNameValuePair("province", province);
		parameters.add(param);
		post.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
		HttpResponse response = client.execute(post);
		// webservice�������ж�
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("�������������");
		}

		// ��ȡ������ж�//��ȡ�Ľ��ΪJSonArray��Ϣ
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");

		JSONArray array = new JSONArray(result);
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			data.add(obj.getString("str"));
		}
	}

	@Override
	public void getCountry(List<String> data, String city) throws ConnectException, ConnectTimeoutException, SocketTimeoutException,
			Exception {
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/getCountry.do";
		HttpClient client;
		HttpUtils httpUtils = new HttpUtils();
		client = httpUtils.getNewHttpClient();

		HttpPost post = new HttpPost(uri);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		NameValuePair param = new BasicNameValuePair("city", city);
		parameters.add(param);
		post.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
		HttpResponse response = client.execute(post);
		// webservice�������ж�
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("�������������");
		}

		// ��ȡ������ж�//��ȡ�Ľ��ΪJSonArray��Ϣ
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");

		JSONArray array = new JSONArray(result);
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			data.add(obj.getString("str"));
		}
	}

	@Override
	public void insertAddress(String province, String city, String country, String name, String phone, String address, String userName)
			throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception {
		// ��ȡ����
		JSONObject json = new JSONObject();
		// ��װ������
		json.put("province", province);
		json.put("city", city);
		json.put("country", country);
		json.put("name", name);
		json.put("phone", phone);
		json.put("street", address);
		json.put("userName", userName);
		// Log.d("mykey", json.toString());
		// ����webservice��ַ
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/insertAddress.do";
		HttpClient client;
		HttpUtils httpUtils = new HttpUtils();
		client = httpUtils.getNewHttpClient();

		HttpPost post = new HttpPost(uri);
		NameValuePair para = new BasicNameValuePair("json", new String(json.toString().getBytes(), "UTF-8"));
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(para);

		post.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
		HttpResponse response = client.execute(post);

		// webservice�������ж�
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("�������������");
		}
		// ��ȡ������ж�//��ȡ�Ľ��ΪJSonArray��Ϣ
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");

		if (!result.equals("success")) {
			throw new CustomException(result);
		}
	}

	@Override
	public Double getMoney(String userName) throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception {
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/getMoney.do";
		HttpClient client;
		HttpUtils httpUtils = new HttpUtils();
		client = httpUtils.getNewHttpClient();

		HttpPost post = new HttpPost(uri);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		NameValuePair param = new BasicNameValuePair("userName", String.valueOf(userName));
		parameters.add(param);

		post.setEntity(new UrlEncodedFormEntity(parameters));
		HttpResponse response = client.execute(post);
		// webservice�������ж�
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("�������������");
		}

		// ��ȡ������ж�//��ȡ�Ľ��ΪJSonArray��Ϣ
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");
		return Double.valueOf(result);
	}

}
