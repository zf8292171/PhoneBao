package com.phoneshop.business;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Map;

import org.apache.http.conn.ConnectTimeoutException;

import android.graphics.Bitmap;

import com.phoneshop.javabean.Address;
import com.phoneshop.javabean.Comments;
import com.phoneshop.javabean.ShopBriefInfo;
import com.phoneshop.javabean.ShopCartInfo;
import com.phoneshop.javabean.ShopFilter;
import com.phoneshop.javabean.ShopInfo;

public interface PhoneBusinessInterface {

	public List<ShopBriefInfo> tradeFilter(ShopFilter shopFilter, int order, int num, List<ShopBriefInfo> data) throws ConnectException,
			ConnectTimeoutException, SocketTimeoutException, Exception;

	public Bitmap getShopHeadPic(int id) throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception;

	public ShopInfo getShopInfo(int id) throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception;

	public Bitmap getShopPic(int id, int index) throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception;
	
	public void getShopComments(int id,List<Comments> params,int num) throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception;
	
	public void addToShopCart(int goodsID,String userName,int goodsNum) throws ConnectException, ConnectTimeoutException, SocketTimeoutException,CustomException, Exception;

	public void getShopCartInfo(String userName,List<ShopCartInfo> params) throws ConnectException, ConnectTimeoutException, SocketTimeoutException,CustomException, Exception;

	public List<Address> getAddress(String userName) throws ConnectException, ConnectTimeoutException, SocketTimeoutException,CustomException, Exception;
	
	public void order(String userName,Map<Integer,Integer> info,int addressID) throws ConnectException, ConnectTimeoutException, SocketTimeoutException,CustomException, Exception;

	public Bitmap getShopHeadPics(int id,int index) throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception;

	public List<Integer> getADGoodsID() throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception;
	
	public void getAdGoodsPic(int goodsID,List<Bitmap> pic) throws  ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception;

	public boolean isCollect(String userName,int goodsID) throws  ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception; 
	public void goodsCollect(String userName,int goodsID) throws  ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception; 
	public void goodsDisCollect(String userName,int goodsID) throws  ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception; 

	public void getGoodsCollectInfo(String userName,List<ShopBriefInfo> data) throws ConnectException, ConnectTimeoutException, SocketTimeoutException, Exception; 
	
	public void orderCancle(String userName,Map<Integer,Integer> info) throws ConnectException, ConnectTimeoutException, SocketTimeoutException,CustomException, Exception;
}
