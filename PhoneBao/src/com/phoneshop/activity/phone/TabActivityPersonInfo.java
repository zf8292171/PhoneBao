package com.phoneshop.activity.phone;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phonebao.R;
import com.phoneshop.activity.personer.AddressActivity;
import com.phoneshop.activity.personer.CollectActivity;
import com.phoneshop.activity.personer.LoginActivity;
import com.phoneshop.activity.personer.OrderActivity;
import com.phoneshop.business.UserBusinessImp;
import com.phoneshop.customview.Title;
import com.phoneshop.utils.UserTag;

@SuppressWarnings("unused")
public class TabActivityPersonInfo extends Activity {

	private Title title;
	private LinearLayout btn_login;

	private TextView txtUser, txtTag;
	private LinearLayout btnLogout, btnExit,btnOrder,btnCollect,btnAddress,btnMoney;
	
	private static final int FIRSTOPEN = 0;
	private static final int LOGINOPEN = 1;
	private static final int LOGOUTOPEN = 2;
	private int tag = 0;// 登陆标志，，目的：优化性能
	
	private UserBusinessImp business = new UserBusinessImp();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_main_personinfo);
		// 初始化数据
		init();

		// 事件监听
		viewListener();

		// 每次跳到该页面都要执行的事件
		repeterDo();

	}

	private void init() {
		title = (Title) findViewById(R.id.title);
		((TextView) title.findViewById(R.id.title_right)).setText("");
		((TextView) title.findViewById(R.id.title_center)).setText("个人信息");
		((TextView) title.findViewById(R.id.title_left)).setText("");
		((TextView) title.findViewById(R.id.title_left))
				.setOnClickListener(null);
		btn_login = (LinearLayout) findViewById(R.id.tab_person_btn_login);
		txtUser = (TextView) findViewById(R.id.tab_person_txt_user);
		txtTag = (TextView) findViewById(R.id.tab_person_txt_tag);
		btnLogout = (LinearLayout) findViewById(R.id.tab_person_btn_logout);
		btnExit = (LinearLayout) findViewById(R.id.tab_person_btn_exit);
		btnOrder= (LinearLayout) findViewById(R.id.btn_order);
		btnCollect = (LinearLayout) findViewById(R.id.btn_collect);
		btnAddress= (LinearLayout) findViewById(R.id.btn_address);
		btnMoney = (LinearLayout) findViewById(R.id.money);
	}

	private void viewListener() {
		// 对退出登陆按钮的一个事件监听
		// 如果已登陆，则退出登陆，并刷新界面，如果未登陆，则提醒您尚未登陆
		btnLogout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (UserTag.userName != null) {
					UserTag.userName = null;
					repeterDo();
				} else {
					Toast.makeText(TabActivityPersonInfo.this, "您尚未登陆",
							Toast.LENGTH_LONG).show();
					;
				}
			}
		});

		btnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				TabActivityPersonInfo.this.finish();
			}
		});
		
		//我的订单事件
		btnOrder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(UserTag.userName==null){
					showTip("请登录后再执行此操作");
					return ;
				}else{
					Intent intent = new Intent(TabActivityPersonInfo.this,OrderActivity.class);
					startActivity(intent);
				}
			}
		});
		
		//收藏商品事件
		btnCollect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(UserTag.userName==null){
					showTip("请登录后再执行此操作");
					return ;
				}else{
					Intent intent = new Intent(TabActivityPersonInfo.this,CollectActivity.class);
					startActivity(intent);
				}
			}
		});
		
		//我的地址事件
		btnAddress.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(UserTag.userName==null){
					showTip("请登录后再执行此操作");
					return ;
				}else{
					Intent intent = new Intent(TabActivityPersonInfo.this,AddressActivity.class);
					startActivity(intent);
				}
			}
		});
		
		btnMoney.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(UserTag.userName==null){
					showTip("请登录后再执行此操作");
					return ;
				}else{
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							try {
								final Double money = business.getMoney(UserTag.userName);
								runOnUiThread(new Runnable() {
									
									@Override
									public void run() {
										new AlertDialog.Builder(TabActivityPersonInfo.this).setMessage("MONEY")
										.setMessage(String.valueOf(money)).show();
									}
								});
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}).start();
				}
			}
		});
	}

	//以下为每次切换到该页面都要进行更新
	private void repeterDo() {
		//检测用户是否存在，并更新控件以及其监听事件
		if (UserTag.userName == null) {
			if (tag == LOGOUTOPEN) {
				return ;
			}
			txtUser.setText("");
			txtTag.setText("未登录");
			btn_login.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(TabActivityPersonInfo.this,
							LoginActivity.class);
					startActivity(intent);
				}
			});
			tag = LOGOUTOPEN;
		} else {
			if (tag == LOGINOPEN) {
				return ;
			}
			txtUser.setText(UserTag.userName);
			txtTag.setText("已登录");
			btn_login.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(TabActivityPersonInfo.this, "已登录",
							Toast.LENGTH_LONG).show();
					;
				}
			});
			tag = LOGINOPEN;
		}
	}

	@Override
	protected void onResume() {
		super.onRestart();
		repeterDo();

	}
	
	private void showTip(String message) {
		Toast.makeText(this, message, 3000).show();
	}

}
