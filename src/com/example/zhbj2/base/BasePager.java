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
 * 5个标签页面的基类：
 *   这是一个类似于自定义控件的类：把标题栏和FrameLayout抽象为一个视图。
 *   
 * 1.创建一个XML布局文件模板。标签页面再细分为标题栏和FrameLayout
 * 2.创建标签页面的基类（各页面中标题栏可以共用，故实现了；FrameLayout各不相同故留给子类实现）
 * 3.给构造器设置Activity参数，使得在创建页面实例时传入Activity实例
 * 4.初始化视图view(用于给ViewPager显示在屏幕上)。
 *   a.标题栏已实现，并找到了其中title控件的实例，各页面子类可自由修改title的值
 *   b.FrameLayout容器为空，也找到了它的实例，各页面子类到时可自由添加内容进去
 * 5.把view设置为全局变量mRootView，供子类调用。  
 */
public class BasePager {

	public Activity mActivity;
	public TextView tv_title; // 使用public修饰，供子类调用
	public ImageButton ib_menu;
	public FrameLayout fl_content;// 空的实例，供子类调用添加view视图

	// 当前页面的布局文件对象，这是可以显示在屏幕上的view.用于给ViewPager显示出来。
	public View mRootView;

	// 通过构造器传入Activity实例
	public BasePager(Activity activity) {
		mActivity = activity;
		mRootView = initView();
		// 因为ViewPager会默认当前页的前后页面，这样比较消耗流量。
		// 所以等页面被选中时才调用initDate方法
		// initDate();
	}

	/**
	 * 初始化视图，
	 */
	public View initView() {
		
		// 获取view。这里的上下文通过构造传入Activity实例而获得。
		View view = View.inflate(mActivity, R.layout.base_pager, null);
		
		tv_title = (TextView) view.findViewById(R.id.tv_title);
		ib_menu = (ImageButton) view.findViewById(R.id.ib_menu);
		fl_content = (FrameLayout) view.findViewById(R.id.fl_content);

		ib_menu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 打开或者关闭侧边栏
				toggle();
			}
		});
		return view;
	}

	/**
	 * 初始化数据： 用于给FrameLayout容器设置内容
	 */
	public void initDate() {

	}
	
	// 打开或者关闭侧边栏
	protected void toggle() {
		// TODO Auto-generated method stub
		MainActivity mainUI = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainUI.getSlidingMenu();
		// Toggle the SlidingMenu. If it is open, it will be closed, and vice
		// versa
		slidingMenu.toggle();
	}
}
