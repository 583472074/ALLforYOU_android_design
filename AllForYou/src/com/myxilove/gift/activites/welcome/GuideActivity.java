package com.myxilove.gift.activites.welcome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import com.myxilove.gift.allforyou.R;
import com.myxilove.gift.allforyou.activities.LoginActivity;
import com.myxilove.gift.allforyou.utils.ActivityStackControlUtil;

public class GuideActivity extends Activity implements OnPageChangeListener {

	private TextView pageNum;
	private ViewPager vp;
	private List<View> views;
	private ViewPagerAdapter vpAdapter;
	private LayoutInflater inflater;
	private View view2, view3, view4;
	private Bitmap bm1, bm2, bm3, bm4;
	private ImageView view1, image2, image3, image4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		ActivityStackControlUtil.add(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		inflater = LayoutInflater.from(this);
		bm1 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.paper_airplane);
		bm2 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.welcome2);
		bm3 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.welcome3);
		bm4 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.welcome4);
		initViews();
		initPageNum();
	}

	private void initPageNum() {
		pageNum = (TextView) findViewById(R.id.page_num);
		// pageNum.setTypeface(BaseApplication.);
		pageNum.setText("");
	}

	private void initViews() {
		views = new ArrayList<View>();
		view1 = (ImageView) inflater.inflate(R.layout.views_one, null);
		view1.setImageBitmap(bm1);
		view1.setScaleType(ScaleType.FIT_XY);
		view2 = inflater.inflate(R.layout.views_two, null);
		image2 = (ImageView) view2.findViewById(R.id.second_image);
		image2.setImageBitmap(bm2);
		view3 = inflater.inflate(R.layout.views_three, null);
		image3 = (ImageView) view3.findViewById(R.id.third_image);
		image3.setImageBitmap(bm3);
		view4 = inflater.inflate(R.layout.views_four, null);
		image4 = (ImageView) view4.findViewById(R.id.forth_image);
		image4.setImageBitmap(bm4);

		views.add(view1);
		views.add(view2);
		views.add(view3);
		views.add(view4);

		vpAdapter = new ViewPagerAdapter(views, this);

		vp = (ViewPager) findViewById(R.id.viewpager);
		// vp.setPageTransformer(true, new DepthPageTransformer());
		vp.setAdapter(vpAdapter);
		vp.setOnPageChangeListener(this);
	}

	public class ViewPagerAdapter extends PagerAdapter {

		private List<View> views;
		private Activity activity;

		public ViewPagerAdapter(List<View> views, Activity activity) {
			this.views = views;
			this.activity = activity;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			Log.e("Viewpaper", "销毁" + arg1);
			((ViewPager) arg0).removeView(views.get(arg1));
		}

		@Override
		public int getCount() {

			if (views != null) {
				return views.size();
			}

			return 0;
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(views.get(arg1), 0);
			if (arg1 == 0) {
				// AnimationSet animationSet = new AnimationSet(true);
				// Animation alphaAnimation = AnimationUtils.loadAnimation(
				// GuideActivity.this, R.anim.alpha);
				// Animation tAnimation = AnimationUtils.loadAnimation(
				// GuideActivity.this, R.anim.trans);
				// animationSet.addAnimation(alphaAnimation);
				// animationSet.addAnimation(tAnimation);
				// ImageView imageView = (ImageView) arg0
				// .findViewById(R.id.first_image);
				// imageView.startAnimation(animationSet);
			}
			if (arg1 == views.size() - 1) {
				Button mStart = (Button) arg0.findViewById(R.id.mstart);

				mStart.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						setGuided();
						goHome();
					}
				});
			}
			return views.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return (arg0 == arg1);
		}

		public void goHome() {
			Intent intent = new Intent(activity, LoginActivity.class);
			activity.startActivity(intent);
			overridePendingTransition(android.R.anim.fade_in,
					android.R.anim.fade_out);
			activity.finish();
		}

		public void setGuided() {
			SharedPreferences preferences = activity.getSharedPreferences(
					"first_pref", Context.MODE_PRIVATE);
			Editor editor = preferences.edit();
			editor.putBoolean("isFirst", false);
			editor.commit();
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		view1.setImageBitmap(null);
		image2.setImageBitmap(null);
		image3.setImageBitmap(null);
		image4.setImageBitmap(null);
		bm1.recycle();
		bm2.recycle();
		bm3.recycle();
		bm4.recycle();
		super.onDestroy();

	}
}
