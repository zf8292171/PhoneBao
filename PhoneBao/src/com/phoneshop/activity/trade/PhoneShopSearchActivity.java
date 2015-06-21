package com.phoneshop.activity.trade;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phonebao.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.phoneshop.database.sqlite.MySqliteOpenHelper;
import com.phoneshop.javabean.ShopFilter;

public class PhoneShopSearchActivity extends Activity implements HistoryFragment.Commucation {

	private SlidingMenu menu;

	private ListView menuListView;

	private List<String> strAttribute;// �ֻ��������ַ�����

	private Button btnRecover;// ���ð�ť

	private String[] strBrand;
	int positionBrand = 0;
	private String[] strCPU;
	int positionCPU = 0;
	private String[] strRAM;
	int positionRAM = 0;
	private String[] strROM;
	int positionROM = 0;
	private String[] strPX;
	int positionPX = 0;
	private String[] strColor;
	int positionColor = 0;
	private String[] strWeb;
	int positionWeb = 0;
	private String[] strPrice;
	int positionPrice = 0;
	private ShopFilter filter = new ShopFilter();

	private MyMenuArrayAdapter menuArrayAdapter;

	private TextView btnBack, btnSearch;

	private EditText etInput;

	private boolean tagFragment = false;// �Ż�UI���ܣ��жϵ�ǰҳ������һ��ҳ��

	private static final String FRAGMENT_TAG_HISTORY = "history";
	private static final String FRAGMENT_TAG_CONTENT = "content";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.shop_search_layout);

		// ��ʼ��
		init();

		// �¼�����
		listener();

	}

	private void init() {
		// Ϊ�˵�����Ĭ��ֵ
		menu = new SlidingMenu(this);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		menu.setMenu(R.layout.shop_search_menu);
		menu.setBehindWidthRes(R.dimen.sliding_width);

		// ��ȡlistview
		menuListView = (ListView) menu.findViewById(R.id.listview1);
		btnRecover = (Button) menu.findViewById(R.id.btn_recover);

		attribute();// ��menu���������Գ�ʼ��

		menuArrayAdapter = new MyMenuArrayAdapter(this, R.layout.listview_items1, strAttribute);

		menuListView.setAdapter(menuArrayAdapter);

		// ������Ƭ
		getFragmentManager().beginTransaction().replace(R.id.framelayout1, new HistoryFragment(), "FRAGMENT_TAG_HISTORY").commit();

		btnBack = (TextView) findViewById(R.id.btn_back);

		btnSearch = (TextView) findViewById(R.id.btn_search);

		etInput = (EditText) findViewById(R.id.et_content);

	}

	// �ֻ�����ֵ�ĳ�ʼ��
	private void attribute() {
		strAttribute = Arrays.asList(getResources().getStringArray(R.array.attribute));
		strBrand = getResources().getStringArray(R.array.brand);
		strColor = getResources().getStringArray(R.array.Color);
		strCPU = getResources().getStringArray(R.array.CPU);
		strPrice = getResources().getStringArray(R.array.Price);
		strPX = getResources().getStringArray(R.array.PX);
		strRAM = getResources().getStringArray(R.array.RAM);
		strROM = getResources().getStringArray(R.array.ROM);
		strWeb = getResources().getStringArray(R.array.Web);
	}

	private void listener() {
		// ���ذ�ť�˳�����
		btnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PhoneShopSearchActivity.this.finish();
			}
		});

		// ������ť
		btnSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String content = etInput.getText().toString().trim();
				if (!content.equals("")) {
					// ���ؼ��ִ������ݿ�
					databaseInsertHistoryWord(content);
				}
				// ��ȡɸѡ
				filter.setContent(content);
				// �л�����
				moveToContent();
			}
		});

		// ����ı����е�����ҳ��
		etInput.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (tagFragment) {
					moveToHistory();
				}
			}
		});

		// �˵����������
		menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String filter = menuArrayAdapter.getItem(position);
				menuListenerDo(filter);
			}
		});

		Intent intent = getIntent();
		String intentBrand = intent.getStringExtra("brand");
		if (intentBrand == null) {
		} else {
			filter.setBrand(intentBrand);
			moveToContent();
		}

		// ����ɸѡ��ť
		btnRecover.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				filter = new ShopFilter();
				positionBrand = 0;
				positionCPU = 0;
				positionRAM = 0;
				positionROM = 0;
				positionPX = 0;
				positionColor = 0;
				positionWeb = 0;
				positionPrice = 0;
				Toast.makeText(PhoneShopSearchActivity.this, "���óɹ�", 3000).show();
			}
		});
	}

	// ������ʷ��¼
	private void databaseInsertHistoryWord(String word) {
		SQLiteOpenHelper dbhelper = null;
		SQLiteDatabase db = null;
		try {
			dbhelper = new MySqliteOpenHelper(this, "phoneshop.db", null, 1);
			db = dbhelper.getWritableDatabase();
			db.execSQL("insert into history(word) values(?)", new String[] { word });
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (db != null) {
				db.close();
			}
			if (dbhelper != null) {
				dbhelper.close();
			}
		}
	}

	// �໬�˵���listview
	private class MyMenuArrayAdapter extends ArrayAdapter<String> {

		private int resource;
		private Context mContext;
		private List<String> params;

		public MyMenuArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
			super(context, textViewResourceId, objects);
			mContext = context;
			resource = textViewResourceId;
			params = objects;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			view = LayoutInflater.from(mContext).inflate(resource, null, false);
			TextView textview = (TextView) view.findViewById(R.id.text1);
			textview.setText(params.get(position));
			return view;
		}

	}

	// menu�¼��ĵ������
	private void menuListenerDo(String attribute) {
		if (attribute.equals("Ʒ��")) {
			new AlertDialog.Builder(this).setTitle("Ʒ��")
					.setSingleChoiceItems(strBrand, positionBrand, new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// ����ֵ�����ϲ˵�
							positionBrand = which;
							filter.setBrand(strBrand[which]);
							menu.toggle();
							filterToContent();
							dialog.dismiss();
						}
					}).show();
		} else if (attribute.equals("CPU����")) {
			new AlertDialog.Builder(this).setTitle("CPU").setSingleChoiceItems(strCPU, positionCPU, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// ����ֵ�����ϲ˵�
					positionCPU = which;
					filter.setCpu(strCPU[which]);
					menu.toggle();
					filterToContent();
					dialog.dismiss();
				}
			}).show();
		} else if (attribute.equals("RAM")) {
			new AlertDialog.Builder(this).setTitle("RAM").setSingleChoiceItems(strRAM, positionRAM, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// ����ֵ�����ϲ˵�
					positionRAM = which;
					filter.setRam(strRAM[which]);
					menu.toggle();
					filterToContent();
					dialog.dismiss();
				}
			}).show();
		} else if (attribute.equals("ROM")) {
			new AlertDialog.Builder(this).setTitle("ROM").setSingleChoiceItems(strROM, positionROM, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// ����ֵ�����ϲ˵�
					positionROM = which;
					filter.setRom(strROM[which]);
					menu.toggle();
					filterToContent();
					dialog.dismiss();
				}
			}).show();
		} else if (attribute.equals("����")) {
			new AlertDialog.Builder(this).setTitle("PX").setSingleChoiceItems(strPX, positionPX, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// ����ֵ�����ϲ˵�
					positionPX = which;
					filter.setPx(strPX[which]);
					menu.toggle();
					filterToContent();
					dialog.dismiss();
				}
			}).show();
		} else if (attribute.equals("��ɫ")) {
			new AlertDialog.Builder(this).setTitle("Color")
					.setSingleChoiceItems(strColor, positionColor, new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// ����ֵ�����ϲ˵�
							positionColor = which;
							filter.setColor(strColor[which]);
							menu.toggle();
							filterToContent();
							dialog.dismiss();
						}
					}).show();
		} else if (attribute.equals("����֧��")) {
			new AlertDialog.Builder(this).setTitle("Web").setSingleChoiceItems(strWeb, positionWeb, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// ����ֵ�����ϲ˵�
					positionWeb = which;
					filter.setWeb(strWeb[which]);
					menu.toggle();
					filterToContent();
					dialog.dismiss();
				}
			}).show();
		} else if (attribute.equals("�۸�")) {
			new AlertDialog.Builder(this).setTitle("Price")
					.setSingleChoiceItems(strPrice, positionPrice, new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// ����ֵ�����ϲ˵�
							positionPrice = which;
							filter.setPrice(strPrice[which]);
							menu.toggle();
							filterToContent();
							dialog.dismiss();
						}
					}).show();
		}
	}

	// �õ�filter
	public ShopFilter getFilter() {
		return filter;
	}

	// �˵���������ͼ�Ĳ���
	private void filterToContent() {
		moveToContent();
		etInput.setText(filter.getContent());
	}

	public void showTip(String message) {
		Toast.makeText(this, message, 3000).show();
	}

	// history�ĵ���¼������Ҫ������
	@Override
	public void historyListViewOnClick(String word) {
		etInput.setText(word);
		filter.setContent(word);
		moveToContent();
	}

	// ��ת��history����Ĳ���
	private void moveToHistory() {
		etInput.setFocusable(true);
		etInput.setFocusableInTouchMode(true);
		tagFragment = false;
		getFragmentManager().beginTransaction().replace(R.id.framelayout1, new HistoryFragment(), FRAGMENT_TAG_HISTORY).commit();
	}

	// ��ת��content����Ĳ���
	private void moveToContent() {
		getFragmentManager().beginTransaction().replace(R.id.framelayout1, new ShopContentFragment(), FRAGMENT_TAG_CONTENT).commit();
		tagFragment = true;
		etInput.setFocusable(false);
		etInput.setFocusableInTouchMode(false);
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(etInput.getWindowToken(), 0);
	}

}
