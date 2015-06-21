package com.phoneshop.activity.trade;

import com.example.phonebao.R;
import com.handmark.pulltorefresh.library.internal.Utils;
import com.phoneshop.activity.personer.LoginActivity;
import com.phoneshop.activity.phone.MainContentActivity;
import com.phoneshop.activity.phone.TabActivityHome;
import com.phoneshop.activity.phone.TabActivityPersonInfo;
import com.phoneshop.activity.phone.TabActivityShopCar;
import com.phoneshop.business.CustomException;
import com.phoneshop.business.PhoneBusinessImp;
import com.phoneshop.utils.UserTag;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class TabMainActivity extends TabActivity {

	public static Button btn;
	private PhoneBusinessImp business = new PhoneBusinessImp();
	private ProgressDialog prograssDialog;
	private int ID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tab_main_2);

		// ��ȡ��ƷID
		ID = -1;
		ID = getIntent().getIntExtra("ID", -1);
		if (ID == -1) {
			showTip("��ȡID��������ϵ�ͷ��������");
			finish();
			return;
		}
		// ��ȡ�����button
		btn = (Button) findViewById(R.id.btn);
		Bundle data = new Bundle();
		TabHost myTab = getTabHost();
		TabSpec specMain = myTab.newTabSpec("info").setIndicator("��Ʒ��Ϣ")
				.setContent(new Intent(this, TabShopInfoActiivty.class).putExtra("ID", ID));
		TabSpec specContent = myTab.newTabSpec("pic").setIndicator("ͼ����Ϣ")
				.setContent(new Intent(this, TabShopPicActivity.class).putExtra("ID", ID));
		TabSpec specPerson = myTab.newTabSpec("comment").setIndicator("�������")
				.setContent(new Intent(this, TabShopCommentsActivity.class).putExtra("ID", ID));
		myTab.addTab(specMain);
		myTab.addTab(specContent);
		myTab.addTab(specPerson);
		ID = getIntent().getIntExtra("ID", -1);

		// Ϊ����ť���ü����¼�
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// �������ڣ��Ƿ�ȷ�Ϲ���
				new AlertDialog.Builder(TabMainActivity.this).setTitle("ȷ����").setMessage("�Ƿ�ȷ���������Ʒ��")
						.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// ��֤�Ƿ��½
								if (UserTag.userName == null) {
									new AlertDialog.Builder(TabMainActivity.this).setTitle("��ʾ").setMessage("����δ��½���Ƿ�תȥ��½ҳ�棿")
											.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

												@Override
												public void onClick(DialogInterface dialog, int which) {
													Intent intent = new Intent(TabMainActivity.this, LoginActivity.class);
													startActivity(intent);
												}
											}).setNegativeButton("ȡ��", null).setCancelable(false).show();
									return;
								}
								// �����ȴ���
								if (prograssDialog == null) {

									prograssDialog = new ProgressDialog(TabMainActivity.this);
									prograssDialog.setTitle("�ȴ�");
									prograssDialog.setMessage("��½��...");
									prograssDialog.setCancelable(false);
									prograssDialog.show();

								}

								// ��������ﳵ
								new Thread(new Runnable() {

									@Override
									public void run() {

										// �¼�
										try {
											business.addToShopCart(ID, UserTag.userName, 1);
											Message msg = new Message();
											msg.what = 1;
											handler.sendMessage(msg);
										} catch (CustomException e) {
											e.printStackTrace();
											Message msg = new Message();
											msg.what = 0;
											Bundle data = new Bundle();
											data.putString("error", e.getMessage());
											msg.setData(data);
											handler.sendMessage(msg);
										} catch (Exception e) {
											e.printStackTrace();
											Message msg = new Message();
											msg.what = -1;
											handler.sendMessage(msg);
										}
									}
								}).start();

								// ȷ�Ϲ����ѯ���Ƿ���˻��Ǽ��������
								new AlertDialog.Builder(TabMainActivity.this).setTitle("ѡ��").setMessage("ȥ���˻��Ǽ�������")
										.setPositiveButton("����", new DialogInterface.OnClickListener() {

											@Override
											public void onClick(DialogInterface dialog, int which) {
												Intent intent = new Intent(TabMainActivity.this, MainContentActivity.class);
												startActivity(intent);
											}
										}).setNegativeButton("��������", null).setCancelable(false).show();
							}
						}).setNegativeButton("ȡ��", null).setCancelable(false).show();
			}
		});

	}

	public void showTip(String message) {
		Toast.makeText(this, message, 3000).show();
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (prograssDialog != null) {
				prograssDialog.dismiss();
				prograssDialog = null;
			}

			switch (msg.what) {
			case 1:
				showTip(msg.getData().getString("��ӹ��ﳵ�ɹ�"));
				break;
			case 0:
				showTip(msg.getData().getString("error"));
				break;
			case -1:
				showTip("��ӹ��ﳵʧ��");
				break;
			}

		};
	};

}
