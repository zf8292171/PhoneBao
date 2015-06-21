package com.phoneshop.activity.trade;

import java.util.ArrayList;
import java.util.List;

import com.example.phonebao.R;
import com.phoneshop.business.PhoneBusinessImp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TabShopPicActivity extends Activity {

	private ImageView[] image;
	private LinearLayout linearLayout;
	private int ID;
	private List<Bitmap> bitmapPools = new ArrayList<Bitmap>();

	private PhoneBusinessImp business = new PhoneBusinessImp();
	
	// 线程回收池
	private List<Thread> threadPooling = new ArrayList<Thread>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shop_tab_trade_pic);

		// 获取商品id
		ID = getIntent().getIntExtra("ID", -1);
		if (ID == -1) {
			showTip("获取ID错误，请联系客服解决问题");
			finish();
			return;
		}

		// 初始化
		init();
	}

	private void init() {
		// 初始化五张图片
		image = new ImageView[5];
		for (int i = 0; i < 5; i++) {
			image[i] = new ImageView(this);
		}

		linearLayout = (LinearLayout) findViewById(R.id.content);

		for (int i = 0; i < 5; i++) {
			final int index =i;
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						// 从网上下载五张图片
						Bitmap bitmap = business.getShopPic(ID, index);
						if (bitmap != null) {
							bitmapPools.add(bitmap);
						}
						Message msg = new Message();
						msg.what = 1;
						Bundle data = new Bundle();
						data.putParcelable("bitmap", bitmap);
						data.putInt("index", index);
						msg.setData(data);
						handler.sendMessage(msg);
					} catch (Exception e) {
						e.printStackTrace();
						Message msg = new Message();
						msg.what = -1;
						handler.sendMessage(msg);
					}finally{
						Thread.interrupted();
					}
				}
			});
			thread.start();
			threadPooling.add(thread);
		}

	}

	//图片下载的资源管理
	private Handler handler = new Handler() {
		private int flag = 0;//当flag达到4时表示五张图片加载完毕 可以添加容器
		public void handleMessage(android.os.Message msg) {
			
			switch (msg.what) {
			case 1:
				int index = msg.getData().getInt("index");
				Bitmap bitmap = msg.getData().getParcelable("bitmap");
				image[index].setImageBitmap(bitmap);
				if(flag<4){
					flag++;
					return ;
				}
				// 成功，将其添加到容器
				if (linearLayout != null && image != null) {
					for (int i = 0; i < 5; i++) {
						linearLayout.addView(image[i]);
					}
				} else {
					showTip("获取数据失败");
					return;
				}
				break;
			case -1:
				showTip("获取数据失败");
				finish();
				return;
			}
		};
	};

	private void showTip(String message) {
		Toast.makeText(this, message, 3000).show();
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
		super.onDestroy();
	}
}
