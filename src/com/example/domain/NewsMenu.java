package com.example.domain;

import java.util.ArrayList;

/**
 * ���ڴ�Ž������Json����
 * ʹ��Gsonʱ�������д���ɣ�
 * 1.��{}�������󣬷�[]��������
 * 2.�����ֶ�����Ҫ��Json�����ֶθ߶�һ�¡�
 *
 */
public class NewsMenu {

	public int retcode;
	
	public ArrayList<Integer> extend;
	
	public ArrayList<NewsMenuData> data;
	
	//������˵�����
	public class NewsMenuData{
		public int id;
		public String title;
		public int type;
		
		public ArrayList<NewsTabData> children;
		
		@Override
		public String toString() {
			return "NewsMenuData [title=" + title + ", children=" + children + "]";
		}
	}
	
	//��ǩҳ�еĶ��� 
	public class NewsTabData{
		public int id;
		public String title;
		public int type;
		public String url;
		@Override
		public String toString() {
			return "NewsTabData [title=" + title + "]";
		}
	}
	@Override
	public String toString() {
		return "NewsMenu [data=" + data + "]";
	}
}
