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
	private int[] imageResIds;//���ͼƬ��ԴID������
	private LinearLayout ll_guide;
	private ImageView redPoint; //С���
	private int pointDistance;//����СԲ��֮��ľ���
	private Button btn_guide;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		
		initViews();
		initModel();
		initControler();
		//Ϊ��ʹ�û�������ã�����СԲ�����ҳ��Ļ����������ƶ�
		vp_guide.setOnPageChangeListener(new OnPageChangeListener() {
			
			// �µ�ҳ�汻ѡ��
			@Override
			public void onPageSelected(int position) {
				if(position == (imageResIds.length-1)){
					//���������һ��ҳ��ʱʹ��ť�ɼ�
					btn_guide.setVisibility(View.VISIBLE);
				}else{//���⻬�������һ��ҳ�棬�����ػ���֮ǰ��ҳ��ʱ��ť��Ȼ�ڡ�
					btn_guide.setVisibility(View.INVISIBLE);
				}
			}
			
			// ��ҳ�滬�������еĻص�
			//����positionOffset��ҳ���ƶ�ƫ�����İٷֱ�
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				System.out.println("��ǰ��Ŀλ��"+position+";ƫ�ưٷֱȣ�"+positionOffset);
				//С�����ƶ�����=����Բ���ľ���*ƫ�ưٷֱ�
				//���⻬����һ���ֺ���ֻᵽԭ�㣬���Լ���position*pointDistance
				int dis = (int) (pointDistance * positionOffset) + position*pointDistance;
				//��ȡ���ֲ���
				RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) redPoint.getLayoutParams();
				//�޸�leftMargin������ССԲ���λ��
				params.leftMargin = dis;
				//�Ѿ�����leftMargin�Ĳ������õ�������
				redPoint.setLayoutParams(params);
			}
			
			// ��ҳ��״̬�����仯ʱ�ص�
			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});
		
		btn_guide.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//��ʱ���������ѽ���������sharedPreference�е�booleanֵ��
				//ֻ�е�һ�ν������ſ����������棬�Ժ󽫲��ٽ����������档
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
		//---------------------����ViewPager�е�ͼƬ-----------------------------------------
        imageResIds = new int[] {R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
        
        //---------------------���û�ɫСԲ��------------------------------------
        ImageView pointView ;  //�ŵ�ѭ���ⲿ����ʡջ�ڴ棨���ñ�������
        for (int i = 0; i < imageResIds.length; i++) {
            pointView = new ImageView(getApplicationContext());
            //����ʹ��setImageResource������ͼƬ��ʹ֮�������ݡ�
            pointView.setImageResource(R.drawable.shape_guide_grypoint);
            //ע�⣺���ֲ����ж���LayoutParams,���Ե�ѡ����Ӧ�Ĳ��֡�
            LayoutParams params = new LinearLayout.LayoutParams(
            		LinearLayout.LayoutParams.WRAP_CONTENT,
            		LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i != 0)
            	//������߾���Ҫһ��LayoutParams(���ֲ���)
            	params.leftMargin = 10;
            pointView.setEnabled(false);
//            pointView.setLayoutParams(params);  
//            ll_guide.addView(pointView);   Ҳ����ʹ������ļ�д����
            ll_guide.addView(pointView, params);//ʹ�þ���LayoutParams�����ķ���
        }
        //---------------------�����ɫԲ��֮��ľ���--------------------------------------
		//�ڿؼ�(СԲ��)�����Ƴ����󣬲��ܼ���Բ���ľ��롣
		//�ؼ��Ļ������̣� measure->layout��ȷ��λ�ã�->draw,ע�⣬Ҫ��activity��onCreate����ִ��֮���
		//��ִ�д˻������̡�
		redPoint.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			//layout������ȷ��λ�ã�ִ����Ϻ�ص�
			@Override
			public void onGlobalLayout() { 
				//��������ɺ��Ƴ������������ظ��ص��˷���
				redPoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				//�ڶ���СԲ�����߾��ȥ��һ��СԲ�����߾�
				pointDistance = ll_guide.getChildAt(1).getLeft() - ll_guide.getChildAt(0).getLeft();
				System.out.println("����СԲ��֮��ľ���"+pointDistance);
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

		//�ж��Ƿ���Ը���
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
		
		// ����Ҫ��ʾ����Ŀ����
        // ����container:����ViewPager
		ImageView imageView;
		//������initModel()�д�������ʱ�ʹ���ImageView��Ȼ����add�������С������һ��
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			int i = imageResIds[position];
			imageView = new ImageView(getApplicationContext());
			imageView.setBackgroundResource(i);//ͨ�����ñ���������ʹͼƬ��䲼�֡�
			//imageView.setImageResource(i);����Ͳ�����䲼�֣�����ͼƬ����Ļ֮���пհ״�
			container.addView(imageView);// ��Ҫ��ʾ����Ŀ��ӵ�container
			return imageView;// ������Ŀ����ʾ����
		}
		
		// ���ٲ��ɼ�����Ŀ��Ĭ�ϳ�ʼ����Ļ����ʾͼƬ��ǰһ�źͺ�һ�ţ������Ļ����١�
        // ����object:Ҫ���ٵĶ���
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
		
		
		
	}
}
