package com.phoneshop.customview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.phonebao.R;

public class Title extends LinearLayout {

	@SuppressWarnings("unused")
	private TextView left, center, right;
	private Context mcontext;

	public Title(Context context, AttributeSet attrs) {
		super(context, attrs);
		mcontext = context;
		LayoutInflater.from(context).inflate(R.layout.originer_title, this);

		// ³õÊ¼»¯²Ù×÷
		init();
	}

	public Title(Context context) {
		super(context);
	}

	public Title(Context context, AttributeSet attrs, int arg) {
		super(context, attrs, arg);
	}

	private void init() {
		left = (TextView) findViewById(R.id.title_left);
		center = (TextView) findViewById(R.id.title_center);
		right = (TextView) findViewById(R.id.title_right);

		left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((Activity) mcontext).finish();
			}
		});
	}

}
