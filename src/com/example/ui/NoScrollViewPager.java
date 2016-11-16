package com.example.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * һ���Զ���ؼ���û�л������ܵ�ViewPager
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
	
	//��д�˷���������ʱʲô���������Ӷ�ʵ�ֶԻ����¼��Ľ��á�
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
    	return true;
    }

}
