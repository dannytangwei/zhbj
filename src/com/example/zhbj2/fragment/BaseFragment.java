package com.example.zhbj2.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * fragment基类:侧边栏碎片与主页面碎片的基类。
 *   1.继承自Fragment
 *   2.重写onCreateView方法，并在其中加载布局(留给子类实现，因为两个碎片没有相同的模
 *     块可以在父类中实现)
 *   3.通过getActivity()得到和当前碎片相关联的活动(活动指的是MainActivity)实例供子类使用
 * 注意：
 *   1.Fragment导包用import android.support.v4.app.Fragment;兼容4.0之前的旧版本
 * 知识点：
 *   1.基类的使用
 *   2.Fragment的用法  
 */
public abstract class BaseFragment extends Fragment {
 
	//定义为全局变量，供子类调用。避免子类使用getActivity()出现空指针异常
	public Activity mActivity1;

	//fragMent创建时回调
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//得到和当前碎片相关联的活动实例，从而可以在fragment中调用活动中的方法!
		//同时还可以作为碎片中需要使用的Context对象。
		mActivity1 = getActivity(); 
	}
	
	//为fragment创建视图（加载布局）时调用
	//因为这里是基类，现在无法确定要创建什么视图。所以要定义成抽象的方法，让子类去实现。
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = initView();
		return view;
	}
	
	//与fragment相关联的活动创建完毕时调用
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		initDate();
	}
	
	/**
	 * 初始化布局，必须由子类实现。
	 * 为fragment创建视图（加载布局）
	 * @return
	 */
	public abstract View initView();
	
	/**
	 * 初始化数据，必须由子类实现
	 */
	public abstract void initDate();
}
