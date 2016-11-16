package com.example.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * ��װsharedPreference
 * @author Administrator
 *
 */
public class PrefUtil {
	
	/**
	 * ����boolean�����ݣ�
	 * @param ctx
	 * @param key The name of the preference to modify.
	 * @param value The new value for the preference.
	 */
	public static void setBoolean(Context ctx, String key, Boolean value){
		SharedPreferences sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).commit();
	}
	/**
	 * ȡ��boolean�����ݣ�
	 * �˷�����static���εľ�̬������������ֱ�ӵ��á�
	 * @param ctx ������
	 * @param key The name of the preference to retrieve.
	 * @param defValue Value to return if this preference does not exist.��û������ʱ������һ��Ĭ��ֵ
	 * @return
	 */
	public static boolean getBoolean(Context ctx, String key, Boolean defValue) {
		// ע��������Ҫ������1.ctx.getSharedPreferences  2.Context.MODE_PRIVATE
		SharedPreferences sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
		return sp.getBoolean(key, defValue);
	}
	
	/**
	 * ����String�����ݣ�
	 * @param ctx
	 * @param key The name of the preference to modify.Ϊ��������һ�����֣��ֶ�����
	 * @param value The new value for the preference. Ҫ���������
	 */
	public static void setString(Context ctx, String key, String value){
		SharedPreferences sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
		sp.edit().putString(key, value).commit();
	}
	public static String getString(Context ctx, String key, String defValue) {
		SharedPreferences sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
		return sp.getString(key, defValue);
	}

	/**
	 * ����int�����ݣ�
	 * @param ctx
	 * @param key The name of the preference to modify.Ϊ��������һ�����֣��ֶ�����
	 * @param value The new value for the preference. Ҫ���������
	 */
	public static void setInt(Context ctx, String key, String value){
		SharedPreferences sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
		sp.edit().putString(key, value).commit();
	}
	public static int getInt(Context ctx, String key, int defValue) {
		SharedPreferences sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
		return sp.getInt(key, defValue);
	}
}
