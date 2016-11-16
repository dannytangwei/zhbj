package com.example.utils;

import android.content.Context;

public class CacheUtils {

	/**
	 * ����(����)���棺
	 *   ��URLΪkey����Json����Ϊvalue��������sharedPreference
	 * @param ctx
	 * @param url
	 * @param value
	 */
	public static void setCache(Context ctx, String url, String json){
		PrefUtil.setString(ctx, url, json);
	}
	
	/**
	 * ��ȡ����
	 * @param ctx
	 * @param url
	 * @param value
	 */
	public static String getCache(Context ctx, String url){
		return PrefUtil.getString(ctx, url, null);
	}
}
