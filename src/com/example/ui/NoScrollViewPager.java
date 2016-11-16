package com.example.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 一个自定义控件：没有滑动功能的ViewPager
 * @author Administrator
 *
 */
public class NoScrollViewPager extends ViewPager {

	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NoScrollViewPager(Context context) {
		super(context);
	}
	
	//重写此方法，触摸时什么都不做，从而实现对滑动事件的禁用。
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
    	return true;
    }

}
