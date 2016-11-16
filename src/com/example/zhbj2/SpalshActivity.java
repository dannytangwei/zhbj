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
		
		//-------------------------ΪSpalsh�������ö���-----------------------------------
		//��ת����
		RotateAnimation rotateAnim = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rotateAnim.setDuration(1500);
		rotateAnim.setFillAfter(true);//���ö���ͣ���ڽ�����λ��
		//���Ŷ���
		ScaleAnimation scaleAnim = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnim.setDuration(1500);
		scaleAnim.setFillAfter(true);
		//���䶯��
		AlphaAnimation alphaAnim = new AlphaAnimation(0, 1);
		alphaAnim.setDuration(2500);
		alphaAnim.setFillAfter(true);
		//��������
		AnimationSet animationSet = new AnimationSet(true);
		animationSet.addAnimation(rotateAnim);
		animationSet.addAnimation(scaleAnim);
		animationSet.addAnimation(alphaAnim);
		//��������
		rl_splash_anim.startAnimation(animationSet);
		
		//-------------------------ΪSpalsh����������������---------------------------------
		animationSet.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			//��������ʱ����תҳ��
			@Override
			public void onAnimationEnd(Animation animation) {
				
				//SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
				//boolean isFirstEnter = sp.getBoolean("is_first_enter", true);�ѽ��з�װ
				//���÷�װ�õ�PrefUtil�࣬��û������ʱĬ��Ϊtrue��
				boolean isFirstEnter = PrefUtil.getBoolean(SpalshActivity.this, "is_first_enter", true);
				Intent intent;
				if(isFirstEnter){
					//��û���ѱ��������ʱ����������������
					intent = new Intent(SpalshActivity.this, GuideActivity.class);
				}else{
					//��������ʱ����ת��ҳ�档
					intent = new Intent(SpalshActivity.this, MainActivity.class);
				}
				startActivity(intent);
				finish();//���ٵ�ǰ����
			}
		});
	}
}
