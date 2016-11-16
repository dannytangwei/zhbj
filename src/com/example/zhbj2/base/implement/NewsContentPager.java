package com.example.zhbj2.base.implement;

import com.example.domain.NewsMenu;
import com.example.global.GlobalConstants;
import com.example.utils.CacheUtils;
import com.example.zhbj2.MainActivity;
import com.example.zhbj2.base.BasePager;
import com.example.zhbj2.fragment.LeftMenuFragment;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ��������
 * @author Administrator
 *
 */
public class NewsContentPager extends BasePager {

	public NewsContentPager(Activity activity) {
		super(activity);
	}

	@Override
	public void initDate() {
		// ��FrameLayout��䲼�ֶ���
		TextView textView = new TextView(mActivity);
		textView.setText("��������");
		textView.setTextColor(Color.RED);
		textView.setTextSize(22);
		textView.setGravity(Gravity.CENTER);
		//���ø����ѻ�ȡ��ʵ������textView��ͼ��ӵ�FrameLayout
		fl_content.addView(textView);
		
		//�޸ı���
		tv_title.setText("����");
		
		//�ж��Ƿ��л���
		if(!TextUtils.isEmpty(CacheUtils.getCache(mActivity, GlobalConstants.CATEGORY_URL)))
		{
			System.out.println("ʹ�û��棬����");
			//�������ݡ�
			parseDate(CacheUtils.getCache(mActivity, GlobalConstants.CATEGORY_URL));
		}else{
			//�������������ȡ���ݡ�ʹ�ÿ�Դ��ܣ�xUtils
			getFromServer();
		}
	}

	/**
	 * �ӷ�������ȡ����
	 * ��������ǵ����Ȩ��
	 */
	private void getFromServer() {
		
		HttpUtils utils = new HttpUtils();
		//new RequestCallBack<T> T��Ҫ����һ�����ͣ�file�����ݵ����ļ���
		//String�����ݵ����ַ�����
		utils.send(HttpMethod.GET, GlobalConstants.CATEGORY_URL, new RequestCallBack<String>() {

			//����ɹ�
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				
				String result = responseInfo.result;
				System.out.println("���������صĽ����"+result);
				
				//�������ݡ� ʹ��Gson
				parseDate(result);
				//���û��棬��URLΪkey����Json����Ϊvalue�����滺�档
				CacheUtils.setCache(mActivity, GlobalConstants.CATEGORY_URL, result);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// ��ӡ�쳣��־
				error.printStackTrace();
				// ���û����ʹ�����Ϣ
				Toast.makeText(mActivity, msg, 0).show();
			}
		});
	}

	/**
	 * ��������
	 * @param json ������������
	 */
	private void parseDate(String json) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		NewsMenu jsonData = gson.fromJson(json, NewsMenu.class);
		System.out.println("���������"+jsonData);
		
		/*
		 * ��Ƭ���ͨ�ţ�
		 *  �������ҳ����Ƭ�д������ݸ��������Ƭ?
		 *  1.��MainActivity�еõ��������Ƭ��ʵ��
		 *  2.����ҳ����Ƭ�й�����MainActivity���Ӷ����Եõ��������ʵ��
		 *  3.���ò�����еķ�����������
		 */
		MainActivity mainUI = (MainActivity) mActivity;
		LeftMenuFragment leftMenuFragment = mainUI.getLeftMenuFragment();
		//���ò�����еķ�����������
		leftMenuFragment.setMenuData(jsonData.data);
	}

}
