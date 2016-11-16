package com.example.utils;

import android.content.Context;

public class CacheUtils {

	/**
	 * 设置(保存)缓存：
	 *   以URL为key，以Json数据为value，保存在sharedPreference
	 * @param ctx
	 * @param url
	 * @param value
	 */
	public static void setCache(Context ctx, String url, String json){
		PrefUtil.setString(ctx, url, json);
	}
	
	/**
	 * 读取缓存
	 * @param ctx
	 * @param url
	 * @param value
	 */
	public static String getCache(Context ctx, String url){
		return PrefUtil.getString(ctx, url, null);
	}
}
