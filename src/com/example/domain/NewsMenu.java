package com.example.domain;

import java.util.ArrayList;

/**
 * 用于存放解析后的Json数据
 * 使用Gson时，类的书写技巧：
 * 1.逢{}创建对象，逢[]创建集合
 * 2.所有字段名称要和Json返回字段高度一致。
 *
 */
public class NewsMenu {

	public int retcode;
	
	public ArrayList<Integer> extend;
	
	public ArrayList<NewsMenuData> data;
	
	//侧边栏菜单对象
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
	
	//标签页中的对象 
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
