package com.phoneshop.activity.personer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.phonebao.R;
import com.phoneshop.activity.trade.TabMainActivity;
import com.phoneshop.business.PhoneBusinessImp;
import com.phoneshop.business.UserBusinessImp;
import com.phoneshop.javabean.Order;
import com.phoneshop.utils.UserTag;

import android.R.array;
import android.app.Activity;
import android.app.PendingIntent.OnFinished;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends Activity {

	private ListView listview;
	private List<Order> params = new ArrayList<Order>();
	private MyArrayAdapter adapter;
	private UserBusinessImp business = new UserBusinessImp();
	private PhoneBusinessImp business2 = new PhoneBusinessImp();
	private ProgressDialog prograssDialog;
	private List<Bitmap> bitmapPooling = new ArrayList<Bitmap>();
	private List<Thread> threadList = new ArrayList<Thread>();// 优化listview
	private TextView btnBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.order);

		// 初始化
		init();

		getData();

	}

	private void init() {
		listview = (ListView) findViewById(R.id.listview);
		adapter = new MyArrayAdapter(this, R.layout.listview_order, params);
		listview.setAdapter(adapter);
		btnBack = (TextView) findViewById(R.id.title_left);
		btnBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				int goodsID = adapter.getItem(position).getGoodsID();
				Intent intent = new Intent(OrderActivity.this, TabMainActivity.class);
				intent.putExtra("ID", goodsID);
				startActivity(intent);
			}
		});
	}

	private void getData() {
		clearBitmap();
		// 弹出等待框
		if (prograssDialog == null) {

			prograssDialog = new ProgressDialog(this);
			prograssDialog.setTitle("等待");
			prograssDialog.setMessage("登陆中...");
			prograssDialog.setCancelable(false);
			prograssDialog.show();

		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					business.getOrderInfo(UserTag.userName, params);
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

	private class MyArrayAdapter extends ArrayAdapter<Order> {

		int textViewResourceId;
		Context context;
		public Map<Integer, Bitmap> imgs = new HashMap<Integer, Bitmap>();

		public MyArrayAdapter(Context context, int textViewResourceId, List<Order> objects) {
			super(context, textViewResourceId, objects);
			this.textViewResourceId = textViewResourceId;
			this.context = context;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			View view = LayoutInflater.from(context).inflate(textViewResourceId, parent, false);
			final int id = getItem(position).getGoodsID();
			TextView txtShopName = (TextView) view.findViewById(R.id.txt_shop_name);
			TextView txtDealState = (TextView) view.findViewById(R.id.txt_deal_state);
			TextView txtGoodsName = (TextView) view.findViewById(R.id.txt_goods_name);
			TextView txtGoodsPrice = (TextView) view.findViewById(R.id.txt_goods_price);
			TextView txtNum = (TextView) view.findViewById(R.id.txt_num);
			TextView txtToatle = (TextView) view.findViewById(R.id.txt_totle_price);
			TextView txtAddress = (TextView) view.findViewById(R.id.txt_address);
			txtShopName.setText(getItem(position).getShopName());
			txtDealState.setText(getItem(position).getOrderState());
			txtGoodsName.setText(getItem(position).getGoodsName());
			txtGoodsPrice.setText("¥" + String.valueOf(getItem(position).getTotlePrice() / getItem(position).getGoodsNum()));
			txtNum.setText("x" + String.valueOf(getItem(position).getGoodsNum()));
			txtToatle.setText("¥" + String.valueOf(getItem(position).getTotlePrice()));
			txtAddress.setText(getItem(position).getProvince() + " " + getItem(position).getCity() + " " + getItem(position).getCity()
					+ " " + getItem(position).getStreet() + " " + getItem(position).getName() + " " + getItem(position).getPhone());
			final ImageView headpic = (ImageView) view.findViewById(R.id.img_headpic);
			if (imgs.get(position) != null) {
				headpic.setBackgroundDrawable(new BitmapDrawable(imgs.get(position)));
			} else {
				// 线程加载图片
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						Looper.prepare();
						try {
							final Bitmap pic = business2.getShopHeadPic(id);
							if (pic != null) {
								bitmapPooling.add(pic);
							}
							imgs.put(position, pic);
							// ui线程更新图片

							headpic.post(new Runnable() {

								@Override
								public void run() {
									headpic.setBackgroundDrawable(new BitmapDrawable(pic));
									Thread.interrupted();
								}

							});
						} catch (Exception e) {
							e.printStackTrace();
						}
						Thread.interrupted();
					}
				});
				thread.start();
				threadList.add(thread);
			}

			return view;
		}

	}

	private void showTip(String message) {
		Toast.makeText(this, message, 3000).show();
	}

	// 获取订单的事件处理
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				// 数据成功后
				adapter.notifyDataSetChanged();
				if (prograssDialog != null) {
					prograssDialog.dismiss();
					prograssDialog = null;
				}
				break;

			case -1:
				showTip("数据加载失败");
				finish();
				break;
			}
		};
	};

	private void clearBitmap() {
		for (Bitmap bitmap : bitmapPooling) {
			if (bitmap != null) {
				if (!bitmap.isRecycled()) {
					bitmap.recycle();
				}
			}
		}
		adapter.imgs.clear();
		bitmapPooling.clear();
	}

	@Override
	public void onDestroy() {
		clearBitmap();
		super.onDestroy();
	}

	@Override
	public void finish() {
		clearBitmap();
		if (prograssDialog != null) {
			prograssDialog.dismiss();
			prograssDialog = null;
		}
		super.finish();
	}

}
