package com.example.zhbj2;
import com.example.utils.PrefUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

public class GuideActivity extends Activity {

	private ViewPager vp_guide;
	private int[] imageResIds;//存放图片资源ID的数组
	private LinearLayout ll_guide;
	private ImageView redPoint; //小红点
	private int pointDistance;//两个小圆点之间的距离
	private Button btn_guide;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		
		initViews();
		initModel();
		initControler();
		//为了使用户体验更好，设置小圆点跟随页面的滑动而左右移动
		vp_guide.setOnPageChangeListener(new OnPageChangeListener() {
			
			// 新的页面被选中
			@Override
			public void onPageSelected(int position) {
				if(position == (imageResIds.length-1)){
					//滑动到最后一个页面时使按钮可见
					btn_guide.setVisibility(View.VISIBLE);
				}else{//避免滑动到最后一个页面，再往回划到之前的页面时按钮任然在。
					btn_guide.setVisibility(View.INVISIBLE);
				}
			}
			
			// 当页面滑动过程中的回调
			//参数positionOffset：页面移动偏移量的百分比
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				System.out.println("当前条目位置"+position+";偏移百分比："+positionOffset);
				//小红点的移动距离=两个圆点间的距离*偏移百分比
				//避免滑动后一松手红点又会到原点，所以加上position*pointDistance
				int dis = (int) (pointDistance * positionOffset) + position*pointDistance;
				//获取布局参数
				RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) redPoint.getLayoutParams();
				//修改leftMargin来更新小小圆点的位置
				params.leftMargin = dis;
				//把具有新leftMargin的参数设置到布局中
				redPoint.setLayoutParams(params);
			}
			
			// 当页面状态发生变化时回调
			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});
		
		btn_guide.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//此时引导界面已结束，设置sharedPreference中的boolean值，
				//只有第一次进入程序才开启引导界面，以后将不再进入引导界面。
				PrefUtil.setBoolean(getApplicationContext(), "is_first_enter", false);
				startActivity(new Intent(GuideActivity.this, MainActivity.class));
				finish();
			}
		});

	}

	private void initViews() {
		vp_guide = (ViewPager) findViewById(R.id.vp_guide);
		ll_guide = (LinearLayout) findViewById(R.id.ll_guide);
		redPoint = (ImageView) findViewById(R.id.iv_guide_redpoint);
		btn_guide = (Button) findViewById(R.id.btn_guide);
	}

	private void initModel() {
		//---------------------设置ViewPager中的图片-----------------------------------------
        imageResIds = new int[] {R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
        
        //---------------------设置灰色小圆点------------------------------------
        ImageView pointView ;  //放到循环外部，节省栈内存（引用变量）。
        for (int i = 0; i < imageResIds.length; i++) {
            pointView = new ImageView(getApplicationContext());
            //这里使用setImageResource来设置图片，使之包裹内容。
            pointView.setImageResource(R.drawable.shape_guide_grypoint);
            //注意：各种布局中都有LayoutParams,所以得选择相应的布局。
            LayoutParams params = new LinearLayout.LayoutParams(
            		LinearLayout.LayoutParams.WRAP_CONTENT,
            		LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i != 0)
            	//设置左边距需要一个LayoutParams(布局参数)
            	params.leftMargin = 10;
            pointView.setEnabled(false);
//            pointView.setLayoutParams(params);  
//            ll_guide.addView(pointView);   也可以使用下面的简写方法
            ll_guide.addView(pointView, params);//使用具有LayoutParams参数的方法
        }
        //---------------------计算灰色圆点之间的距离--------------------------------------
		//在控件(小圆点)被绘制出来后，才能计算圆点间的距离。
		//控件的绘制流程： measure->layout（确定位置）->draw,注意，要在activity的onCreate方法执行之后才
		//会执行此绘制流程。
		redPoint.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			//layout方法（确定位置）执行完毕后回调
			@Override
			public void onGlobalLayout() { 
				//当绘制完成后移除监听，避免重复回调此方法
				redPoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				//第二个小圆点的左边距减去第一个小圆点的左边距
				pointDistance = ll_guide.getChildAt(1).getLeft() - ll_guide.getChildAt(0).getLeft();
				System.out.println("两个小圆点之间的距离"+pointDistance);
			}
		});		
		
	}

	private void initControler() {
		// TODO Auto-generated method stub
		vp_guide.setAdapter(new MyAdapter());
	}
	private class MyAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imageResIds.length;
		}

		//判断是否可以复用
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
		
		// 返回要显示的条目内容
        // 参数container:就是ViewPager
		ImageView imageView;
		//可以在initModel()中创建数组时就创建ImageView，然后再add到集合中。结果都一样
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			int i = imageResIds[position];
			imageView = new ImageView(getApplicationContext());
			imageView.setBackgroundResource(i);//通过设置背景，可以使图片填充布局。
			//imageView.setImageResource(i);这里就不能填充布局，导致图片与屏幕之间有空白处
			container.addView(imageView);// 把要显示的条目添加到container
			return imageView;// 返回条目到显示界面
		}
		
		// 销毁不可见的条目：默认初始化屏幕所显示图片的前一张和后一张，其它的会销毁。
        // 参数object:要销毁的对象
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
		
		
		
	}
}
