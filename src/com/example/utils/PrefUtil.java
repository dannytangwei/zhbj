package com.example.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 封装sharedPreference
 * @author Administrator
 *
 */
public class PrefUtil {
	
	/**
	 * 设置boolean型数据：
	 * @param ctx
	 * @param key The name of the preference to modify.
	 * @param value The new value for the preference.
	 */
	public static void setBoolean(Context ctx, String key, Boolean value){
		SharedPreferences sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).commit();
	}
	/**
	 * 取出boolean型数据：
	 * 此方法是static修饰的静态方法，可由类直接调用。
	 * @param ctx 上下文
	 * @param key The name of the preference to retrieve.
	 * @param defValue Value to return if this preference does not exist.当没有数据时，设置一个默认值
	 * @return
	 */
	public static boolean getBoolean(Context ctx, String key, Boolean defValue) {
		// 注意这里需要上下文1.ctx.getSharedPreferences  2.Context.MODE_PRIVATE
		SharedPreferences sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
		return sp.getBoolean(key, defValue);
	}
	
	/**
	 * 设置String型数据：
	 * @param ctx
	 * @param key The name of the preference to modify.为数据设置一个名字（字段名）
	 * @param value The new value for the preference. 要保存的数据
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
	 * 设置int型数据：
	 * @param ctx
	 * @param key The name of the preference to modify.为数据设置一个名字（字段名）
	 * @param value The new value for the preference. 要保存的数据
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
