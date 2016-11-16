package com.example.zhbj2;
import com.example.utils.PrefUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.RelativeLayout;

public class SpalshActivity extends Activity {
	private RelativeLayout rl_splash_anim;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		rl_splash_anim = (RelativeLayout) findViewById(R.id.rl_splash_anim);
		
		//-------------------------为Spalsh界面设置动画-----------------------------------
		//旋转动画
		RotateAnimation rotateAnim = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rotateAnim.setDuration(1500);
		rotateAnim.setFillAfter(true);//设置动画停留在结束的位置
		//缩放动画
		ScaleAnimation scaleAnim = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnim.setDuration(1500);
		scaleAnim.setFillAfter(true);
		//渐变动画
		AlphaAnimation alphaAnim = new AlphaAnimation(0, 1);
		alphaAnim.setDuration(2500);
		alphaAnim.setFillAfter(true);
		//动画集合
		AnimationSet animationSet = new AnimationSet(true);
		animationSet.addAnimation(rotateAnim);
		animationSet.addAnimation(scaleAnim);
		animationSet.addAnimation(alphaAnim);
		//启动动画
		rl_splash_anim.startAnimation(animationSet);
		
		//-------------------------为Spalsh界面设置新手引导---------------------------------
		animationSet.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			//动画结束时，跳转页面
			@Override
			public void onAnimationEnd(Animation animation) {
				
				//SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
				//boolean isFirstEnter = sp.getBoolean("is_first_enter", true);已进行封装
				//调用封装好的PrefUtil类，当没有数据时默认为true。
				boolean isFirstEnter = PrefUtil.getBoolean(SpalshActivity.this, "is_first_enter", true);
				Intent intent;
				if(isFirstEnter){
					//当没有已保存的数据时，创建新手引导。
					intent = new Intent(SpalshActivity.this, GuideActivity.class);
				}else{
					//当有数据时，跳转主页面。
					intent = new Intent(SpalshActivity.this, MainActivity.class);
				}
				startActivity(intent);
				finish();//销毁当前界面
			}
		});
	}
}
