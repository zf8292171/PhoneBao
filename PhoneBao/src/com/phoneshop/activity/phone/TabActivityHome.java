package com.phoneshop.activity.phone;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.allthelucky.common.view.AutoPlayManager;
import com.allthelucky.common.view.ImageIndicatorView;
import com.example.phonebao.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.phoneshop.activity.trade.PhoneShopSearchActivity;
import com.phoneshop.activity.trade.TabMainActivity;
import com.phoneshop.business.PhoneBusinessImp;
import com.phoneshop.javabean.Brand;

public class TabActivityHome extends Activity {

	private ImageIndicatorView imgHead;
	private BrandAdapter myBrandAdapter;

	List<Brand> str;

	// PullToRefreshScrollView mPullRefreshScrollView;
	// ScrollView mScrollView;

	private GridView myGrid;
	private EditText btnSearch;

	private List<Integer> adGoodsID;
	private PhoneBusinessImp business = new PhoneBusinessImp();
	private List<Bitmap> adGoodsPic = new ArrayList<Bitmap>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_main_home);

		// ��ʼ��
		init();

		// �ؼ��¼�
		viewDo();

	}

	private void init() {

		// ��ʼ����������
		str = new ArrayList<Brand>();
		str.add(new Brand(R.drawable.logo_apple, "iphone"));
		str.add(new Brand(R.drawable.logo_huawei, "��Ϊ"));
		str.add(new Brand(R.drawable.logo_lenovo, "����"));
		str.add(new Brand(R.drawable.logo_nokia, "ŵ����"));
		str.add(new Brand(R.drawable.logo_wp, "WindowPhone"));
		str.add(new Brand(R.drawable.logo_sansum, "����"));
		str.add(new Brand(R.drawable.logo_zte, "����"));
		str.add(new Brand(R.drawable.logo_dashen, "����"));
		str.add(new Brand(R.drawable.logo_htc, "HTC"));
		str.add(new Brand(R.drawable.logo_kupai, "����"));
		str.add(new Brand(R.drawable.logo_mi, "С��"));
		str.add(new Brand(R.drawable.logo_oppo, "OPPO"));
		str.add(new Brand(R.drawable.logo_vivo, "vivo"));
		// Ϊ���������������������
		myGrid = (GridView) findViewById(R.id.tab_home_grid);
		// ������
		myBrandAdapter = new BrandAdapter(this, R.layout.tab_gridview_items, str);
		myGrid.setAdapter(myBrandAdapter);

		btnSearch = (EditText) findViewById(R.id.btnSearch2);

		// ��ȡ�ؼ�
		imgHead = (ImageIndicatorView) findViewById(R.id.img_shop_head_pic);
		imgHead.setIndicateStyle(1);

	}

	private void viewDo() {

		// //����ˢ��Ч��
		// mPullRefreshScrollView = (PullToRefreshScrollView)
		// findViewById(R.id.pull_refresh_scrollview);
		// mPullRefreshScrollView.setOnRefreshListener(new
		// OnRefreshListener<ScrollView>() {
		//
		// @Override
		// public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
		// new GetDataTask().execute();
		// }
		// });
		// mScrollView = mPullRefreshScrollView.getRefreshableView();

		btnSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TabActivityHome.this, PhoneShopSearchActivity.class);
				startActivity(intent);
			}
		});

		myGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String brand = myBrandAdapter.getItem(position).getBrandName();
				Intent intent = new Intent(TabActivityHome.this, PhoneShopSearchActivity.class);
				intent.putExtra("brand", brand);
				startActivity(intent);
			}
		});

		// ���ͼƬ�ֲ�
		// 1.��ȡ������ȡ���ͼƬ��id
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					adGoodsID = business.getADGoodsID();
					Message msg = new Message();
					msg.what = 1;
					handler.sendMessage(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	// //����ˢ�´����¼�
	// private class GetDataTask extends AsyncTask<Void, Void, String[]> {
	//
	// @Override
	// protected String[] doInBackground(Void... params) {
	// try {
	// Thread.sleep(4000);
	// } catch (InterruptedException e) {
	// }
	// return null;
	// }
	//
	// @Override
	// protected void onPostExecute(String[] result) {
	// mPullRefreshScrollView.onRefreshComplete();
	// super.onPostExecute(result);
	// }
	// }

	// ͼƬ�ֲ���δ�������¼�
	public Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				// ��ȡÿ����Ʒid��Ӧ�Ĺ��
				new Thread(new Runnable() {

					@Override
					public void run() {
						if (adGoodsID != null) {
							for (int i = 0; i < adGoodsID.size(); i++) {
								try {
									business.getAdGoodsPic(adGoodsID.get(i), adGoodsPic);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							Message msg = new Message();
							msg.what = 2;
							handler.sendMessage(msg);
						}
					}
				}).start();
				break;
			case 2:
				try {
					// �����ֲ��ؼ�
					for (int i = 0; i < adGoodsPic.size(); i++) {
						if (adGoodsPic.get(i) != null) {
							ImageView imagview = new ImageView(TabActivityHome.this);
							imagview.setImageBitmap(adGoodsPic.get(i));
							imgHead.addViewItem(imagview);
						}
					}
					imgHead.show();

					AutoPlayManager autoBrocastManager = new AutoPlayManager(imgHead);
					autoBrocastManager.setBroadcastEnable(true);
					autoBrocastManager.setBroadCastTimes(20);// ѭ������
					autoBrocastManager.setBroadcastTimeIntevel(3 * 1000, 3 * 1000);// �״�����ʱ�估���
					autoBrocastManager.loop();
					imgHead.setOnItemClickListener(new ImageIndicatorView.OnItemClickListener() {

						@Override
						public void OnItemClick(View view, int position) {
							Intent intent = new Intent(TabActivityHome.this, TabMainActivity.class);
							intent.putExtra("ID", adGoodsID.get(position));
							startActivity(intent);
						}
					});
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		};
	};

	class BrandAdapter extends ArrayAdapter<Brand> {

		private View view;
		private TextView txtContent;
		private int resourceID;
		private List<Brand> resource;
		private ImageView img;

		public BrandAdapter(Context context, int textViewResourceId, List<Brand> objects) {
			super(context, textViewResourceId, objects);
			resourceID = textViewResourceId;
			resource = objects;

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			view = LayoutInflater.from(getContext()).inflate(resourceID, null);
			txtContent = (TextView) view.findViewById(R.id.tab_home_grid_content_txt);
			txtContent.setText(resource.get(position).getBrandName());
			img = (ImageView) view.findViewById(R.id.tab_home_grid_content_img);
			img.setImageResource(resource.get(position).getImgID());
			return view;
		}

	}

	private void showTip(String message) {
		Toast.makeText(this, message, 3000).show();
		;
	}

}
