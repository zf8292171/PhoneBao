package com.phoneshop.activity.trade;

import java.util.ArrayList;
import java.util.List;

import com.allthelucky.common.view.ImageIndicatorView;
import com.allthelucky.common.view.network.NetworkImageIndicatorView;
import com.example.phonebao.R;
import com.phoneshop.activity.personer.LoginActivity;
import com.phoneshop.business.PhoneBusinessImp;
import com.phoneshop.javabean.ShopInfo;
import com.phoneshop.utils.UserTag;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TabShopInfoActiivty extends Activity {

	private ImageIndicatorView imgHead;
	private ImageView[] image = new ImageView[5];
	private int ID;
	private PhoneBusinessImp business = new PhoneBusinessImp();
	private ShopInfo shopInfo;
	private TextView txtBrand, txtSystem, txtCpu, txtSize, txtRam, txtRom, txtResolution, txtPreCamera, txtColor, txtWeb, txtPrice,
			goodsTitle, txtSellNum;
	private CheckBox receive;
	private boolean receiveTag = false;// 收藏标志位

	private List<Bitmap> bitmapPools = new ArrayList<Bitmap>();

	// 线程回收池
	private List<Thread> threadPooling = new ArrayList<Thread>();

	// 加入等待窗口
	private ProgressDialog prograssDialog;

	private boolean isCollect = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shop_tab_trade_info);

		// 初始化
		init();

	}

	private void init() {

		// 弹出等待框
		if (prograssDialog == null) {

			prograssDialog = new ProgressDialog(TabShopInfoActiivty.this);
			prograssDialog.setTitle("等待");
			prograssDialog.setMessage("数据获取中...");
			prograssDialog.setCancelable(false);
			prograssDialog.show();

		}

		// 收藏按钮的获取
		receive = (CheckBox) findViewById(R.id.receive);

		receive.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final boolean flag = receive.isChecked();
				receive.setChecked(!flag);
				receive.setClickable(false);
				new Thread(new Runnable() {

					@Override
					public void run() {
						if (flag == true) {
							try {
								business.goodsCollect(UserTag.userName, ID);
								runOnUiThread(new Runnable() {

									@Override
									public void run() {
										receiveTag = flag;
										receive.setChecked(receiveTag);
										showTip("收藏成功");
									}
								});
							} catch (Exception e) {
								e.printStackTrace();
								runOnUiThread(new Runnable() {

									@Override
									public void run() {
										showTip("收藏失败");
									}
								});
							}finally{
								receive.setClickable(true);
							}
						}else{
							try {
								business.goodsDisCollect(UserTag.userName, ID);
								runOnUiThread(new Runnable() {

									@Override
									public void run() {
										receiveTag = flag;
										receive.setChecked(receiveTag);
									}
								});
							} catch (Exception e) {
								e.printStackTrace();
								runOnUiThread(new Runnable() {

									@Override
									public void run() {
										showTip("操作失败");
									}
								});
							}finally{
								receive.setClickable(true);
							}
						}
					}
				}).start();
			}
		});

		// 获取控件
		imgHead = (ImageIndicatorView) findViewById(R.id.img_shop_head_pic);
		imgHead.setIndicateStyle(1);
		// 初始化五张图片
		for (int i = 0; i < 5; i++) {
			image[i] = new ImageView(this);
		}
		// 获取商品id
		ID = getIntent().getIntExtra("ID", -1);
		if (ID == -1) {
			showTip("获取ID错误，请联系客服解决问题");
			finish();
			return;
		}

		txtBrand = (TextView) findViewById(R.id.brand);
		txtSystem = (TextView) findViewById(R.id.system);
		txtCpu = (TextView) findViewById(R.id.cpu);
		txtSize = (TextView) findViewById(R.id.size);
		txtRam = (TextView) findViewById(R.id.ram);
		txtRom = (TextView) findViewById(R.id.rom);
		txtResolution = (TextView) findViewById(R.id.resolution);
		txtPreCamera = (TextView) findViewById(R.id.camera);
		txtColor = (TextView) findViewById(R.id.color);
		txtWeb = (TextView) findViewById(R.id.web);
		txtPrice = (TextView) findViewById(R.id.txt_price);
		goodsTitle = (TextView) findViewById(R.id.goods_title);
		txtSellNum = (TextView) findViewById(R.id.sell_num);

		// 获取商品的所有消息
		new Thread(new Runnable() {
			@Override
			public void run() {

				try {
					shopInfo = business.getShopInfo(ID);
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

		// 获取商品的图片
		for (int i = 0; i < 5; i++) {
			final int index = i;
			// 获取五张图片
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {

					try {
						// 从网上下载五张图片

						Bitmap bitmap = business.getShopHeadPics(ID, index);
						if (bitmap != null) {
							bitmapPools.add(bitmap);
						}
						Message msg = new Message();
						msg.what = 1;
						Bundle data = new Bundle();
						data.putParcelable("bitmap", bitmap);
						data.putInt("index", index);
						msg.setData(data);
						handler2.sendMessage(msg);
					} catch (Exception e) {
						e.printStackTrace();
						Message msg = new Message();
						msg.what = -1;
						handler2.sendMessage(msg);
					} finally {
						Thread.interrupted();
					}

				}
			});
			thread.start();
			threadPooling.add(thread);
		}

		// 获取该商品是否收藏

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					isCollect = business.isCollect(UserTag.userName, ID);
					Log.d("mykey", String.valueOf(isCollect));
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							receive.setChecked(isCollect);
							
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	private void showTip(String message) {
		Toast.makeText(this, message, 3000).show();
	}

	// 商品信息的获取
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				// 成功,为各控件设置值
				// private TextView
				if (shopInfo != null) {
					setTextView();
				} else {
					showTip("商品不存在");
					finish();
					return;
				}
				// 为窗口的购买button附上价格
				if (TabMainActivity.btn != null) {
					String price = "￥" + String.valueOf(shopInfo.getPrice());
					txtPrice.setText(price);
				}
				if (prograssDialog != null) {
					prograssDialog.dismiss();
					prograssDialog = null;
				}

				break;
			case -1:
				showTip("数据错误");
				finish();
				break;
			}

		};
	};

	// 轮播图片获取的处理
	private Handler handler2 = new Handler() {
		private int flag = 0;//当flag达到4时表示五张图片加载完毕 可以添加容器
		public void handleMessage(Message msg) {
			
			switch (msg.what) {
			case 1:
				int index = msg.getData().getInt("index");
				Bitmap bitmap = msg.getData().getParcelable("bitmap");
				image[index].setImageBitmap(bitmap);
				if(flag<4){
					flag++;
					return ;
				}
				// 成功,轮播图片
				for (int i = 0; i < 5; i++) {
					imgHead.addViewItem(image[i]);
				}
				imgHead.show();

				break;
			case -1:
				showTip("数据错误");
				finish();
				break;
			}

		};
	};

	private void setTextView() {
		txtBrand.setText(shopInfo.getBrand());
		txtSystem.setText(shopInfo.getOs());
		txtCpu.setText(shopInfo.getCpu());
		txtSize.setText(shopInfo.getPhoneSize());
		txtRam.setText(shopInfo.getRam());
		txtRom.setText(shopInfo.getRom());
		txtResolution.setText(shopInfo.getResolution());
		txtPreCamera.setText(shopInfo.getPreCamera());
		txtColor.setText(shopInfo.getColor());
		txtWeb.setText(shopInfo.getCommucation());
		goodsTitle.setText(shopInfo.getName());
		txtSellNum.setText("月销：" + shopInfo.getSellNums());
	}

	private void clearBitmap() {
		for (Bitmap bitmap : bitmapPools) {
			if (bitmap != null) {
				if (!bitmap.isRecycled()) {
					bitmap.recycle();
				}
			}
		}
		bitmapPools.clear();
	}

	private void clearThread() {
		// 关闭过去的线程
		for (Thread thread : threadPooling) {
			if (thread.isAlive()) {
				thread.interrupt();
			}
		}
		threadPooling.clear();
	}

	@Override
	protected void onDestroy() {
		clearThread();
		clearBitmap();
		if (prograssDialog != null) {
			prograssDialog.dismiss();
			prograssDialog = null;
		}
		super.onDestroy();
	}
}
