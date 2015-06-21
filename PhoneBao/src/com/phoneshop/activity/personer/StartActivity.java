package com.phoneshop.activity.personer;

import com.example.phonebao.R;
import com.phoneshop.activity.phone.MainContentActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.start_layout);

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					handler.sendEmptyMessage(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			finish();
			Intent intent = new Intent(StartActivity.this, MainContentActivity.class);
			startActivity(intent);

		};
	};
}
