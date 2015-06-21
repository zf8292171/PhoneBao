package com.phoneshop.activity.trade;

import java.util.ArrayList;
import java.util.List;

import com.example.phonebao.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.phoneshop.business.PhoneBusinessImp;
import com.phoneshop.javabean.Comments;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TabShopCommentsActivity extends Activity {

	private int ID;
	private List<Comments> params = new ArrayList<Comments>();
	private MyArrayAdapter adapter;
	private PullToRefreshListView mPullRefreshListView;
	private int num = 5;
	private PhoneBusinessImp business = new PhoneBusinessImp();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shop_tab_comments);

		// ��ʼ��
		init();

	}

	private void init() {
		// ��ȡ�б�ؼ�
		mPullRefreshListView = (PullToRefreshListView) TabShopCommentsActivity.this.findViewById(R.id.pull_refresh_list);

		// ��ȡ��Ʒid
		ID = getIntent().getIntExtra("ID", -1);
		if (ID == -1) {
			showTip("��ȡID��������ϵ�ͷ��������");
			finish();
			return;
		}

		adapter = new MyArrayAdapter(this, R.layout.listview_comments, params);

		// ��ȡ��������
		getData();

		//����ˢ�µ���������
		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String label = DateUtils.formatDateTime(TabShopCommentsActivity.this, System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

				// Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

				// Do work to refresh the list here.
				new GetDataTask().execute();
			}
		});

		mPullRefreshListView.setAdapter(adapter);

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
			getData();
			mPullRefreshListView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}

	private void getData() {
		params.clear();
		// ��ȡ��������
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					business.getShopComments(ID, params,num);
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

	private class MyArrayAdapter extends ArrayAdapter<Comments> {

		Context context;
		int textViewResourceId;
		List<Comments> params;

		public MyArrayAdapter(Context context, int textViewResourceId, List<Comments> objects) {
			super(context, textViewResourceId, objects);
			this.context = context;
			this.textViewResourceId = textViewResourceId;
			this.params = objects;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = LayoutInflater.from(context).inflate(textViewResourceId, parent, false);
			TextView txt1 = (TextView) view.findViewById(R.id.txt1);// �˺�
			TextView txt2 = (TextView) view.findViewById(R.id.txt2);// ����
			TextView txt3 = (TextView) view.findViewById(R.id.txt3);// ����
			txt1.setText(getItem(position).getUser());
			txt2.setText(getItem(position).getContent());
			txt3.setText(getItem(position).getTime());
			return view;
		}

	}

	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				//������ɺ�
				
				adapter.notifyDataSetChanged();
				break;
			case -1:
				showTip("��ȡ�����쳣");
				break;
			}
		};
	};
	
	private void showTip(String message) {
		Toast.makeText(this, message, 3000).show();
	}

}
