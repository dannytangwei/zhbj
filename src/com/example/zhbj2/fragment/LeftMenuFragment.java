package com.example.zhbj2.fragment;

import java.util.ArrayList;

import com.example.domain.NewsMenu.NewsMenuData;
import com.example.zhbj2.MainActivity;
import com.example.zhbj2.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 侧边栏fragment：
 *   这是一个Fragment，用于动态加载（覆盖）到MainActivity的侧边栏left_menu。
 *   不用fragment照样也能实现侧边栏，使用fragment是为了解耦！
 *   
 *  步骤：
 *   1.接收Json数据
 *   2. 
 * @author Administrator
 *
 */
public class LeftMenuFragment extends BaseFragment {
	
	private ArrayList<NewsMenuData> jsonData;//服务器传递来的数据，由主页面碎片接收再传递至此。
	private ListView lv_list;
	private int currentMenuPosition = 0;//第一个侧边栏菜单项的位置
	private MyAdapter myAdapter;

	@Override
	public View initView() {
		//第一个参数需要传入一个上下文，这里不能传入this，因为Fragment并不继承Context。
		//通过在基类中调用getActivity得到活动实例,定义为全局变量，这里就可以使用了.
		View view = View.inflate(mActivity1, R.layout.fragment_left_menu, null);
		lv_list = (ListView) view.findViewById(R.id.lv_list);
		return view;
	}

	@Override
	public void initDate() {
		// TODO Auto-generated method stub
		
	}

	//用于接收Json数据，接收了数据后把数据使用ListView设置到屏幕
	public void setMenuData(ArrayList<NewsMenuData> data) {
		// TODO Auto-generated method stub
		jsonData = data;
		myAdapter = new MyAdapter();
		lv_list.setAdapter(myAdapter);
		
		lv_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, 
					int position, long id) {
				// 更新当前被选中的位置
				currentMenuPosition = position;
				myAdapter.notifyDataSetChanged();//刷新ListView 
				
				//关闭侧边栏
				toggle();
			}
		});
	}
	//打开或者关闭侧边栏
	protected void toggle() {
		// TODO Auto-generated method stub
		MainActivity mainUI = (MainActivity) mActivity1;
		SlidingMenu slidingMenu = mainUI.getSlidingMenu();
		//Toggle the SlidingMenu. If it is open, it will be closed, and vice versa
		slidingMenu.toggle();
	}

	class MyAdapter extends BaseAdapter{

		private TextView tv_menu;
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return jsonData.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = View.inflate(mActivity1, R.layout.list_item_left_menu, null);
			tv_menu = (TextView) view.findViewById(R.id.tv_menu);
			tv_menu.setText(jsonData.get(position).title);
			//把第一侧边栏的菜单项设置为可用，即红色字体与图标，其它为白色
			if(position == currentMenuPosition){
				tv_menu.setEnabled(true);
			}else{
				tv_menu.setEnabled(false);
			}
			return view;
		}
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
	}

}
