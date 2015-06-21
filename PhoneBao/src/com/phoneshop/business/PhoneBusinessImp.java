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
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.phoneshop.javabean.Address;
import com.phoneshop.javabean.Comments;
import com.phoneshop.javabean.ShopBriefInfo;
import com.phoneshop.javabean.ShopCartInfo;
import com.phoneshop.javabean.ShopFilter;
import com.phoneshop.javabean.ShopInfo;
import com.phoneshop.utils.HttpUtils;

public class PhoneBusinessImp implements PhoneBusinessInterface {

	// 获取手机的简要信息
	@Override
	public List<ShopBriefInfo> tradeFilter(ShopFilter shopFilter, int order, int num, List<ShopBriefInfo> data) throws ConnectException,
			ConnectTimeoutException, SocketTimeoutException, Exception {
		data.clear();

		// 获取数据
		JSONObject json = new JSONObject();
		// 封装类数据
		json.put("content", shopFilter.getContent());
		json.put("brand", shopFilter.getBrand());
		json.put("cpu", shopFilter.getCpu());
		json.put("ram", shopFilter.getRam());
		json.put("rom", shopFilter.getRom());
		json.put("px", shopFilter.getPx());
		json.put("color", shopFilter.getColor());
		json.put("web", shopFilter.getWeb());
		json.put("price", shopFilter.getPrice());
		json.put("order", order);
		json.put("num", num);

		// Log.d("mykey", json.toString());
		// 设置webservice地址
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/getShopBriefInfo.do";
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

		JSONArray jsonArray = new JSONArray(result);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			data.add(new ShopBriefInfo(obj.getInt("id"), obj.getString("goodsName"), obj.getDouble("price"), obj.getInt("sellNum"), obj
					.getString("province"), obj.getString("city"), obj.getString("shopName")));
		}

		return data;
	}

	@Override
	public Bitmap getShopHeadPic(int id) throws ConnectException, ConnectTimeoutException, SocketTimeoutException, CustomException,
			Exception {
		// 设置webservice地址
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/getShopHeadPic.do";
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
		Bitmap bitmap = BitmapFactory.decodeStream(response.getEntity().getContent());
		return bitmap;
	}

	@Override
	public ShopInfo getShopInfo(int id) throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception {
		ShopInfo shopInfo = null;
		// 设置webservice地址
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/getShopInfo.do";
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
		if (id == 9) {
			Log.d("mykey", result);
		}
		JSONObject jsonObject = new JSONObject(result);

		shopInfo = new ShopInfo(jsonObject.getInt("id"), jsonObject.getString("name"), jsonObject.getString("no"),
				jsonObject.getDouble("price"), jsonObject.getInt("goodsNums"), jsonObject.getString("introduce"),
				jsonObject.getInt("sellNums"), jsonObject.getString("goodsContent"), jsonObject.getString("time"),
				jsonObject.getString("brand"), jsonObject.getString("cpu"), jsonObject.getString("phoneSize"), jsonObject.getString("os"),
				jsonObject.getString("ram"), jsonObject.getString("rom"), jsonObject.getString("resolution"),
				jsonObject.getString("preCamera"), jsonObject.getString("camera"), jsonObject.getString("color"),
				jsonObject.getString("commucation"));

		return shopInfo;
	}

	@Override
	public Bitmap getShopPic(int id, int index) throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception {
		// 设置webservice地址
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/getShopPic.do";
		HttpClient client;
		HttpUtils httpUtils = new HttpUtils();
		client = httpUtils.getNewHttpClient();

		HttpPost post = new HttpPost(uri);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		NameValuePair param = new BasicNameValuePair("id", String.valueOf(id));
		NameValuePair param2 = new BasicNameValuePair("index", String.valueOf(index));
		parameters.add(param);
		parameters.add(param2);

		post.setEntity(new UrlEncodedFormEntity(parameters));
		HttpResponse response = client.execute(post);
		// webservice请求码判断
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("服务器请求出错");
		}

		// 获取结果并判断//获取的结果为JSonArray信息
		Bitmap bitmap = BitmapFactory.decodeStream(response.getEntity().getContent());
		return bitmap;

	}

	@Override
	public void getShopComments(int id, List<Comments> params, int num) throws ConnectException, ConnectTimeoutException,
			SocketTimeoutException, Exception {
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/getShopComments.do";
		HttpClient client;
		HttpUtils httpUtils = new HttpUtils();
		client = httpUtils.getNewHttpClient();

		HttpPost post = new HttpPost(uri);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		NameValuePair param = new BasicNameValuePair("id", String.valueOf(id));
		parameters.add(param);
		NameValuePair param2 = new BasicNameValuePair("num", String.valueOf(num));
		parameters.add(param2);
		post.setEntity(new UrlEncodedFormEntity(parameters));
		HttpResponse response = client.execute(post);
		// webservice请求码判断
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("服务器请求出错");
		}

		// 获取结果并判断//获取的结果为JSonArray信息
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");

		JSONArray jsonArray = new JSONArray(result);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			params.add(new Comments(obj.getString("user"), obj.getString("content"), obj.getString("time")));
		}
	}

	@Override
	public void addToShopCart(int goodsID, String userName, int goodsNum) throws ConnectException, ConnectTimeoutException,
			CustomException, SocketTimeoutException, Exception {
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/addToShopCart.do";
		HttpClient client;
		HttpUtils httpUtils = new HttpUtils();
		client = httpUtils.getNewHttpClient();

		HttpPost post = new HttpPost(uri);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		NameValuePair param = new BasicNameValuePair("goodsID", String.valueOf(goodsID));
		parameters.add(param);
		NameValuePair param2 = new BasicNameValuePair("userName", String.valueOf(userName));
		parameters.add(param2);
		NameValuePair param3 = new BasicNameValuePair("goodsNum", String.valueOf(goodsNum));
		parameters.add(param3);

		post.setEntity(new UrlEncodedFormEntity(parameters));
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
	public void getShopCartInfo(String userName, List<ShopCartInfo> params) throws ConnectException, ConnectTimeoutException,
			SocketTimeoutException, CustomException, Exception {
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/getShopCartInfo.do";
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

		JSONArray array = new JSONArray(result);
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			params.add(new ShopCartInfo(obj.getInt("id"), obj.getInt("goodsID"), obj.getInt("userID"), obj.getString("goodsName"), obj
					.getInt("shopCartNum"), obj.getDouble("price")));
		}
	}

	@Override
	public void order(String userName, Map<Integer, Integer> info, int addressID) throws ConnectException, ConnectTimeoutException,
			SocketTimeoutException, CustomException, Exception {
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/playOrder.do";
		HttpClient client;
		HttpUtils httpUtils = new HttpUtils();
		client = httpUtils.getNewHttpClient();

		HttpPost post = new HttpPost(uri);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		NameValuePair param = new BasicNameValuePair("userName", String.valueOf(userName));
		parameters.add(param);
		JSONArray json = new JSONArray();
		for (Entry<Integer, Integer> entry : info.entrySet()) {
			int goodsID = entry.getKey();
			int goodsNum = entry.getValue();
			JSONObject obj = new JSONObject();
			obj.put("goodsID", goodsID);
			obj.put("goodsNum", goodsNum);
			json.put(obj);
		}
		NameValuePair param2 = new BasicNameValuePair("info", json.toString());
		parameters.add(param2);
		NameValuePair param3 = new BasicNameValuePair("addressID", String.valueOf(addressID));
		parameters.add(param3);

		post.setEntity(new UrlEncodedFormEntity(parameters));
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
	public List<Address> getAddress(String userName) throws ConnectException, ConnectTimeoutException, SocketTimeoutException,
			CustomException, Exception {
		List<Address> data = new ArrayList<Address>();
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/getAddressInfo.do";
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

		JSONArray array = new JSONArray(result);
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			data.add(new Address(obj.getInt("id"), obj.getString("province"), obj.getString("city"), obj.getString("country"), obj
					.getString("street"), obj.getString("phone"), obj.getString("name")));
		}

		return data;
	}

	@Override
	public Bitmap getShopHeadPics(int id, int index) throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception {
		// 设置webservice地址
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/getShopHeadPics.do";
		HttpClient client;
		HttpUtils httpUtils = new HttpUtils();
		client = httpUtils.getNewHttpClient();

		HttpPost post = new HttpPost(uri);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		NameValuePair param = new BasicNameValuePair("id", String.valueOf(id));
		NameValuePair param2 = new BasicNameValuePair("index", String.valueOf(index));
		parameters.add(param);
		parameters.add(param2);

		post.setEntity(new UrlEncodedFormEntity(parameters));
		HttpResponse response = client.execute(post);
		// webservice请求码判断
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("服务器请求出错");
		}

		// 获取结果并判断//获取的结果为JSonArray信息
		Bitmap bitmap = BitmapFactory.decodeStream(response.getEntity().getContent());
		return bitmap;
	}

	@Override
	public List<Integer> getADGoodsID() throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception {
		List<Integer> data = new ArrayList<Integer>();
		// 设置webservice地址
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/getAdGoodsIds.do";
		HttpClient client;
		HttpUtils httpUtils = new HttpUtils();
		client = httpUtils.getNewHttpClient();

		HttpPost post = new HttpPost(uri);

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
			data.add(array.getJSONObject(i).getInt("id"));
		}

		return data;
	}

	@Override
	public void getAdGoodsPic(int goodsID, List<Bitmap> pic) throws ConnectException, ConnectTimeoutException, SocketTimeoutException,
			Exception {
		// 设置webservice地址
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/getADPic.do";
		HttpClient client;
		HttpUtils httpUtils = new HttpUtils();
		client = httpUtils.getNewHttpClient();

		HttpPost post = new HttpPost(uri);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		NameValuePair param = new BasicNameValuePair("id", String.valueOf(goodsID));
		parameters.add(param);

		post.setEntity(new UrlEncodedFormEntity(parameters));
		HttpResponse response = client.execute(post);
		// webservice请求码判断
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("服务器请求出错");
		}

		// 获取结果并判断//获取的结果为JSonArray信息
		Bitmap bitmap = BitmapFactory.decodeStream(response.getEntity().getContent());

		pic.add(bitmap);

	}

	@Override
	public boolean isCollect(String userName, int goodsID) throws ConnectException, ConnectTimeoutException, SocketTimeoutException,
			Exception {
		// 设置webservice地址
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/isCollect.do";
		HttpClient client;
		HttpUtils httpUtils = new HttpUtils();
		client = httpUtils.getNewHttpClient();

		HttpPost post = new HttpPost(uri);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		NameValuePair param = new BasicNameValuePair("goodsID", String.valueOf(goodsID));
		parameters.add(param);
		NameValuePair param2 = new BasicNameValuePair("userName", String.valueOf(userName));
		parameters.add(param2);

		post.setEntity(new UrlEncodedFormEntity(parameters));

		HttpResponse response = client.execute(post);
		// webservice请求码判断
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("服务器请求出错");
		}
		// 获取结果并判断//获取的结果为JSonArray信息
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");

		if (result.equals("true"))
			return true;
		else
			return false;
	}

	@Override
	public void goodsCollect(String userName, int goodsID) throws ConnectException, ConnectTimeoutException, SocketTimeoutException,
			Exception {
		// 设置webservice地址
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/collectShop.do";
		HttpClient client;
		HttpUtils httpUtils = new HttpUtils();
		client = httpUtils.getNewHttpClient();

		HttpPost post = new HttpPost(uri);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		NameValuePair param = new BasicNameValuePair("goodsID", String.valueOf(goodsID));
		parameters.add(param);
		NameValuePair param2 = new BasicNameValuePair("userName", String.valueOf(userName));
		parameters.add(param2);

		post.setEntity(new UrlEncodedFormEntity(parameters));

		HttpResponse response = client.execute(post);
		// webservice请求码判断
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("服务器请求出错");
		}
		// 获取结果并判断//获取的结果为JSonArray信息
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");

		if (!result.equals("success")) {
			throw new Exception("收藏失败");
		}
	}

	@Override
	public void goodsDisCollect(String userName, int goodsID) throws ConnectException, ConnectTimeoutException, SocketTimeoutException,
			Exception {
		// 设置webservice地址
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/disCollectGoods.do";
		HttpClient client;
		HttpUtils httpUtils = new HttpUtils();
		client = httpUtils.getNewHttpClient();

		HttpPost post = new HttpPost(uri);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		NameValuePair param = new BasicNameValuePair("goodsID", String.valueOf(goodsID));
		parameters.add(param);
		NameValuePair param2 = new BasicNameValuePair("userName", String.valueOf(userName));
		parameters.add(param2);

		post.setEntity(new UrlEncodedFormEntity(parameters));

		HttpResponse response = client.execute(post);
		// webservice请求码判断
		int statuCode = response.getStatusLine().getStatusCode();
		if (statuCode != HttpStatus.SC_OK) {
			throw new CustomException("服务器请求出错");
		}
		// 获取结果并判断//获取的结果为JSonArray信息
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");

		if (!result.equals("success")) {
			throw new Exception("操作失败");
		}

	}

	@Override
	public void getGoodsCollectInfo(String userName, List<ShopBriefInfo> data) throws ConnectException, ConnectTimeoutException,
			SocketTimeoutException, Exception {
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/getGoodsCollect.do";
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
		

		JSONArray jsonArray = new JSONArray(result);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			data.add(new ShopBriefInfo(obj.getInt("id"), obj.getString("goodsName"), obj.getDouble("price"), obj.getInt("sellNum"), obj
					.getString("province"), obj.getString("city"), obj.getString("shopName")));
		}
	}

	@Override
	public void orderCancle(String userName, Map<Integer, Integer> info) throws ConnectException, ConnectTimeoutException,
			SocketTimeoutException, CustomException, Exception {
		String uri = "http://" + HttpUtils.url + "/PhoneBaoWebService/orderCancle.do";
		HttpClient client;
		HttpUtils httpUtils = new HttpUtils();
		client = httpUtils.getNewHttpClient();

		HttpPost post = new HttpPost(uri);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		NameValuePair param = new BasicNameValuePair("userName", String.valueOf(userName));
		parameters.add(param);
		JSONArray json = new JSONArray();
		for (Entry<Integer, Integer> entry : info.entrySet()) {
			int goodsID = entry.getKey();
			int goodsNum = entry.getValue();
			JSONObject obj = new JSONObject();
			obj.put("goodsID", goodsID);
			obj.put("goodsNum", goodsNum);
			json.put(obj);
		}
		NameValuePair param2 = new BasicNameValuePair("info", json.toString());
		parameters.add(param2);

		post.setEntity(new UrlEncodedFormEntity(parameters));
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

}
