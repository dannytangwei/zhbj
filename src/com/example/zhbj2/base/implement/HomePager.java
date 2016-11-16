package com.example.zhbj2.base.implement;

import com.example.zhbj2.base.BasePager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * 首页
 *
 */
public class HomePager extends BasePager {

	public HomePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initDate() {
		// 给FrameLayout填充布局对象
		TextView textView = new TextView(mActivity);
		textView.setText("首页");
		textView.setTextColor(Color.RED);
		textView.setTextSize(22);
		textView.setGravity(Gravity.CENTER);
		//调用父类已获取的实例，把textView视图添加到FrameLayout
		fl_content.addView(textView);
		
		ib_menu.setVisibility(View.INVISIBLE);
	}
}
