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
 * �����fragment��
 *   ����һ��Fragment�����ڶ�̬���أ����ǣ���MainActivity�Ĳ����left_menu��
 *   ����fragment����Ҳ��ʵ�ֲ������ʹ��fragment��Ϊ�˽��
 *   
 *  ���裺
 *   1.����Json����
 *   2. 
 * @author Administrator
 *
 */
public class LeftMenuFragment extends BaseFragment {
	
	private ArrayList<NewsMenuData> jsonData;//�����������������ݣ�����ҳ����Ƭ�����ٴ������ˡ�
	private ListView lv_list;
	private int currentMenuPosition = 0;//��һ��������˵����λ��
	private MyAdapter myAdapter;

	@Override
	public View initView() {
		//��һ��������Ҫ����һ�������ģ����ﲻ�ܴ���this����ΪFragment�����̳�Context��
		//ͨ���ڻ����е���getActivity�õ��ʵ��,����Ϊȫ�ֱ���������Ϳ���ʹ����.
		View view = View.inflate(mActivity1, R.layout.fragment_left_menu, null);
		lv_list = (ListView) view.findViewById(R.id.lv_list);
		return view;
	}

	@Override
	public void initDate() {
		// TODO Auto-generated method stub
		
	}

	//���ڽ���Json���ݣ����������ݺ������ʹ��ListView���õ���Ļ
	public void setMenuData(ArrayList<NewsMenuData> data) {
		// TODO Auto-generated method stub
		jsonData = data;
		myAdapter = new MyAdapter();
		lv_list.setAdapter(myAdapter);
		
		lv_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, 
					int position, long id) {
				// ���µ�ǰ��ѡ�е�λ��
				currentMenuPosition = position;
				myAdapter.notifyDataSetChanged();//ˢ��ListView 
				
				//�رղ����
				toggle();
			}
		});
	}
	//�򿪻��߹رղ����
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
			//�ѵ�һ������Ĳ˵�������Ϊ���ã�����ɫ������ͼ�꣬����Ϊ��ɫ
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
