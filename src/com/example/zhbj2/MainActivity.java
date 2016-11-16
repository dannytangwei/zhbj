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
 * ˼·��
 *  һ.Spalsh����
 *  ��.Guide����
 *  ��.������MainActivity
 *    a.���ò�������
 *     1.ʹ�ÿ�Դ�� slidingMenu
 *     2.�̳���SlidingFragmentActivity
 *     3.��onCreate�����η���Ϊpublic����ΪSlidingFragmentActivity�����η���public
 *     4.�������API 
 *    b.��̬���أ����ǣ�fragment��MainActivity�Ĳ��������ҳ��
 *  ��.������ҳ����ƬContentFragment����
 *      
 *  ��.���ò������ƬLeftMenuFragment�Ĳ���
 *       
 *
 */
public class MainActivity extends SlidingFragmentActivity {

	private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";//fragment�ı��
	private static final String TAG_CONTENT = "TAG_CONTENT";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		//�������˵�
		setBehindContentView(R.layout.left_menu);
		SlidingMenu slidingMenu = getSlidingMenu();
		//����ȫ���ɴ�������ʹ���˵�����
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setBehindOffset(200);//��ĻԤ��200���صĿ��
		
		initFragment();
	}
    
	/**
	 * ��̬�����Ƭ��
	 *  1.����fragmentʵ��
	 *  2.��ȡ��FragmentManager
	 *  3.����beginTransaction������������
	 *  4.ʹ��replace()�����򲼾������ڼ�����Ƭ����fragment����֡���֡�
	 *  5.����commit���������ύ����
	 *  
	 * ע�⣺
	 *   getSupportFragmentManager();����4.0֮ǰ�İ汾
	 *   getFragmentManager();������4.0֮ǰ�İ汾
	 */
	private void initFragment(){
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		//����һ��FramLyaout����������id,�������������fragmentʵ��
		//����������ǣ�����ͨ������ҵ���Ӧ��fragment
		transaction.replace(R.id.fl_left_menu, new LeftMenuFragment(), TAG_LEFT_MENU);
		transaction.replace(R.id.fl_content, new ContentFragment(), TAG_CONTENT);
		transaction.commit();
	}
	
	/**
	 * �ڻ��ManiActivity���л�ȡ�������Ƭ��ʵ����
	 *   ͨ��ʵ���Ӷ����Ե�����Ƭ��ķ����ˡ�
	 * @return 
	 */
	public LeftMenuFragment getLeftMenuFragment(){
		FragmentManager fm = getSupportFragmentManager();
		//ע��������LeftMenuFragment
		LeftMenuFragment leftFragment = (LeftMenuFragment) fm.findFragmentByTag(TAG_LEFT_MENU);
		return leftFragment;
	}
}
