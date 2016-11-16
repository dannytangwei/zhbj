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
 * 新闻中心
 * @author Administrator
 *
 */
public class NewsContentPager extends BasePager {

	public NewsContentPager(Activity activity) {
		super(activity);
	}

	@Override
	public void initDate() {
		// 给FrameLayout填充布局对象
		TextView textView = new TextView(mActivity);
		textView.setText("新闻中心");
		textView.setTextColor(Color.RED);
		textView.setTextSize(22);
		textView.setGravity(Gravity.CENTER);
		//调用父类已获取的实例，把textView视图添加到FrameLayout
		fl_content.addView(textView);
		
		//修改标题
		tv_title.setText("新闻");
		
		//判断是否有缓存
		if(!TextUtils.isEmpty(CacheUtils.getCache(mActivity, GlobalConstants.CATEGORY_URL)))
		{
			System.out.println("使用缓存，，，");
			//解析数据。
			parseDate(CacheUtils.getCache(mActivity, GlobalConstants.CATEGORY_URL));
		}else{
			//请求服务器，获取数据。使用开源框架：xUtils
			getFromServer();
		}
	}

	/**
	 * 从服务器获取数据
	 * 访问网络记得添加权限
	 */
	private void getFromServer() {
		
		HttpUtils utils = new HttpUtils();
		//new RequestCallBack<T> T需要传递一个泛型，file代表传递的是文件，
		//String代表传递的是字符串。
		utils.send(HttpMethod.GET, GlobalConstants.CATEGORY_URL, new RequestCallBack<String>() {

			//请求成功
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				
				String result = responseInfo.result;
				System.out.println("服务器返回的结果："+result);
				
				//解析数据。 使用Gson
				parseDate(result);
				//设置缓存，以URL为key，以Json数据为value，保存缓存。
				CacheUtils.setCache(mActivity, GlobalConstants.CATEGORY_URL, result);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// 打印异常日志
				error.printStackTrace();
				// 向用户发送错误信息
				Toast.makeText(mActivity, msg, 0).show();
			}
		});
	}

	/**
	 * 解析数据
	 * @param json 待解析的数据
	 */
	private void parseDate(String json) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		NewsMenu jsonData = gson.fromJson(json, NewsMenu.class);
		System.out.println("解析结果："+jsonData);
		
		/*
		 * 碎片间的通信：
		 *  如何在主页面碎片中传递数据给侧边栏碎片?
		 *  1.在MainActivity中得到侧边栏碎片的实例
		 *  2.在主页面碎片中关联上MainActivity，从而可以得到侧边栏的实例
		 *  3.调用侧边栏中的方法传递数据
		 */
		MainActivity mainUI = (MainActivity) mActivity;
		LeftMenuFragment leftMenuFragment = mainUI.getLeftMenuFragment();
		//调用侧边栏中的方法传递数据
		leftMenuFragment.setMenuData(jsonData.data);
	}

}
