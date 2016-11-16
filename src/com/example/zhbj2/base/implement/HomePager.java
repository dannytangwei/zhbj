package com.example.zhbj2.base.implement;

import com.example.zhbj2.base.BasePager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * ��ҳ
 *
 */
public class HomePager extends BasePager {

	public HomePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initDate() {
		// ��FrameLayout��䲼�ֶ���
		TextView textView = new TextView(mActivity);
		textView.setText("��ҳ");
		textView.setTextColor(Color.RED);
		textView.setTextSize(22);
		textView.setGravity(Gravity.CENTER);
		//���ø����ѻ�ȡ��ʵ������textView��ͼ��ӵ�FrameLayout
		fl_content.addView(textView);
		
		ib_menu.setVisibility(View.INVISIBLE);
	}
}
