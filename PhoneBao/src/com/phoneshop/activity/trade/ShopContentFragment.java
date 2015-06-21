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

	private ProgressDialog prograssDialog;// �ȴ���

	private PhoneBusinessInterface business = new PhoneBusinessImp();

	private static final int MSG_BUSINESS_SUCESS = 1;
	private static final int MSG_DOWNLOAD_PIC = 2;

	private String MSG_DATA = "data";

	private int order = 0;// �����ֶ� ����0ΪĬ������1Ϊ��������2Ϊ�۸�͵�������3Ϊ�۸�ߵ�������
	private int num = 5;// ����������Ĭ��Ϊ5�� ÿ������ˢ�¶��������

	private List<Thread> threadList = new ArrayList<Thread>();// �Ż�listview

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
		init();// ��ʼ��
		getShopInfo();// ��ȡ��Ʒ
	}

	private void init() {

		params = new ArrayList<ShopBriefInfo>();

		// ����������
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

		// ���ÿؼ�
		composerLayout clayout = (composerLayout) view.findViewById(R.id.test);

		clayout.init(new int[] { R.drawable.composer_camera, R.drawable.composer_music, R.drawable.composer_place,
				R.drawable.composer_sleep }, R.drawable.composer_button, R.drawable.composer_icn_plus, composerLayout.LEFTBOTTOM, 180, 300);

		// �����ֶΰ�ť
		OnClickListener clickit = new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (v.getId() == 100 + 0) {// Ĭ������
					order = 3;
					getShopInfo();
				} else if (v.getId() == 100 + 1) {// ��������
					order = 2;
					getShopInfo();
				} else if (v.getId() == 100 + 2) {// �۸�͵�������
					order = 1;
					getShopInfo();
				} else if (v.getId() == 100 + 3) {// �۸�ߵ�������
					order = 0;
					getShopInfo();
				}
			}
		};
		clayout.setButtonsOnClickListener(clickit);

		// ����listview�ĵ���¼�
		mPullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// ��ֵ��Ʒ��id�����ŵĻ
				int ID = adapter.getItem(--position).getId();
				Intent intent = new Intent(activity, TabMainActivity.class);
				intent.putExtra("ID", ID);
				startActivity(intent);
			}

		});

	}

	// ����ˢ���첽����
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

	// ��ȡ��Ʒ��Ϣ
	private void getShopInfo() {
		clearThread();
		clearBitmap();

		// �����ȴ���
		if (prograssDialog == null) {

			prograssDialog = new ProgressDialog(activity);
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
					business.tradeFilter(((PhoneShopSearchActivity) activity).getFilter(), order, num, params);
					// �ɹ�������Ϣ
					Message msg = new Message();
					msg.what = MSG_BUSINESS_SUCESS;
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
			txtAddress.setText(getItem(position).getProvince()+" "+getItem(position).getCity());
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

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (prograssDialog != null) {
				prograssDialog.dismiss();
				prograssDialog = null;
			}

			// ��params���и��²���
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

	@Override
	public void onDestroy() {
		clearThread();
		clearBitmap();
		super.onDestroy();
	}

}