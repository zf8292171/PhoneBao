package com.phoneshop.activity.phone;

import java.net.SocketTimeoutException;

import org.apache.http.conn.ConnectTimeoutException;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;

import com.example.phonebao.R;
import com.phoneshop.business.PhoneBusinessImp;

@SuppressWarnings("deprecation")
public class MainContentActivity extends TabActivity {
	
	private View viewHome ,viewShopCar,viewPersoner;
	private ImageView img;
	private TextView text;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tab_main);
		Drawable home = getResources().getDrawable(R.drawable.extra_home);
		home.setBounds(0, 0, 50, 50);
		Drawable shopcar = getResources().getDrawable(R.drawable.extra_shopcar);
		home.setBounds(0, 0, 50, 50);
		Drawable personer = getResources().getDrawable(R.drawable.extra_person);
		home.setBounds(0, 0, 50, 50);
		
		TabHost myTab = getTabHost();
		viewHome = LayoutInflater.from(this).inflate(R.layout.icon, null);
		viewShopCar =  LayoutInflater.from(this).inflate(R.layout.icon, null);
		viewPersoner = LayoutInflater.from(this).inflate(R.layout.icon, null);
		TabSpec specMain = myTab.newTabSpec("home").setIndicator(viewHome)
				.setContent(new Intent(this,TabActivityHome.class));
		TabSpec specContent = myTab.newTabSpec("content").setIndicator(viewShopCar)
				.setContent(new Intent(this,TabActivityShopCar.class));
		TabSpec specPerson = myTab.newTabSpec("personer").setIndicator(viewPersoner)
				.setContent(new Intent(this,TabActivityPersonInfo.class));
		myTab.addTab(specMain);
		myTab.addTab(specContent);
		myTab.addTab(specPerson);
		
		img = (ImageView) viewHome.findViewById(R.id.img);
		img.setBackgroundResource(R.drawable.home2);
		text = (TextView) viewHome.findViewById(R.id.text);
		text.setTextColor(getResources().getColor(R.color.font));
		text.setText("主页");
		
		img = (ImageView) viewShopCar.findViewById(R.id.img);
		img.setBackgroundResource(R.drawable.shopcart1);
		text = (TextView) viewShopCar.findViewById(R.id.text);
		text.setText("购物车");
		
		img = (ImageView) viewPersoner.findViewById(R.id.img);
		img.setBackgroundResource(R.drawable.personer2);
		text = (TextView) viewPersoner.findViewById(R.id.text);
		text.setText("个人信息");
		
		myTab.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				if(tabId.equals("home")){
					img = (ImageView) viewHome.findViewById(R.id.img);
					img.setBackgroundResource(R.drawable.home2);
					text = (TextView) viewHome.findViewById(R.id.text);
					text.setTextColor(getResources().getColor(R.color.font));
					text.setText("主页");
					
					img = (ImageView) viewShopCar.findViewById(R.id.img);
					img.setBackgroundResource(R.drawable.shopcart1);
					text = (TextView) viewShopCar.findViewById(R.id.text);
					text.setTextColor(getResources().getColor(R.color.font2));
					text.setText("购物车");
					
					img = (ImageView) viewPersoner.findViewById(R.id.img);
					img.setBackgroundResource(R.drawable.personer2);
					text = (TextView) viewPersoner.findViewById(R.id.text);
					text.setTextColor(getResources().getColor(R.color.font2));
					text.setText("个人信息");
					return ;
				}
				if(tabId.equals("content")){
					img = (ImageView) viewHome.findViewById(R.id.img);
					img.setBackgroundResource(R.drawable.home1);
					text = (TextView) viewHome.findViewById(R.id.text);
					text.setTextColor(getResources().getColor(R.color.font2));
					text.setText("主页");
					
					img = (ImageView) viewShopCar.findViewById(R.id.img);
					img.setBackgroundResource(R.drawable.shopcart2);
					text = (TextView) viewShopCar.findViewById(R.id.text);
					text.setTextColor(getResources().getColor(R.color.font));
					text.setText("购物车");
					
					img = (ImageView) viewPersoner.findViewById(R.id.img);
					img.setBackgroundResource(R.drawable.personer2);
					text = (TextView) viewPersoner.findViewById(R.id.text);
					text.setTextColor(getResources().getColor(R.color.font2));
					text.setText("个人信息");
					return ;
				}
				if(tabId.equals("personer")){
					img = (ImageView) viewHome.findViewById(R.id.img);
					img.setBackgroundResource(R.drawable.home1);
					text = (TextView) viewHome.findViewById(R.id.text);
					text.setTextColor(getResources().getColor(R.color.font2));
					text.setText("主页");
					
					img = (ImageView) viewShopCar.findViewById(R.id.img);
					img.setBackgroundResource(R.drawable.shopcart1);
					text = (TextView) viewShopCar.findViewById(R.id.text);
					text.setTextColor(getResources().getColor(R.color.font2));
					text.setText("购物车");
					
					img = (ImageView) viewPersoner.findViewById(R.id.img);
					img.setBackgroundResource(R.drawable.personer1);
					text = (TextView) viewPersoner.findViewById(R.id.text);
					text.setTextColor(getResources().getColor(R.color.font));
					text.setText("个人信息");
					return ;
				}
			}
		});
		
	}
}
