package com.phoneshop.activity.personer;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.conn.ConnectTimeoutException;

import com.example.phonebao.R;
import com.phoneshop.activity.phone.TabActivityShopCar;
import com.phoneshop.activity.trade.PhoneShopSearchActivity;
import com.phoneshop.activity.trade.TabMainActivity;
import com.phoneshop.business.PhoneBusinessImp;
import com.phoneshop.business.PhoneBusinessInterface;
import com.phoneshop.javabean.ShopBriefInfo;
import com.phoneshop.utils.UserTag;

import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CollectActivity extends Activity {

	private ListView listview;
	private List<Thread> threadList = new ArrayList<Thread>();// �Ż�listview

	private List<Bitmap> bitMapPool = new ArrayList<Bitmap>();

	private PhoneBusinessInterface business = new PhoneBusinessImp();
	private MyArrayAdapter adapter;
	private List<ShopBriefInfo> params;
	private ProgressDialog prograssDialog;// �ȴ���
	
	private TextView btnBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shop_collect);

		init();
		getShopInfo();
	}

	private void init() {
		params = new ArrayList<ShopBriefInfo>();

		listview = (ListView) findViewById(R.id.listview);
		// ����������
		adapter = new MyArrayAdapter(this, R.layout.listview_item_shop, params);

		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				int goodsID = adapter.getItem(position).getId();
				Intent intent = new Intent(CollectActivity.this, TabMainActivity.class);
				intent.putExtra("ID", goodsID);
				startActivity(intent);
			}
		});
		
		btnBack = (TextView) findViewById(R.id.back);
		
		btnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	// ��ȡ��Ʒ��Ϣ
	private void getShopInfo() {
		clearThread();
		clearBitmap();

		// �����ȴ���
		if (prograssDialog == null) {

			prograssDialog = new ProgressDialog(this);
			prograssDialog.setTitle("�ȴ�");
			prograssDialog.setMessage("������...");
			prograssDialog.setCancelable(false);
			prograssDialog.show();

		}

		// �����̣߳���̨��ȡ��Դ
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					// ִ��
					business.getGoodsCollectInfo(UserTag.userName, params);
					// �ɹ�������Ϣ
					Message msg = new Message();
					msg.what = 1;
					handler.sendMessage(msg);
				} catch (SocketTimeoutException e) {
					// �������
					e.printStackTrace();
					Message msg = new Message();
					msg.what = -1;
					Bundle data = new Bundle();
					data.putSerializable("error", "��������Ӧ��ʱ");
					msg.setData(data);
					handler.sendMessage(msg);
				} catch (ConnectTimeoutException e) {
					e.printStackTrace();
					Message msg = new Message();
					Bundle data = new Bundle();
					msg.what = -1;
					data.putString("error", "����������ά��");
					msg.setData(data);
					handler.sendMessage(msg);
				} catch (ConnectException e) {
					// �������
					e.printStackTrace();
					Message msg = new Message();
					msg.what = -1;
					Bundle data = new Bundle();
					data.putSerializable("error", "���ӷ�������ʱ");
					msg.setData(data);
					handler.sendMessage(msg);
				} catch (Exception e) {
					// �������
					e.printStackTrace();
					Message msg = new Message();
					msg.what = -1;
					Bundle data = new Bundle();
					data.putSerializable("error", "�쳣��������");
					msg.setData(data);
					handler.sendMessage(msg);
				}
			}
		}).start();
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (prograssDialog != null) {
				prograssDialog.dismiss();
				prograssDialog = null;
			}

			// ��params���и��²���
			switch (msg.what) {
			case 1:
				adapter.notifyDataSetChanged();
				break;
			case -1:
				showTip(msg.getData().getString("error"));
				break;
			}
		};
	};

	private class MyArrayAdapter extends ArrayAdapter<ShopBriefInfo> {

		private Context context;
		private int resource;
		private List<ShopBriefInfo> params;
		public Map<Integer, Bitmap> imgs = new HashMap<Integer, Bitmap>();

		public MyArrayAdapter(Context context, int textViewResourceId, List<ShopBriefInfo> objects) {
			super(context, textViewResourceId, objects);
			this.context = context;
			resource = textViewResourceId;
			params = objects;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			final int id = getItem(position).getId();
			View view = null;
			view = LayoutInflater.from(context).inflate(resource, parent, false);
			TextView txtGoodsName = (TextView) view.findViewById(R.id.txt_goods_name);
			TextView txtShopName = (TextView) view.findViewById(R.id.txt_shop_name);
			TextView txtNum = (TextView) view.findViewById(R.id.txtNum);
			TextView txtPrice = (TextView) view.findViewById(R.id.txt_price);
			TextView txtAddress = (TextView) view.findViewById(R.id.txt_address);
			final ImageView headpic = (ImageView) view.findViewById(R.id.shop_head_pic);
			txtGoodsName.setText(getItem(position).getGoodsName());
			txtShopName.setText(getItem(position).getShopName());
			txtNum.setText(String.valueOf(getItem(position).getSellNum()) + "�˸���");
			txtPrice.setText(String.valueOf(getItem(position).getPrice()));
			txtAddress.setText(getItem(position).getProvince() + " " + getItem(position).getCity());
			if (imgs.get(position) != null) {
				headpic.setBackgroundDrawable(new BitmapDrawable(imgs.get(position)));
			} else {
				// �̼߳���ͼƬ
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						Looper.prepare();
						try {
							final Bitmap pic = business.getShopHeadPic(id);
							bitMapPool.add(pic);
							imgs.put(position, pic);
							// ui�̸߳���ͼƬ

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

	private void clearThread() {
		// �رչ�ȥ���߳�
		for (Thread thread : threadList) {
			if (thread.isAlive()) {
				thread.interrupt();
			}
		}
		threadList.clear();
	}

	private void clearBitmap() {
		for (Bitmap bitmap : bitMapPool) {
			if (bitmap != null) {
				if (!bitmap.isRecycled()) {
					bitmap.recycle();
				}
			}
		}
		adapter.imgs.clear();
		bitMapPool.clear();
	}

	private void showTip(String message) {
		Toast.makeText(this, message, 3000).show();
	}

	@Override
	public void onDestroy() {
		clearThread();
		clearBitmap();
		super.onDestroy();
	}

}
