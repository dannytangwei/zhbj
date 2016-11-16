package com.example.zhbj2.fragment;

import java.util.ArrayList;

import com.example.ui.NoScrollViewPager;
import com.example.zhbj2.MainActivity;
import com.example.zhbj2.R;
import com.example.zhbj2.base.BasePager;
import com.example.zhbj2.base.implement.GovAffairsPager;
import com.example.zhbj2.base.implement.HomePager;
import com.example.zhbj2.base.implement.NewsContentPager;
import com.example.zhbj2.base.implement.SettingPager;
import com.example.zhbj2.base.implement.SmartServicePager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * 主页面碎片ContentFragment：
 *   用于动态加载（覆盖）到MainActivity的主页面activity_main。
 *   
 * 1.创建fragment_content.xml布局
 *   a.主页面粗分为2大块，上面用ViewPager容器，下面为RadioGroup
 *   b.实现RadioGroup容器中的5个标签
 *   c.创建一个不能左右滑动自定义ViewPager（记得要使用全类名）
 * 2.创建ContentFragment类继承自BaseFragment父类，加载fragment_content.xml布局
 * 3.创建5个标签页面（view）。
 *   a.创建一个XML布局文件模板。标签页面再细分为标题栏和FrameLayout
 *   b.创建标签页面的基类（各页面中标题栏可以共用，故实现了；FrameLayout各不相同故留给子类实现）
 *   c.给构造器设置Activity参数，使得在创建页面实例时传入Activity实例
 *   d.初始化视图view(用于给ViewPager显示在屏幕上)。
 *     标题栏已实现，并找到了其中title控件的实例，各页面子类可自由修改title的值
 *     FrameLayout容器为空，也找到了它的实例，各页面子类到时可自由添加内容进去
 *   e.把view设置为全局变量mRootView，供子类调用。
 * 4.把标签页面添加到适配器，并设置到ViewPager
 *   a.创建5个标签页面类的实例添加到集合。
 *   b.
 *   c.
 *
 */
public class ContentFragment extends BaseFragment {

	private NoScrollViewPager vp_content;
	private ArrayList<BasePager> mPagers;//5个标签页的集合
	private RadioGroup rg_group;

	
	@Override
	public View initView() {
		//加载fragment_content.xml布局
		View view = View.inflate(mActivity1, R.layout.fragment_content, null);
		//获取视图中ViewPager的实例
		vp_content = (NoScrollViewPager) view.findViewById(R.id.vp_fragment_content);
		rg_group = (RadioGroup) view.findViewById(R.id.rg_group);
		return view;
	}

	@Override
	public void initDate() {
		mPagers = new ArrayList<BasePager>();
		// 调用父类的构造方法传入activity，创建5个标签页面类的实例(这并不是视图，只是一个类的实例)
		 //并把5个标签页类的实例都添加到集合。
		mPagers.add(new HomePager(mActivity1));
		mPagers.add(new NewsContentPager(mActivity1));
		mPagers.add(new SmartServicePager(mActivity1));
		mPagers.add(new GovAffairsPager(mActivity1));
		mPagers.add(new SettingPager(mActivity1));
		
		vp_content.setAdapter(new ContentAdapter());
		
		//底栏标签切换监听
		rg_group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.rb_fragment_home:
					//切换到第一个条目视图，false表示没有动画。
					vp_content.setCurrentItem(0, false);
					break;
				case R.id.rb_fragment_news:
					vp_content.setCurrentItem(1, false);
					break;
				case R.id.rb_fragment_smart:
					vp_content.setCurrentItem(2, false);
					break;
				case R.id.rb_fragment_gov:
					vp_content.setCurrentItem(3, false);
					break;
				case R.id.rb_fragment_setting:
					vp_content.setCurrentItem(4, false);
					break;
				}
			}
		});
		vp_content.setOnPageChangeListener(new OnPageChangeListener() {
			
			//切换至某页面时调用
			@Override
			public void onPageSelected(int position) {
				// 页面被选中时才调用initDate方法
				BasePager pager = mPagers.get(position);
				pager.initDate();
				if(position == 0 || position == mPagers.size()-1){
					//首页和设置页面要禁用侧边栏	
					setSlidingMenuEnable(false);
				}else{
					setSlidingMenuEnable(true);
				}
				
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		//手动加载第一页的数据。
		//因为刚进入程序时没有切换/选中页面，以后当用户切换到哪页就加载哪页的数据。
		mPagers.get(0).initDate();
		//首页禁用侧边栏
		setSlidingMenuEnable(false);
	}
	
    //开启或禁用侧边栏
	protected void setSlidingMenuEnable(boolean enable) {
		// 通过MainActivity对象获取侧边栏对象
		MainActivity mainUI = (MainActivity) mActivity1;
	    SlidingMenu slidingMenu = mainUI.getSlidingMenu();
	    if(enable){
	    	slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
	    }else{
	    	slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
	    }
	}


	class ContentAdapter extends PagerAdapter{
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mPagers.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view == object;
		}
		
		//返回要显示的条目
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// 得到待显示的页面类的实例
			BasePager pager = mPagers.get(position);
			//得到视图
			View view = pager.mRootView;
			container.addView(view);
			return view;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View) object);;
		}
	}
}
