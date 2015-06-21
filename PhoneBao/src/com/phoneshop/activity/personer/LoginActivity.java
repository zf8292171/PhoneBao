package com.phoneshop.activity.personer;

import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.conn.ConnectTimeoutException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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

public class LoginActivity extends Activity {
	private LinearLayout lineContent;
	private int distance = 400;
	private Button btnLogin;
	private TextView txtResume, txtRegeist;
	private EditText etUser,etPassword;
	private ProgressDialog prograssDialog;
	private Title title;
	
	private UserBusinessInterface loginDo = new UserBusinessImp();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.user_login);
		// ��ʼ������
		init();
		// �����¼�����
		setListen();
	}

	//��ʼ������
	private void init() {

		// ��¼�򶯻�
		lineContent = (LinearLayout) findViewById(R.id.login_content_line);
		lineContent.setTranslationY(distance);
		lineContent.setVisibility(-1);
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						lineContent.setTranslationY(distance);
					}
				});
				distance -= 4;
				if (distance <= 0) {
					this.cancel();
				}
			}
		}, 50, 10);
		lineContent.setVisibility(1);

		btnLogin = (Button) findViewById(R.id.login_btnok);
		txtResume = (TextView) findViewById(R.id.login_txt_resume);
		txtRegeist = (TextView) findViewById(R.id.login_txt_regeist);
		etUser = (EditText) findViewById(R.id.login_user);
		etPassword = (EditText) findViewById(R.id.login_password);
		
		title = (Title) findViewById(R.id.title);

		((TextView)title.findViewById(R.id.title_right)).setText("");
		((TextView)title.findViewById(R.id.title_center)).setText("�� ½");
		
		((LinearLayout)title.findViewById(R.id.layout)).setBackgroundResource(R.color.title_background);
		

	}
	
	
	//�����¼�
	private void setListen(){
		//��½
		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				//����֤
				final String user= etUser.getText().toString();
				final String password = etPassword.getText().toString();
				if(user.equals("")||user==null){
					showTip("�������˺�");
					return;
				}
				if(password.equals("")||password==null){
					showTip("����������");
					return;
				}
				
				//�����ȴ���
				if(prograssDialog == null){
					
					prograssDialog = new ProgressDialog(LoginActivity.this);
					prograssDialog.setTitle("�ȴ�");
					prograssDialog.setMessage("��½��...");
					prograssDialog.setCancelable(false);
					prograssDialog.show();
					
				}
				
				//���webservice
				new Thread(new Runnable() {
					public void run() {
						try {
							loginDo.login(user, password);
							Message msg = new Message();
							Bundle data = new Bundle();
							data.putString("user",user);
							data.putString("password", password);
							msg.setData(data);
							msg.what = 1;
							handler.sendMessage(msg);
						}catch(CustomException e){
							e.printStackTrace();
							Message msg = new Message();
							Bundle data = new Bundle();
							data.putString("ErrorMessage", e.getMessage());
							msg.setData(data);
							handler.sendMessage(msg);
						}catch (ConnectTimeoutException e) {
							e.printStackTrace();
							Message msg = new Message();
							Bundle data = new Bundle();
							data.putString("ErrorMessage", "����������ά��");
							msg.setData(data);
							handler.sendMessage(msg);
						}
						catch (ConnectException e) {
							e.printStackTrace();
							Message msg = new Message();
							Bundle data = new Bundle();
							data.putString("ErrorMessage", "����������ά��");
							msg.setData(data);
							handler.sendMessage(msg);
						}catch (SocketTimeoutException e) {
							e.printStackTrace();
							Message msg = new Message();
							Bundle data = new Bundle();
							data.putString("ErrorMessage", "������æ�ߣ������Ժ��½");
							msg.setData(data);
							handler.sendMessage(msg);
						}
						catch (Exception e) {
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
		//���ü����¼�
		txtResume.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				etUser.setText("");
				etPassword.setText("");
			}
		});
		//ע��
		txtRegeist.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(LoginActivity.this, RegeistActivity.class);
				startActivity(intent);
			}
		});
	}
	
	public void showTip(String message){
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
	
	
	//handler�¼�����
	@SuppressLint("HandlerLeak")
	private  class IHandler extends Handler{
		private WeakReference<LoginActivity> mactivity;
		public IHandler(LoginActivity activity) {
			mactivity = new WeakReference<LoginActivity>(activity);
		}
		@SuppressWarnings("unused")
		@Override
		public void handleMessage(Message msg) {
			if(mactivity.get().prograssDialog!=null){
				mactivity.get().prograssDialog.dismiss();
				mactivity.get().prograssDialog = null;
			}
			
			switch (msg.what) {
			//1Ϊ�ɹ���½��0Ϊ�쳣
			case 0:
				mactivity.get().showTip(msg.getData().getString("ErrorMessage"));
				break;
			case 1:
				//��½����
				String user = msg.getData().getString("user");
				String password = msg.getData().getString("password");
				mactivity.get().showTip("��½�ɹ�");
				//
				UserTag.userName=user;
				LoginActivity.this.finish();
				break;
			default:
				break;
			}
			
		}
	}
	
	private IHandler handler = new IHandler(this);
}
