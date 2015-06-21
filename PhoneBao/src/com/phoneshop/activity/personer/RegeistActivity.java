package com.phoneshop.activity.personer;

import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.conn.ConnectTimeoutException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.sax.StartElementListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phonebao.R;
import com.phoneshop.activity.phone.MainContentActivity;
import com.phoneshop.business.CustomException;
import com.phoneshop.business.UserBusinessImp;
import com.phoneshop.business.UserBusinessInterface;
import com.phoneshop.customview.Title;
import com.phoneshop.utils.UserTag;

public class RegeistActivity extends Activity {
	private Title title;
	private Button btnNext, btnNext2, btnNext3;
	private EditText etUser, etName, etNo, etPassword;
	private LinearLayout pageLoad, pageLoad2, pageLoad3;
	private UserBusinessInterface userBusiness = new UserBusinessImp();
	private ProgressDialog prograssDialog;

	private static final int MSG_SUCCESS_USERREPETER = 1;
	private static final int MSG_SUCCESS_USERREGEIST = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.user_regeist);

		// ��ʼ��
		init();
	}

	private void init() {
		try{
			title = (Title) findViewById(R.id.title);
			((TextView) title.findViewById(R.id.title_center)).setText("ע ��");
			((TextView) title.findViewById(R.id.title_right)).setText("");
			((LinearLayout)title.findViewById(R.id.layout)).setBackgroundResource(R.color.title_background);
			btnNext = (Button) findViewById(R.id.login_btn_next);
			btnNext2 = (Button) findViewById(R.id.login_btn_next2);
			btnNext3 = (Button) findViewById(R.id.login_btn_next3);
			btnNext3.setText("��  ��");
			etUser = (EditText) findViewById(R.id.regeist_input_user);
			etName = (EditText) findViewById(R.id.regeist_input_name);
			etNo = (EditText) findViewById(R.id.regeist_input_no);
			etPassword = (EditText) findViewById(R.id.regeist_input_password);
			pageLoad = (LinearLayout) findViewById(R.id.regeist_page1);
			pageLoad2 = (LinearLayout) findViewById(R.id.regeist_page2);
			pageLoad3 = (LinearLayout) findViewById(R.id.regeist_page3);
		}catch(Exception e){
			e.printStackTrace();
			Toast.makeText(this, "��ʼ������", Toast.LENGTH_LONG).show();
		}

		// ʵ�ֶ�̬ҳ����ת
		//���˺��ظ����м���
		btnNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// ����
				String str = etUser.getText().toString().trim();
				if (str == null || str.equals("") ) {
					Toast.makeText(RegeistActivity.this, "�����������û���",
							Toast.LENGTH_SHORT).show();
					return;
				}

				if (prograssDialog == null) {

					prograssDialog = new ProgressDialog(RegeistActivity.this);
					prograssDialog.setTitle("�ȴ�");
					prograssDialog.setMessage("�û�����֤��...");
					prograssDialog.setCancelable(false);
					prograssDialog.show();

				}
				

				
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							String user = etUser.getText().toString();
							userBusiness.userRepeter(user);
							handler.sendEmptyMessage(MSG_SUCCESS_USERREPETER);
						} catch (CustomException e) {
							e.printStackTrace();
							Message msg = new Message();
							Bundle data = new Bundle();
							data.putString("ErrorMessage", e.getMessage());
							msg.setData(data);
							handler.sendMessage(msg);
						} catch (ConnectTimeoutException e) {
							e.printStackTrace();
							Message msg = new Message();
							Bundle data = new Bundle();
							data.putString("ErrorMessage", "����������ά��");
							msg.setData(data);
							handler.sendMessage(msg);
						} catch (ConnectException e) {
							e.printStackTrace();
							Message msg = new Message();
							Bundle data = new Bundle();
							data.putString("ErrorMessage", "����������ά��");
							msg.setData(data);
							handler.sendMessage(msg);
						} catch (SocketTimeoutException e) {
							e.printStackTrace();
							Message msg = new Message();
							Bundle data = new Bundle();
							data.putString("ErrorMessage", "������æ�ߣ������Ժ�ע��");
							msg.setData(data);
							handler.sendMessage(msg);
						} catch (Exception e) {
							e.printStackTrace();
							Message msg = new Message();
							Bundle data = new Bundle();
							data.putString("ErrorMessage", "����ִ�г���");
							msg.setData(data);
							handler.sendMessage(msg);
						}
					}
				}).start();

			}
		});

		
		//����phone��email�¼�����
		btnNext2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ����
				String strName = etName.getText().toString().trim();
				if (strName.equals("") || strName == null) {
					Toast.makeText(RegeistActivity.this, "������������ϵ�绰",
							Toast.LENGTH_SHORT).show();
					return;
				}
				String strNo = etNo.getText().toString().trim();
				if (strNo.equals("") || strNo == null) {
					Toast.makeText(RegeistActivity.this, "����������email��ַ",
							Toast.LENGTH_SHORT).show();
					return;
				}


				pageLoad2.setVisibility(View.GONE);
				pageLoad3.setVisibility(View.VISIBLE);
			}
		});

		//ע�ᰴť����
		btnNext3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ����
				String str = etPassword.getText().toString().trim();
				if (str.equals("") || str == null) {
					Toast.makeText(RegeistActivity.this, "��������������",
							Toast.LENGTH_SHORT).show();
					return;
				}
				//��ȡֵ
				String user, password, name, no;
				user = etUser.getText().toString().trim();
				password = etPassword.getText().toString().trim();
				name = etName.getText().toString().trim();
				no = etNo.getText().toString().trim();
				final Map<String, String> data = new HashMap<String,String>();
				if (user.equals("") || user == null || password.equals("")
						|| password == null || name.equals("") || name == null
						|| no.equals("") || no == null) {
					Toast.makeText(RegeistActivity.this, "�����쳣���޷�ע��", Toast.LENGTH_SHORT).show();
					return ;
				}
				data.put("user", user);
				data.put("password", password);
				data.put("phone", name);
				data.put("email", no);

				if (prograssDialog == null) {

					prograssDialog = new ProgressDialog(RegeistActivity.this);
					prograssDialog.setTitle("�ȴ�");
					prograssDialog.setMessage("ע�������...");
					prograssDialog.setCancelable(false);
					prograssDialog.show();

				}

				new Thread(new Runnable() {

					@SuppressWarnings("unused")
					@Override
					public void run() {
						try {
							String user = etUser.getText().toString();
							userBusiness.userRegeist(data);
							UserTag.userName = user;
							handler.sendEmptyMessage(MSG_SUCCESS_USERREGEIST);
						} catch (CustomException e) {
							e.printStackTrace();
							Message msg = new Message();
							Bundle data = new Bundle();
							data.putString("ErrorMessage", e.getMessage());
							msg.setData(data);
							handler.sendMessage(msg);
						} catch (ConnectTimeoutException e) {
							e.printStackTrace();
							Message msg = new Message();
							Bundle data = new Bundle();
							data.putString("ErrorMessage", "����������ά��");
							msg.setData(data);
							handler.sendMessage(msg);
						} catch (ConnectException e) {
							e.printStackTrace();
							Message msg = new Message();
							Bundle data = new Bundle();
							data.putString("ErrorMessage", "����������ά��");
							msg.setData(data);
							handler.sendMessage(msg);
						} catch (SocketTimeoutException e) {
							e.printStackTrace();
							Message msg = new Message();
							Bundle data = new Bundle();
							data.putString("ErrorMessage", "������æ�ߣ������Ժ�ע��");
							msg.setData(data);
							handler.sendMessage(msg);
						} catch (Exception e) {
							e.printStackTrace();
							Message msg = new Message();
							Bundle data = new Bundle();
							data.putString("ErrorMessage", "����ִ�г���");
							msg.setData(data);
							handler.sendMessage(msg);
						}
					}
				}).start();

				// ע����ɵ��¼�
				// ....

			}
		});

	}

	public void showTip(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	// handler�¼�����
	private static class IHandler extends Handler {
		private WeakReference<RegeistActivity> mactivity;

		public IHandler(RegeistActivity activity) {
			mactivity = new WeakReference<RegeistActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			if (mactivity.get().prograssDialog != null) {
				mactivity.get().prograssDialog.dismiss();
				mactivity.get().prograssDialog = null;
			}

			switch (msg.what) {
			case 0:
				// �쳣����
				mactivity.get()
						.showTip(msg.getData().getString("ErrorMessage"));
				break;
			case MSG_SUCCESS_USERREPETER:
				// ������һҳ��
				mactivity.get().pageLoad.setVisibility(View.GONE);
				mactivity.get().pageLoad2.setVisibility(View.VISIBLE);
				break;

			case MSG_SUCCESS_USERREGEIST:
				// ע��ɹ���ִ��
				mactivity.get().showTip("ע��ɹ�");
				Intent intent = new Intent(mactivity.get(),MainContentActivity.class);
				mactivity.get().startActivity(intent);
				
				break;
			default:
				break;
			}

		}
	}

	private IHandler handler = new IHandler(this);

}
