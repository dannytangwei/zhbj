package com.example.zhbj2.base.implement;

import com.example.zhbj2.base.BasePager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

/**
 * ����
 * @author Administrator
 *
 */
public class GovAffairsPager extends BasePager {

	public GovAffairsPager(Activity activity) {
		super(activity);
	}

	@Override
	public void initDate() {
		// ��FrameLayout��䲼�ֶ���
		TextView textView = new TextView(mActivity);
		textView.setText("����");
		textView.setTextColor(Color.RED);
		textView.setTextSize(22);
		textView.setGravity(Gravity.CENTER);
		//���ø����ѻ�ȡ��ʵ������textView��ͼ��ӵ�FrameLayout
		fl_content.addView(textView);
		
		//�޸ı���
		tv_title.setText("�˿ڹ���");
	}
}
