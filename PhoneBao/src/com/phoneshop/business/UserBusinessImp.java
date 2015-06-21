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

	// 用户登陆事件
	@Override
	public void login(String user, String password) throws ConnectTimeoutException, SocketTimeoutException, ConnectException, Exception {

		// 设置webservice地址
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/login.do";
		HttpClient client;
		HttpUtils httpUtils = new HttpUtils();
		client = httpUtils.getNewHttpClient();

		HttpPost post = new HttpPost(uri);

		// 设置传递数据
		NameValuePair paraUser = new BasicNameValuePair("user", user);
		NameValuePair paraPassword = new BasicNameValuePair("password", password);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(paraUser);
		parameters.add(paraPassword);
		post.setEntity(new UrlEncodedFormEntity(parameters));

		HttpResponse response = client.execute(post);

		// webservice请求码判断
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("服务器请求出错");
		}

		// 获取结果并判断
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");

		if (!result.equals("success")) {
			throw new CustomException(result);
		}

	}

	// 检测用户重名事件
	@Override
	public void userRepeter(String user) throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception {
		// 设置webservice地址
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

		// webservice请求码判断
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("服务器请求出错");
		}

		// 获取结果并判断
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");

		if (!result.equals("success")) {
			throw new CustomException(result);
		}
	}

	// 用户注册事件
	@Override
	public void userRegeist(Map<String, String> data) throws ConnectTimeoutException, ConnectException, SocketTimeoutException, Exception {

		JSONObject json = new JSONObject();
		for (Entry<String, String> para : data.entrySet()) {
			json.put(para.getKey(), para.getValue());
		}

		// 设置webservice地址
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

		// webservice请求码判断
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("服务器请求出错");
		}

		// 获取结果并判断
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
		// webservice请求码判断
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("服务器请求出错");
		}

		// 获取结果并判断//获取的结果为JSonArray信息
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");

		// 解析结果

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
		// webservice请求码判断
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("服务器请求出错");
		}

		// 获取结果并判断//获取的结果为JSonArray信息
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");

		// 解析结果
		if (!result.equals("success")) {
			throw new CustomException("删除失败");
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
		// webservice请求码判断
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("服务器请求出错");
		}

		// 获取结果并判断//获取的结果为JSonArray信息
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
		// webservice请求码判断
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("服务器请求出错");
		}

		// 获取结果并判断//获取的结果为JSonArray信息
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
		// webservice请求码判断
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("服务器请求出错");
		}

		// 获取结果并判断//获取的结果为JSonArray信息
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
		// 获取数据
		JSONObject json = new JSONObject();
		// 封装类数据
		json.put("province", province);
		json.put("city", city);
		json.put("country", country);
		json.put("name", name);
		json.put("phone", phone);
		json.put("street", address);
		json.put("userName", userName);
		// Log.d("mykey", json.toString());
		// 设置webservice地址
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

		// webservice请求码判断
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("服务器请求出错");
		}
		// 获取结果并判断//获取的结果为JSonArray信息
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
		// webservice请求码判断
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("服务器请求出错");
		}

		// 获取结果并判断//获取的结果为JSonArray信息
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");
		return Double.valueOf(result);
	}

}
