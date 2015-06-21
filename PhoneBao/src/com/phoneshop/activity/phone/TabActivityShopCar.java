package com.phoneshop.activity.phone;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.conn.ConnectTimeoutException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

import com.example.phonebao.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.phoneshop.activity.personer.LoginActivity;
import com.phoneshop.activity.trade.PhoneShopSearchActivity;
import com.phoneshop.activity.trade.TabMainActivity;
import com.phoneshop.business.CustomException;
import com.phoneshop.business.PhoneBusinessImp;
import com.phoneshop.customview.Title;
import com.phoneshop.javabean.Address;
import com.phoneshop.javabean.ShopCartInfo;
import com.phoneshop.utils.UserTag;

@SuppressWarnings("unused")
public class TabActivityShopCar extends Activity {
	private PullToRefreshListView mylist;
	private ShopAdapter adapter;
	private List<String> str_Resource;
	private Title title;
	private Button btnOK;

	private static final int FIRSTOPEN = 0;
	private static final int LOGINOPEN = 1;
	private static final int LOGOUTOPEN = 2;
	private int tag = 0;// ��½��־����Ŀ�ģ��Ż�����

	private PhoneBusinessImp business = new PhoneBusinessImp();

	private List<ShopCartInfo> params = new ArrayList<ShopCartInfo>();
	private Button btnDelete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// progressִ�в�������ΪҪ�漰�л����棬����ÿ��ѭ����Ҫ��ⶼҪ�����£�
		progress();
	}

	// ÿ���л�����ҳ�涼Ҫ�ظ����Ĳ���
	private void progress() {
		// �ж���Ҫ���ص�½��Ľ�������ѵ�½�Ľ���
		if (UserTag.userName == null) {
			// �Ż����ܲ���������ÿ�ζ�Ҫ����ҳ��
			if (tag == LOGOUTOPEN) {
				return;
			}
			// ���ص�½ǰ��ҳ��
			setContentView(R.layout.tab_main_shopcar_notlogin);
			final Button btnLogin = (Button) findViewById(R.id.tab_shopcar_btn_login);
			btnLogin.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent(TabActivityShopCar.this, LoginActivity.class);
					startActivity(intent);
				}
			});
			tag = LOGOUTOPEN;
			return;
		} else {
			if (tag == LOGINOPEN) {
				return;
			}
			// ���ص�½���ҳ��
			setContentView(R.layout.tab_main_shopcar);
			tag = LOGINOPEN;
		}

		// ��ʼ��������
		init();

		// �¼�����
		viewListener();

	}

	private void init() {
		// ����������
		adapter = new ShopAdapter(this, R.layout.tab_main_shopcar_listview_items, params);

		// ������
		title = (Title) findViewById(R.id.title);
		((TextView) title.findViewById(R.id.title_right)).setText("");
		((TextView) title.findViewById(R.id.title_center)).setText("�� �� ��");
		((TextView) title.findViewById(R.id.title_left)).setText("");
		((TextView) title.findViewById(R.id.title_left)).setOnClickListener(null);

		mylist = (PullToRefreshListView) findViewById(R.id.pulltorefresh_phone);

		// �ĵ���ť
		btnOK = (Button) findViewById(R.id.btnOK);

		// ȡ���ĵ���ť
		btnDelete = (Button) findViewById(R.id.btn_delete);
	}

	private void getShopCartInfo() {
		params.clear();
		adapter.getNameNum().clear();
		if (UserTag.userName != null) {
			// ��ȡ���ﳵ��Ϣ
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						business.getShopCartInfo(UserTag.userName, params);
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
	}

	private void viewListener() {
		// Ϊ���ﳵ��������������
		mylist.setAdapter(adapter);
		mylist.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME
						| DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

				new GetDataTask().execute();
			}
		});

		// ��Ʒ�ĵ���¼�
		mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				int goodsID = adapter.getItem(--position).getGoodsID();
				Intent intent = new Intent(TabActivityShopCar.this, TabMainActivity.class);
				intent.putExtra("ID", goodsID);
				startActivity(intent);
			}
		});

		// �µ��ļ����¼�
		btnOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ���û��ѡ����Ʒ������ʾ
				if (adapter.getNameNum().size() == 0) {
					showTip("��ѡ����Ʒ");
					return;
				} else {
					// �����ַѡ����Ŀ
					// ��ȡ��ַ��Ϣ
					if (UserTag.userName == null) {
						showTip("�����µ�½");
						return;
					}
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
			}
		});

		btnDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (adapter.getNameNum().size() == 0) {
					showTip("��ѡ����Ʒ");
					return;
				}
				if (UserTag.userName == null) {
					showTip("�����µ�½");
					return;
				}
				new AlertDialog.Builder(TabActivityShopCar.this).setTitle("ȷ��").setMessage("ȷ��������Ʒ�Ƴ����ﳵ��")
						.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {

								// ɾ�����ﳵ�¼�
								new Thread(new Runnable() {

									@Override
									public void run() {
										try {
											business.orderCancle(UserTag.userName, adapter.getNameNum());
											Message msg = new Message();
											msg.what = 1;
											handler2.sendMessage(msg);
										} catch (CustomException e) {
											e.printStackTrace();
											Message msg = new Message();
											msg.what = 0;
											Bundle data = new Bundle();
											data.putString("error", e.getMessage());
											msg.setData(data);
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
						}).setNegativeButton("ȡ��", null).show().setCancelable(true);

			}
		});
	}

	// ����ˢ����Ҫ���еĲ���
	private class GetDataTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			getShopCartInfo();
			mylist.onRefreshComplete();

		}
	}

	// �л����ûִ�еĲ���
	@Override
	protected void onResume() {
		super.onResume();
		progress();
	}

	private void showTip(String message) {
		Toast.makeText(this, message, 3000).show();
	}

	// ��ȡ���ﳵ���ݵ�handler
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				// �ɹ���ִ��
				adapter.notifyDataSetChanged();
				break;
			case -1:
				showTip("���ݻ�ȡʧ��");
				break;
			}
		};
	};

	// �ĵ���handler
	private Handler handler2 = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				// �ɹ���ִ��
				// ˢ�¹��ﳵ
				getShopCartInfo();
				break;
			case 0:
				// �Զ���Ĵ���
				showTip(msg.getData().getString("error"));
				break;
			case -1:
				// ����Ĵ���
				showTip("���ݻ�ȡʧ��");
				break;
			}
		};
	};

	// ��ȡ��ַ��handler
	private Handler handler3 = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				// �ɹ���ִ��
				AddressSelect(msg);
				break;
			case 0:
				// ����û������Ĵ���
				showTip(msg.getData().getString("error"));
				AddressSelect(msg);
				break;
			case -1:
				// ����Ĵ���
				showTip("���ݻ�ȡʧ��");
				break;
			}
		};
	};

	// ѡ���ַ
	private void AddressSelect(Message msg) {
		List<Address> list = msg.getData().getParcelableArrayList("data");
		if (list.size() == 0) {
			showTip("��ַΪ������ӵ�ַ���ٲ���");
			return;
		} else {
			final String[] address = new String[list.size()];
			final Map<String, Integer> map = new HashMap<String, Integer>();
			for (int i = 0; i < list.size(); i++) {
				address[i] = list.get(i).getCountry() + "   " + list.get(i).getStreet() + "   " + list.get(i).getName() + "   "
						+ list.get(i).getPhone();
				map.put(address[i], list.get(i).getId());
			}
			// ������ַѡ���
			new AlertDialog.Builder(TabActivityShopCar.this).setTitle("��ַѡ��")
					.setSingleChoiceItems(address, 0, new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							String str = address[which];
							// ��ȡѡ�е�ַ��id
							final int id = map.get(str);
							dialog.dismiss();
							new AlertDialog.Builder(TabActivityShopCar.this).setTitle("ȷ��").setMessage("�Ƿ��µ���")
									.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

										@Override
										public void onClick(DialogInterface dialog, int which) {
											// ִ���µ��¼�
											doOrder(id);

										}
									}).setNegativeButton("ȡ��", null).show().setCancelable(false);
						}
					}).show();
		}
	}

	// ��ַѡ�����ĵ�
	// �µ�����
	private void doOrder(final int addressID) {
		showTip("�µ��ɹ���ַ��" + addressID);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					business.order(UserTag.userName, adapter.getNameNum(), addressID);
					Message msg = new Message();
					msg.what = 1;
					handler2.sendMessage(msg);
				} catch (CustomException e) {
					e.printStackTrace();
					Message msg = new Message();
					msg.what = 0;
					Bundle data = new Bundle();
					data.putString("error", e.getMessage());
					msg.setData(data);
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

	private class ShopAdapter extends ArrayAdapter<ShopCartInfo> {

		private View view;
		private TextView txtContent;
		private int resourceID;
		// ���ݵļ�ֵ��
		private Map<Integer, Integer> NameNum = new HashMap<Integer, Integer>();// ��Ʒ����

		public ShopAdapter(Context context, int textViewResourceId, List<ShopCartInfo> objects) {
			super(context, textViewResourceId, objects);
			resourceID = textViewResourceId;
		}

		// ����������������Ϣ
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			view = LayoutInflater.from(getContext()).inflate(resourceID, null);
			// ��������
			final TextView title = (TextView) view.findViewById(R.id.txt1);
			final TextView num = (TextView) view.findViewById(R.id.txt2);
			final TextView price = (TextView) view.findViewById(R.id.txt3);
			final Button decreasse = (Button) view.findViewById(R.id.btn1);
			final Button add = (Button) view.findViewById(R.id.btn2);
			final CheckBox check = (CheckBox) view.findViewById(R.id.check);
			final ImageView headpic = (ImageView) view.findViewById(R.id.img);

			// Ϊ��������
			title.setText(getItem(position).getGoodsName());
			num.setText(String.valueOf(getItem(position).getShopCartNum()));
			price.setText(String.valueOf(getItem(position).getPrice()));

			final int id = getItem(position).getGoodsID();
			// ����ͼƬ
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						final Bitmap pic = business.getShopHeadPic(id);

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
				}
			}).start();

			final int local = position;
			final double prices = getItem(position).getPrice();
			add.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					int nums = Integer.valueOf(num.getText().toString().trim());
					nums++;
					num.setText(String.valueOf(nums));
					getItem(local).setShopCartNum(nums);
					if (check.isChecked()) {
						NameNum.put(getItem(local).getGoodsID(), getItem(local).getShopCartNum());
					}
					double temp = 0;
					temp = prices * nums;
					price.setText(String.valueOf(temp));
				}
			});
			decreasse.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					int nums = Integer.valueOf(num.getText().toString().trim());
					if (nums > 1) {
						nums--;
						num.setText(String.valueOf(nums));
						getItem(local).setShopCartNum(nums);
						if (check.isChecked()) {
							NameNum.put(getItem(local).getGoodsID(), getItem(local).getShopCartNum());
						}
						double temp = 0;
						temp = prices * nums;
						price.setText(String.valueOf(temp));
					}
				}
			});

			// ��ѡ���¼�
			check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked) {
						NameNum.put(getItem(local).getGoodsID(), getItem(local).getShopCartNum());
					} else {
						NameNum.remove(getItem(local).getGoodsID());
					}
				}
			});
			return view;
		}

		// ��ȡ��ֵ��
		public Map<Integer, Integer> getNameNum() {
			return NameNum;
		}

	}

}
