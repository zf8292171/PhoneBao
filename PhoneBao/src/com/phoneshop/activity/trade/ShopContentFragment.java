package com.phoneshop.activity.trade;

import geniuz.myPathbutton.composerLayout;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.conn.ConnectTimeoutException;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterViewFlipper;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.phonebao.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.phoneshop.business.PhoneBusinessImp;
import com.phoneshop.business.PhoneBusinessInterface;
import com.phoneshop.javabean.ShopBriefInfo;

public class ShopContentFragment extends Fragment {

	private View view;

	private MyArrayAdapter adapter;

	private List<ShopBriefInfo> params;

	private Activity activity;

	private composerLayout clayout;

	private PullToRefreshListView mPullRefreshListView;

	private ProgressDialog prograssDialog;// 等待框

	private PhoneBusinessInterface business = new PhoneBusinessImp();

	private static final int MSG_BUSINESS_SUCESS = 1;
	private static final int MSG_DOWNLOAD_PIC = 2;

	private String MSG_DATA = "data";

	private int order = 0;// 排序字段 ，，0为默认排序，1为销量排序，2为价格低到高排序，3为价格高到低排序
	private int num = 5;// 请求数量，默认为5个 每次下拉刷新多请求五个

	private List<Thread> threadList = new ArrayList<Thread>();// 优化listview

	private List<Bitmap> bitMapPool = new ArrayList<Bitmap>();

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = activity;
	}

	//
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = null;
		view = inflater.inflate(R.layout.shop_search_content, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		init();// 初始化
		getShopInfo();// 获取商品
	}

	private void init() {

		params = new ArrayList<ShopBriefInfo>();

		// 设置适配器
		adapter = new MyArrayAdapter(activity, R.layout.listview_item_shop, params);

		mPullRefreshListView = (PullToRefreshListView) view.findViewById(R.id.pull_refresh_list);

		// Set a listener to be invoked when the list should be refreshed.
		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String label = DateUtils.formatDateTime(view.getContext(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME
						| DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

				// Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

				// Do work to refresh the list here.
				new GetDataTask().execute();
			}
		});

		mPullRefreshListView.setAdapter(adapter);

		// 引用控件
		composerLayout clayout = (composerLayout) view.findViewById(R.id.test);

		clayout.init(new int[] { R.drawable.composer_camera, R.drawable.composer_music, R.drawable.composer_place,
				R.drawable.composer_sleep }, R.drawable.composer_button, R.drawable.composer_icn_plus, composerLayout.LEFTBOTTOM, 180, 300);

		// 排序字段按钮
		OnClickListener clickit = new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (v.getId() == 100 + 0) {// 默认排序
					order = 3;
					getShopInfo();
				} else if (v.getId() == 100 + 1) {// 销量排序
					order = 2;
					getShopInfo();
				} else if (v.getId() == 100 + 2) {// 价格低到高排序
					order = 1;
					getShopInfo();
				} else if (v.getId() == 100 + 3) {// 价格高到低排序
					order = 0;
					getShopInfo();
				}
			}
		};
		clayout.setButtonsOnClickListener(clickit);

		// 关于listview的点击事件
		mPullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// 传值商品的id，打开信的活动
				int ID = adapter.getItem(--position).getId();
				Intent intent = new Intent(activity, TabMainActivity.class);
				intent.putExtra("ID", ID);
				startActivity(intent);
			}

		});

	}

	// 下拉刷新异步任务
	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(String[] result) {

			num = num + 5;
			getShopInfo();
			mPullRefreshListView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}

	// 获取商品信息
	private void getShopInfo() {
		clearThread();
		clearBitmap();

		// 弹出等待框
		if (prograssDialog == null) {

			prograssDialog = new ProgressDialog(activity);
			prograssDialog.setTitle("等待");
			prograssDialog.setMessage("加载中...");
			prograssDialog.setCancelable(false);
			prograssDialog.show();

		}

		// 开启线程，后台获取资源
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					// 执行
					business.tradeFilter(((PhoneShopSearchActivity) activity).getFilter(), order, num, params);
					// 成功后发送消息
					Message msg = new Message();
					msg.what = MSG_BUSINESS_SUCESS;
					handler.sendMessage(msg);
				} catch (SocketTimeoutException e) {
					// 出错后处理
					e.printStackTrace();
					Message msg = new Message();
					msg.what = -1;
					Bundle data = new Bundle();
					data.putSerializable("error", "服务器响应超时");
					msg.setData(data);
					handler.sendMessage(msg);
				} catch (ConnectTimeoutException e) {
					e.printStackTrace();
					Message msg = new Message();
					Bundle data = new Bundle();
					msg.what = -1;
					data.putString("error", "服务器正在维护");
					msg.setData(data);
					handler.sendMessage(msg);
				} catch (ConnectException e) {
					// 出错后处理
					e.printStackTrace();
					Message msg = new Message();
					msg.what = -1;
					Bundle data = new Bundle();
					data.putSerializable("error", "连接服务器超时");
					msg.setData(data);
					handler.sendMessage(msg);
				} catch (Exception e) {
					// 出错后处理
					e.printStackTrace();
					Message msg = new Message();
					msg.what = -1;
					Bundle data = new Bundle();
					data.putSerializable("error", "异常出错，请检查");
					msg.setData(data);
					handler.sendMessage(msg);
				}
			}
		}).start();
	}

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
			txtNum.setText(String.valueOf(getItem(position).getSellNum()) + "人付款");
			txtPrice.setText(String.valueOf(getItem(position).getPrice()));
			txtAddress.setText(getItem(position).getProvince()+" "+getItem(position).getCity());
			if (imgs.get(position) != null) {
				headpic.setBackgroundDrawable(new BitmapDrawable(imgs.get(position)));
			} else {
				// 线程加载图片
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						Looper.prepare();
						try {
							final Bitmap pic = business.getShopHeadPic(id);
							bitMapPool.add(pic);
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

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (prograssDialog != null) {
				prograssDialog.dismiss();
				prograssDialog = null;
			}

			// 对params进行跟新操作
			switch (msg.what) {
			case MSG_BUSINESS_SUCESS:
				adapter.notifyDataSetChanged();
				break;
			case -1:
				((PhoneShopSearchActivity) activity).showTip(msg.getData().getString("error"));
				break;
			}
		};
	};

	private void clearThread() {
		// 关闭过去的线程
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

	@Override
	public void onDestroy() {
		clearThread();
		clearBitmap();
		super.onDestroy();
	}

}