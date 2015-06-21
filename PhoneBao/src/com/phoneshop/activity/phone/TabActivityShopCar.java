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
	private int tag = 0;// 登陆标志，，目的：优化性能

	private PhoneBusinessImp business = new PhoneBusinessImp();

	private List<ShopCartInfo> params = new ArrayList<ShopCartInfo>();
	private Button btnDelete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// progress执行操作（因为要涉及切换界面，所以每次循环都要检测都要做的事）
		progress();
	}

	// 每次切换到该页面都要重复做的操作
	private void progress() {
		// 判断需要加载登陆后的界面和提醒登陆的界面
		if (UserTag.userName == null) {
			// 优化性能操作，避免每次都要加载页面
			if (tag == LOGOUTOPEN) {
				return;
			}
			// 加载登陆前的页面
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
			// 加载登陆后的页面
			setContentView(R.layout.tab_main_shopcar);
			tag = LOGINOPEN;
		}

		// 初始化数数据
		init();

		// 事件监听
		viewListener();

	}

	private void init() {
		// 设置适配器
		adapter = new ShopAdapter(this, R.layout.tab_main_shopcar_listview_items, params);

		// 标题栏
		title = (Title) findViewById(R.id.title);
		((TextView) title.findViewById(R.id.title_right)).setText("");
		((TextView) title.findViewById(R.id.title_center)).setText("购 物 车");
		((TextView) title.findViewById(R.id.title_left)).setText("");
		((TextView) title.findViewById(R.id.title_left)).setOnClickListener(null);

		mylist = (PullToRefreshListView) findViewById(R.id.pulltorefresh_phone);

		// 拍单按钮
		btnOK = (Button) findViewById(R.id.btnOK);

		// 取消拍单按钮
		btnDelete = (Button) findViewById(R.id.btn_delete);
	}

	private void getShopCartInfo() {
		params.clear();
		adapter.getNameNum().clear();
		if (UserTag.userName != null) {
			// 获取购物车信息
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
		// 为购物车内容设置适配器
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

		// 商品的点击事件
		mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				int goodsID = adapter.getItem(--position).getGoodsID();
				Intent intent = new Intent(TabActivityShopCar.this, TabMainActivity.class);
				intent.putExtra("ID", goodsID);
				startActivity(intent);
			}
		});

		// 下单的监听事件
		btnOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 如果没有选择商品，则提示
				if (adapter.getNameNum().size() == 0) {
					showTip("请选择商品");
					return;
				} else {
					// 进入地址选择项目
					// 获取地址信息
					if (UserTag.userName == null) {
						showTip("请重新登陆");
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
					showTip("请选择商品");
					return;
				}
				if (UserTag.userName == null) {
					showTip("请重新登陆");
					return;
				}
				new AlertDialog.Builder(TabActivityShopCar.this).setTitle("确定").setMessage("确定将此商品移出购物车？")
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {

								// 删除购物车事件
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
						}).setNegativeButton("取消", null).show().setCancelable(true);

			}
		});
	}

	// 下拉刷新需要进行的操作
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

	// 切换到该活动执行的操作
	@Override
	protected void onResume() {
		super.onResume();
		progress();
	}

	private void showTip(String message) {
		Toast.makeText(this, message, 3000).show();
	}

	// 获取购物车数据的handler
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				// 成功后执行
				adapter.notifyDataSetChanged();
				break;
			case -1:
				showTip("数据获取失败");
				break;
			}
		};
	};

	// 拍单的handler
	private Handler handler2 = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				// 成功后执行
				// 刷新购物车
				getShopCartInfo();
				break;
			case 0:
				// 自定义的错误
				showTip(msg.getData().getString("error"));
				break;
			case -1:
				// 程序的错误
				showTip("数据获取失败");
				break;
			}
		};
	};

	// 获取地址的handler
	private Handler handler3 = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				// 成功后执行
				AddressSelect(msg);
				break;
			case 0:
				// 代码没有问题的错误
				showTip(msg.getData().getString("error"));
				AddressSelect(msg);
				break;
			case -1:
				// 程序的错误
				showTip("数据获取失败");
				break;
			}
		};
	};

	// 选择地址
	private void AddressSelect(Message msg) {
		List<Address> list = msg.getData().getParcelableArrayList("data");
		if (list.size() == 0) {
			showTip("地址为空请添加地址后再操作");
			return;
		} else {
			final String[] address = new String[list.size()];
			final Map<String, Integer> map = new HashMap<String, Integer>();
			for (int i = 0; i < list.size(); i++) {
				address[i] = list.get(i).getCountry() + "   " + list.get(i).getStreet() + "   " + list.get(i).getName() + "   "
						+ list.get(i).getPhone();
				map.put(address[i], list.get(i).getId());
			}
			// 弹出地址选择框
			new AlertDialog.Builder(TabActivityShopCar.this).setTitle("地址选择")
					.setSingleChoiceItems(address, 0, new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							String str = address[which];
							// 获取选中地址的id
							final int id = map.get(str);
							dialog.dismiss();
							new AlertDialog.Builder(TabActivityShopCar.this).setTitle("确认").setMessage("是否下单？")
									.setPositiveButton("确定", new DialogInterface.OnClickListener() {

										@Override
										public void onClick(DialogInterface dialog, int which) {
											// 执行下单事件
											doOrder(id);

										}
									}).setNegativeButton("取消", null).show().setCancelable(false);
						}
					}).show();
		}
	}

	// 地址选择后的拍单
	// 下单操作
	private void doOrder(final int addressID) {
		showTip("下单成功地址号" + addressID);
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
		// 数据的键值对
		private Map<Integer, Integer> NameNum = new HashMap<Integer, Integer>();// 商品数量

		public ShopAdapter(Context context, int textViewResourceId, List<ShopCartInfo> objects) {
			super(context, textViewResourceId, objects);
			resourceID = textViewResourceId;
		}

		// 加载适配器数据信息
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			view = LayoutInflater.from(getContext()).inflate(resourceID, null);
			// 加载数据
			final TextView title = (TextView) view.findViewById(R.id.txt1);
			final TextView num = (TextView) view.findViewById(R.id.txt2);
			final TextView price = (TextView) view.findViewById(R.id.txt3);
			final Button decreasse = (Button) view.findViewById(R.id.btn1);
			final Button add = (Button) view.findViewById(R.id.btn2);
			final CheckBox check = (CheckBox) view.findViewById(R.id.check);
			final ImageView headpic = (ImageView) view.findViewById(R.id.img);

			// 为参数设置
			title.setText(getItem(position).getGoodsName());
			num.setText(String.valueOf(getItem(position).getShopCartNum()));
			price.setText(String.valueOf(getItem(position).getPrice()));

			final int id = getItem(position).getGoodsID();
			// 加载图片
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						final Bitmap pic = business.getShopHeadPic(id);

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

			// 勾选的事件
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

		// 获取键值对
		public Map<Integer, Integer> getNameNum() {
			return NameNum;
		}

	}

}
