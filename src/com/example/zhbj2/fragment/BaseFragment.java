package com.example.zhbj2.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * fragment����:�������Ƭ����ҳ����Ƭ�Ļ��ࡣ
 *   1.�̳���Fragment
 *   2.��дonCreateView�������������м��ز���(��������ʵ�֣���Ϊ������Ƭû����ͬ��ģ
 *     ������ڸ�����ʵ��)
 *   3.ͨ��getActivity()�õ��͵�ǰ��Ƭ������Ļ(�ָ����MainActivity)ʵ��������ʹ��
 * ע�⣺
 *   1.Fragment������import android.support.v4.app.Fragment;����4.0֮ǰ�ľɰ汾
 * ֪ʶ�㣺
 *   1.�����ʹ��
 *   2.Fragment���÷�  
 */
public abstract class BaseFragment extends Fragment {
 
	//����Ϊȫ�ֱ�������������á���������ʹ��getActivity()���ֿ�ָ���쳣
	public Activity mActivity1;

	//fragMent����ʱ�ص�
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//�õ��͵�ǰ��Ƭ������Ļʵ�����Ӷ�������fragment�е��û�еķ���!
		//ͬʱ��������Ϊ��Ƭ����Ҫʹ�õ�Context����
		mActivity1 = getActivity(); 
	}
	
	//Ϊfragment������ͼ�����ز��֣�ʱ����
	//��Ϊ�����ǻ��࣬�����޷�ȷ��Ҫ����ʲô��ͼ������Ҫ����ɳ���ķ�����������ȥʵ�֡�
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = initView();
		return view;
	}
	
	//��fragment������Ļ�������ʱ����
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		initDate();
	}
	
	/**
	 * ��ʼ�����֣�����������ʵ�֡�
	 * Ϊfragment������ͼ�����ز��֣�
	 * @return
	 */
	public abstract View initView();
	
	/**
	 * ��ʼ�����ݣ�����������ʵ��
	 */
	public abstract void initDate();
}
