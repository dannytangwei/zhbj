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
 * ��ҳ����ƬContentFragment��
 *   ���ڶ�̬���أ����ǣ���MainActivity����ҳ��activity_main��
 *   
 * 1.����fragment_content.xml����
 *   a.��ҳ��ַ�Ϊ2��飬������ViewPager����������ΪRadioGroup
 *   b.ʵ��RadioGroup�����е�5����ǩ
 *   c.����һ���������һ����Զ���ViewPager���ǵ�Ҫʹ��ȫ������
 * 2.����ContentFragment��̳���BaseFragment���࣬����fragment_content.xml����
 * 3.����5����ǩҳ�棨view����
 *   a.����һ��XML�����ļ�ģ�塣��ǩҳ����ϸ��Ϊ��������FrameLayout
 *   b.������ǩҳ��Ļ��ࣨ��ҳ���б��������Թ��ã���ʵ���ˣ�FrameLayout������ͬ����������ʵ�֣�
 *   c.������������Activity������ʹ���ڴ���ҳ��ʵ��ʱ����Activityʵ��
 *   d.��ʼ����ͼview(���ڸ�ViewPager��ʾ����Ļ��)��
 *     ��������ʵ�֣����ҵ�������title�ؼ���ʵ������ҳ������������޸�title��ֵ
 *     FrameLayout����Ϊ�գ�Ҳ�ҵ�������ʵ������ҳ�����ൽʱ������������ݽ�ȥ
 *   e.��view����Ϊȫ�ֱ���mRootView����������á�
 * 4.�ѱ�ǩҳ����ӵ��������������õ�ViewPager
 *   a.����5����ǩҳ�����ʵ����ӵ����ϡ�
 *   b.
 *   c.
 *
 */
public class ContentFragment extends BaseFragment {

	private NoScrollViewPager vp_content;
	private ArrayList<BasePager> mPagers;//5����ǩҳ�ļ���
	private RadioGroup rg_group;

	
	@Override
	public View initView() {
		//����fragment_content.xml����
		View view = View.inflate(mActivity1, R.layout.fragment_content, null);
		//��ȡ��ͼ��ViewPager��ʵ��
		vp_content = (NoScrollViewPager) view.findViewById(R.id.vp_fragment_content);
		rg_group = (RadioGroup) view.findViewById(R.id.rg_group);
		return view;
	}

	@Override
	public void initDate() {
		mPagers = new ArrayList<BasePager>();
		// ���ø���Ĺ��췽������activity������5����ǩҳ�����ʵ��(�Ⲣ������ͼ��ֻ��һ�����ʵ��)
		 //����5����ǩҳ���ʵ������ӵ����ϡ�
		mPagers.add(new HomePager(mActivity1));
		mPagers.add(new NewsContentPager(mActivity1));
		mPagers.add(new SmartServicePager(mActivity1));
		mPagers.add(new GovAffairsPager(mActivity1));
		mPagers.add(new SettingPager(mActivity1));
		
		vp_content.setAdapter(new ContentAdapter());
		
		//������ǩ�л�����
		rg_group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.rb_fragment_home:
					//�л�����һ����Ŀ��ͼ��false��ʾû�ж�����
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
			
			//�л���ĳҳ��ʱ����
			@Override
			public void onPageSelected(int position) {
				// ҳ�汻ѡ��ʱ�ŵ���initDate����
				BasePager pager = mPagers.get(position);
				pager.initDate();
				if(position == 0 || position == mPagers.size()-1){
					//��ҳ������ҳ��Ҫ���ò����	
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
		//�ֶ����ص�һҳ�����ݡ�
		//��Ϊ�ս������ʱû���л�/ѡ��ҳ�棬�Ժ��û��л�����ҳ�ͼ�����ҳ�����ݡ�
		mPagers.get(0).initDate();
		//��ҳ���ò����
		setSlidingMenuEnable(false);
	}
	
    //��������ò����
	protected void setSlidingMenuEnable(boolean enable) {
		// ͨ��MainActivity�����ȡ���������
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
		
		//����Ҫ��ʾ����Ŀ
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// �õ�����ʾ��ҳ�����ʵ��
			BasePager pager = mPagers.get(position);
			//�õ���ͼ
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
