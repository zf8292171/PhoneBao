package com.phoneshop.activity.trade;

import java.util.ArrayList;
import java.util.List;

import com.example.phonebao.R;
import com.phoneshop.database.sqlite.MySqliteOpenHelper;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HistoryFragment extends Fragment {

	//与Activity取得联系的接口
	public interface Commucation {
		public void historyListViewOnClick(String word);
	}
	
	private View view;

	private MyArrayAdapter adapter;

	private List<String> params;

	private ListView listView;

	private Activity activity;
	
	private Commucation comm;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = activity;
		//得到Activity的联系方便调用
		comm = (Commucation)activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.shop_search_history, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		init();// 初始化

		// 设置事件监听
		listener();
	}

	private void init() {
		listView = (ListView) view.findViewById(R.id.listview1);

		params = new ArrayList<String>();

		adapter = new MyArrayAdapter(activity, R.layout.listview_items1, params);

		listView.setAdapter(adapter);
		databaseReadHistoryWord();// 读取历史记录词汇存入集合params

	}

	// 事件监听
	private void listener() {
		// listview事件监听
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String word = adapter.getItem(position);
				comm.historyListViewOnClick(word);
			}
		});
	}

	// 读取历史记录
	private void databaseReadHistoryWord() {
		SQLiteOpenHelper dbhelper = null;
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			dbhelper = new MySqliteOpenHelper(activity, "phoneshop.db", null, 1);
			db = dbhelper.getWritableDatabase();
			cursor = db.rawQuery("select * from history order by id desc limit 10", null);
			while (cursor.moveToNext()) {
				String str = cursor.getString(1);
				params.add(str);
			}
			adapter.notifyDataSetChanged();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
			if (dbhelper != null) {
				dbhelper.close();
			}
		}
	}

	// 历史记录的listview
	private class MyArrayAdapter extends ArrayAdapter<String> {

		private Context context;
		private int resource;
		private List<String> params;

		public MyArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
			super(context, textViewResourceId, objects);
			this.context = context;
			resource = textViewResourceId;
			params = objects;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			view = LayoutInflater.from(context).inflate(resource, parent, false);
			TextView text1 = (TextView) view.findViewById(R.id.text1);
			text1.setText(params.get(position));
			return view;
		}

	}

}
