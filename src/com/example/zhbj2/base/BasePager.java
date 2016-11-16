package com.example.zhbj2.base;

import com.example.zhbj2.MainActivity;
import com.example.zhbj2.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * 5����ǩҳ��Ļ��ࣺ
 *   ����һ���������Զ���ؼ����ࣺ�ѱ�������FrameLayout����Ϊһ����ͼ��
 *   
 * 1.����һ��XML�����ļ�ģ�塣��ǩҳ����ϸ��Ϊ��������FrameLayout
 * 2.������ǩҳ��Ļ��ࣨ��ҳ���б��������Թ��ã���ʵ���ˣ�FrameLayout������ͬ����������ʵ�֣�
 * 3.������������Activity������ʹ���ڴ���ҳ��ʵ��ʱ����Activityʵ��
 * 4.��ʼ����ͼview(���ڸ�ViewPager��ʾ����Ļ��)��
 *   a.��������ʵ�֣����ҵ�������title�ؼ���ʵ������ҳ������������޸�title��ֵ
 *   b.FrameLayout����Ϊ�գ�Ҳ�ҵ�������ʵ������ҳ�����ൽʱ������������ݽ�ȥ
 * 5.��view����Ϊȫ�ֱ���mRootView����������á�  
 */
public class BasePager {

	public Activity mActivity;
	public TextView tv_title; // ʹ��public���Σ����������
	public ImageButton ib_menu;
	public FrameLayout fl_content;// �յ�ʵ����������������view��ͼ

	// ��ǰҳ��Ĳ����ļ��������ǿ�����ʾ����Ļ�ϵ�view.���ڸ�ViewPager��ʾ������
	public View mRootView;

	// ͨ������������Activityʵ��
	public BasePager(Activity activity) {
		mActivity = activity;
		mRootView = initView();
		// ��ΪViewPager��Ĭ�ϵ�ǰҳ��ǰ��ҳ�棬�����Ƚ�����������
		// ���Ե�ҳ�汻ѡ��ʱ�ŵ���initDate����
		// initDate();
	}

	/**
	 * ��ʼ����ͼ��
	 */
	public View initView() {
		
		// ��ȡview�������������ͨ�����촫��Activityʵ������á�
		View view = View.inflate(mActivity, R.layout.base_pager, null);
		
		tv_title = (TextView) view.findViewById(R.id.tv_title);
		ib_menu = (ImageButton) view.findViewById(R.id.ib_menu);
		fl_content = (FrameLayout) view.findViewById(R.id.fl_content);

		ib_menu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// �򿪻��߹رղ����
				toggle();
			}
		});
		return view;
	}

	/**
	 * ��ʼ�����ݣ� ���ڸ�FrameLayout������������
	 */
	public void initDate() {

	}
	
	// �򿪻��߹رղ����
	protected void toggle() {
		// TODO Auto-generated method stub
		MainActivity mainUI = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainUI.getSlidingMenu();
		// Toggle the SlidingMenu. If it is open, it will be closed, and vice
		// versa
		slidingMenu.toggle();
	}
}
