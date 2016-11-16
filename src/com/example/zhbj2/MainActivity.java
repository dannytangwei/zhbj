package com.example.zhbj2;

import com.example.zhbj2.fragment.ContentFragment;
import com.example.zhbj2.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

/**   
 * 思路：
 *  一.Spalsh界面
 *  二.Guide界面
 *  三.主界面MainActivity
 *    a.设置侧边栏框架
 *     1.使用开源库 slidingMenu
 *     2.继承自SlidingFragmentActivity
 *     3.把onCreate的修饰符改为public，因为SlidingFragmentActivity的修饰符是public
 *     4.调用相关API 
 *    b.动态加载（覆盖）fragment到MainActivity的侧边栏和主页面
 *  四.设置主页面碎片ContentFragment布局
 *      
 *  五.设置侧边栏碎片LeftMenuFragment的布局
 *       
 *
 */
public class MainActivity extends SlidingFragmentActivity {

	private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";//fragment的标记
	private static final String TAG_CONTENT = "TAG_CONTENT";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		//设置左侧菜单
		setBehindContentView(R.layout.left_menu);
		SlidingMenu slidingMenu = getSlidingMenu();
		//设置全屏可触摸滑动使左侧菜单出现
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setBehindOffset(200);//屏幕预留200像素的宽度
		
		initFragment();
	}
    
	/**
	 * 动态添加碎片：
	 *  1.创建fragment实例
	 *  2.获取到FragmentManager
	 *  3.调用beginTransaction方法开启事务
	 *  4.使用replace()方法向布局容器内加入碎片，用fragment覆盖帧布局。
	 *  5.调用commit（）方法提交事务
	 *  
	 * 注意：
	 *   getSupportFragmentManager();兼容4.0之前的版本
	 *   getFragmentManager();不兼容4.0之前的版本
	 */
	private void initFragment(){
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		//参数一：FramLyaout布局容器的id,参数二：待添加fragment实例
		//参数三：标记，可以通过标记找到对应的fragment
		transaction.replace(R.id.fl_left_menu, new LeftMenuFragment(), TAG_LEFT_MENU);
		transaction.replace(R.id.fl_content, new ContentFragment(), TAG_CONTENT);
		transaction.commit();
	}
	
	/**
	 * 在活动（ManiActivity）中获取侧边栏碎片的实例。
	 *   通过实例从而可以调用碎片里的方法了。
	 * @return 
	 */
	public LeftMenuFragment getLeftMenuFragment(){
		FragmentManager fm = getSupportFragmentManager();
		//注意这里是LeftMenuFragment
		LeftMenuFragment leftFragment = (LeftMenuFragment) fm.findFragmentByTag(TAG_LEFT_MENU);
		return leftFragment;
	}
}
