package com.phoneshop.activity.personer;

import java.util.ArrayList;
import java.util.List;

import com.example.phonebao.R;
import com.phoneshop.business.UserBusinessImp;
import com.phoneshop.utils.UserTag;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AddAddressActiivity extends Activity {

	private ListView listview;
	private List<String> params = new ArrayList<String>();
	private MyArrayAdapter adapter;
	private UserBusinessImp business = new UserBusinessImp();

	private TextView back, title;
	int flag = 0;// 1表示province 2表示city 3表示country

	private String province = null, city = null, country = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.add_address);

		listview = (ListView) findViewById(R.id.listview);
		adapter = new MyArrayAdapter(this, R.layout.listview_items1, params);
		back = (TextView) findViewById(R.id.title_left);
		title = (TextView) findViewById(R.id.title_center);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				switch (flag) {
				case 1:
					province = adapter.getItem(position);
					getCity();
					break;
				case 2:
					city = adapter.getItem(position);
					getCountry();
					break;
				case 3:
					country = adapter.getItem(position);
					final View view2 = LayoutInflater.from(AddAddressActiivity.this).inflate(R.layout.addressinfo, null);
					new AlertDialog.Builder(AddAddressActiivity.this).setTitle("填写基本信息").setView(view2)
							.setPositiveButton("确定", new OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog, int which) {
									final TextView name = (TextView) view2.findViewById(R.id.name);
									final TextView phone = (TextView) view2.findViewById(R.id.phone);
									final TextView address = (TextView) view2.findViewById(R.id.address);
									if (name.getText().toString().trim().equals("") || phone.getText().toString().trim().equals("")
											|| address.getText().toString().trim().equals("")) {
										showTip("不能为空");
										return;
									}
									new Thread(new Runnable() {

										@Override
										public void run() {
											try {
												business.insertAddress(province, city, country, name.getText().toString().trim(), phone
														.getText().toString().trim(), address.getText().toString().trim(), UserTag.userName);
												Message msg = new Message();
												msg.what = 1;
												handler2.sendMessage(msg);

											} catch (Exception e) {
												e.printStackTrace();
												Message msg = new Message();
												msg.what = -1;
												handler2.sendMessage(msg);
											}
										}
									}).start();
								}
							}).setNegativeButton("取消", new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog, int which) {
									finish();
								}
							}).show().setCancelable(false);;
					break;
				default:
					break;
				}
			}
		});

		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (flag) {
				case 1:
					finish();
					break;
				case 2:
					city = null;
					getProvince();
					break;
				case 3:
					country = null;
					getCity();
					break;
				default:
					break;
				}
			}
		});
		getProvince();

	}

	private void getProvince() {
		flag = 1;
		title.setText("选择省份");
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					params.clear();
					business.getProvice(params);
					Message msg = new Message();
					msg.what = 1;
					handler.sendMessage(msg);
				} catch (Exception e) {
					e.printStackTrace();
					Message msg = new Message();
					msg.what = -1;
					handler.sendMessage(msg);
				}
			}
		}).start();
	}

	private void getCity() {
		flag = 2;
		title.setText("选择城市");
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					params.clear();
					business.getCity(params, province);
					;
					Message msg = new Message();
					msg.what = 1;
					handler.sendMessage(msg);
				} catch (Exception e) {
					e.printStackTrace();
					Message msg = new Message();
					msg.what = -1;
					handler.sendMessage(msg);
				}
			}
		}).start();
	}

	private void getCountry() {
		flag = 3;
		title.setText("选择乡镇");
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					params.clear();
					business.getCountry(params, city);
					Message msg = new Message();
					msg.what = 1;
					handler.sendMessage(msg);
				} catch (Exception e) {
					e.printStackTrace();
					Message msg = new Message();
					msg.what = -1;
					handler.sendMessage(msg);
				}
			}
		}).start();

	}

	private class MyArrayAdapter extends ArrayAdapter<String> {

		private Context context;
		private int textViewResourceId;

		public MyArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
			super(context, textViewResourceId, objects);
			this.context = context;
			this.textViewResourceId = textViewResourceId;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = LayoutInflater.from(context).inflate(textViewResourceId, parent, false);
			TextView text = (TextView) view.findViewById(R.id.text1);
			text.setText(getItem(position));
			return view;

		}

	}

	// 获取地址的handler
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				// 成功后执行
				adapter.notifyDataSetChanged();
				break;
			case -1:
				// 程序的错误
				showTip("数据获取失败");
				break;
			}
		}

	};

	// 插入地址的
	private Handler handler2 = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				// 成功后执行
				finish();
				showTip("添加成功");
				break;
			case -1:
				// 程序的错误
				showTip("添加失败");
				finish();
				break;
			}
		}

	};

	private void showTip(String message) {
		Toast.makeText(this, message, 3000).show();
	}

}
