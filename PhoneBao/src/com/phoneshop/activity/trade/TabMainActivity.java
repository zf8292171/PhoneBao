package com.phoneshop.activity.trade;

import com.example.phonebao.R;
import com.handmark.pulltorefresh.library.internal.Utils;
import com.phoneshop.activity.personer.LoginActivity;
import com.phoneshop.activity.phone.MainContentActivity;
import com.phoneshop.activity.phone.TabActivityHome;
import com.phoneshop.activity.phone.TabActivityPersonInfo;
import com.phoneshop.activity.phone.TabActivityShopCar;
import com.phoneshop.business.CustomException;
import com.phoneshop.business.PhoneBusinessImp;
import com.phoneshop.utils.UserTag;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class TabMainActivity extends TabActivity {

	public static Button btn;
	private PhoneBusinessImp business = new PhoneBusinessImp();
	private ProgressDialog prograssDialog;
	private int ID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tab_main_2);

		// 获取商品ID
		ID = -1;
		ID = getIntent().getIntExtra("ID", -1);
		if (ID == -1) {
			showTip("获取ID错误，请联系客服解决问题");
			finish();
			return;
		}
		// 获取购买的button
		btn = (Button) findViewById(R.id.btn);
		Bundle data = new Bundle();
		TabHost myTab = getTabHost();
		TabSpec specMain = myTab.newTabSpec("info").setIndicator("商品信息")
				.setContent(new Intent(this, TabShopInfoActiivty.class).putExtra("ID", ID));
		TabSpec specContent = myTab.newTabSpec("pic").setIndicator("图文信息")
				.setContent(new Intent(this, TabShopPicActivity.class).putExtra("ID", ID));
		TabSpec specPerson = myTab.newTabSpec("comment").setIndicator("买家评价")
				.setContent(new Intent(this, TabShopCommentsActivity.class).putExtra("ID", ID));
		myTab.addTab(specMain);
		myTab.addTab(specContent);
		myTab.addTab(specPerson);
		ID = getIntent().getIntExtra("ID", -1);

		// 为购买按钮设置监听事件
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 弹出窗口，是否确认购买
				new AlertDialog.Builder(TabMainActivity.this).setTitle("确定？").setMessage("是否确定购买此商品？")
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// 验证是否登陆
								if (UserTag.userName == null) {
									new AlertDialog.Builder(TabMainActivity.this).setTitle("提示").setMessage("您还未登陆，是否转去登陆页面？")
											.setPositiveButton("确定", new DialogInterface.OnClickListener() {

												@Override
												public void onClick(DialogInterface dialog, int which) {
													Intent intent = new Intent(TabMainActivity.this, LoginActivity.class);
													startActivity(intent);
												}
											}).setNegativeButton("取消", null).setCancelable(false).show();
									return;
								}
								// 弹出等待框
								if (prograssDialog == null) {

									prograssDialog = new ProgressDialog(TabMainActivity.this);
									prograssDialog.setTitle("等待");
									prograssDialog.setMessage("登陆中...");
									prograssDialog.setCancelable(false);
									prograssDialog.show();

								}

								// 添加至购物车
								new Thread(new Runnable() {

									@Override
									public void run() {

										// 事件
										try {
											business.addToShopCart(ID, UserTag.userName, 1);
											Message msg = new Message();
											msg.what = 1;
											handler.sendMessage(msg);
										} catch (CustomException e) {
											e.printStackTrace();
											Message msg = new Message();
											msg.what = 0;
											Bundle data = new Bundle();
											data.putString("error", e.getMessage());
											msg.setData(data);
											handler.sendMessage(msg);
										} catch (Exception e) {
											e.printStackTrace();
											Message msg = new Message();
											msg.what = -1;
											handler.sendMessage(msg);
										}
									}
								}).start();

								// 确认购买后询问是否结账还是继续浏览？
								new AlertDialog.Builder(TabMainActivity.this).setTitle("选择").setMessage("去结账还是继续游览")
										.setPositiveButton("结账", new DialogInterface.OnClickListener() {

											@Override
											public void onClick(DialogInterface dialog, int which) {
												Intent intent = new Intent(TabMainActivity.this, MainContentActivity.class);
												startActivity(intent);
											}
										}).setNegativeButton("继续游览", null).setCancelable(false).show();
							}
						}).setNegativeButton("取消", null).setCancelable(false).show();
			}
		});

	}

	public void showTip(String message) {
		Toast.makeText(this, message, 3000).show();
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (prograssDialog != null) {
				prograssDialog.dismiss();
				prograssDialog = null;
			}

			switch (msg.what) {
			case 1:
				showTip(msg.getData().getString("添加购物车成功"));
				break;
			case 0:
				showTip(msg.getData().getString("error"));
				break;
			case -1:
				showTip("添加购物车失败");
				break;
			}

		};
	};

}
