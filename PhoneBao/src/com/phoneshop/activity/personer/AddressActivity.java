package com.phoneshop.activity.personer;

import java.util.ArrayList;
import java.util.List;

import com.example.phonebao.R;
import com.phoneshop.business.CustomException;
import com.phoneshop.business.PhoneBusinessImp;
import com.phoneshop.business.UserBusinessImp;
import com.phoneshop.javabean.Address;
import com.phoneshop.utils.UserTag;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AddressActivity extends Activity {

	private ListView listview;
	private List<Address> params = new ArrayList<Address>();
	private MyArrayAdapter adapter;
	private PhoneBusinessImp business = new PhoneBusinessImp();
	private UserBusinessImp userBusiness = new UserBusinessImp();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.address_layout);

		adapter = new MyArrayAdapter(this, R.layout.listview2, params);

		listview = (ListView) findViewById(R.id.listview);
		listview.setAdapter(adapter);

		getAdressInfo();

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// 获取地址id
				final int ID = adapter.getItem(position).getId();
				String[] items = new String[] { "删除" };
				new AlertDialog.Builder(AddressActivity.this).setMessage("选项").setItems(items, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						new AlertDialog.Builder(AddressActivity.this).setMessage("确定？").setMessage("确定删除？")
								.setPositiveButton("确定", new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog, int which) {
										// 删除地址
										new Thread(new Runnable() {

											@Override
											public void run() {
												try {
													userBusiness.deleteAdress(ID);
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
												}
											}
										}).start();
									}
								}).setNegativeButton("取消", null).show().setCancelable(false);
					}
				}).show();
			}
		});

	}

	private void getAdressInfo() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					List<Address> list = null;
					list = business.getAddress(UserTag.userName);
					Message msg = new Message();
					msg.what = 1;
					Bundle data = new Bundle();
					data.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) list);
					msg.setData(data);
					handler3.sendMessage(msg);
				} catch (Exception e) {
					e.printStackTrace();
					Message msg = new Message();
					msg.what = -1;
					handler3.sendMessage(msg);
				}
			}
		}).start();
	}

	private class MyArrayAdapter extends ArrayAdapter<Address> {

		private Context context;
		private int textViewResourceId;

		public MyArrayAdapter(Context context, int textViewResourceId, List<Address> objects) {
			super(context, textViewResourceId, objects);
			this.context = context;
			this.textViewResourceId = textViewResourceId;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = LayoutInflater.from(context).inflate(textViewResourceId, parent, false);
			TextView name = (TextView) view.findViewById(R.id.name);
			TextView phone = (TextView) view.findViewById(R.id.phone);
			TextView address = (TextView) view.findViewById(R.id.address);
			name.setText(getItem(position).getName());
			phone.setText(getItem(position).getPhone());
			address.setText(getItem(position).getProvince() + " " + getItem(position).getCity() + " " + getItem(position).getCountry()
					+ " " + getItem(position).getStreet());
			return view;
		}

	}

	// 获取地址的handler
	private Handler handler3 = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				// 成功后执行
				params.clear();
				List<Address> list = msg.getData().getParcelableArrayList("data");
				for (Address address : list) {
					params.add(address);
				}
				adapter.notifyDataSetChanged();
				break;
			case 0:
				// 代码没有问题的错误
				showTip(msg.getData().getString("error"));
				break;
			case -1:
				// 程序的错误
				showTip("数据获取失败");
				break;
			}
		}

	};

	// 获取地址的handler
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				// 成功后执行
				getAdressInfo();
				showTip("删除成功");
				break;
			case 0:
				// 代码没有问题的错误
				showTip(msg.getData().getString("error"));
				break;
			case -1:
				// 程序的错误
				showTip("数据获取失败");
				break;
			}
		}

	};

	private void showTip(String message) {
		Toast.makeText(this, message, 3000).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.address, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent(this, AddAddressActiivity.class);
		startActivity(intent);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		getAdressInfo();
	}
}
