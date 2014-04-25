package com.myxilove.gift.allforyou.fragmets;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.myxilove.gift.allforyou.R;
import com.myxilove.gift.allforyou.activities.DetailsActivity;
import com.myxilove.gift.allforyou.activities.MainActivity;
import com.myxilove.gift.allforyou.adapter.BigImageAdapter;
import com.myxilove.gift.allforyou.adapter.MyPagerAdapter;
import com.myxilove.gift.allforyou.utils.BitmapPicUtils;
import com.myxilove.gift.allforyou.utils.FixedSpeedScroller;
import com.myxilove.gift.application.BaseApplication;
import com.myxilove.gift.model.CodeClass;
import com.nhaarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.ScaleInAnimationAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

@SuppressLint("ValidFragment")
public class ListViewFragment extends Fragment {

	private View rootView;
	private ListView listView;
	private Context context;
	private CodeClass[] urls;
	private ViewPager viewPager;
	private String[] titles;
	private int[] imageResId;
	private List<View> dots;
	private List<ImageView> imageViews;
	private TextView tv_title;
	private int currentItem = 0;
	private ScheduledExecutorService scheduledExecutorService;
	private static double picScale;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private BigImageAdapter adpater;
	private Animator mCurrentAnimator;

	// private Bitmap[] bmArray;

	public ListViewFragment(Context context, CodeClass[] urls) {
		this.context = context;
		this.urls = urls;
		tf = BaseApplication.chineseTypeface;
	}

	private String[] mHeaderNames = { "Cute Cats", "Few Cats", "Some Cats",
			"Some More Cats", "Some More More Cats", "Many Cats",
			"Many Many Cats", "So Many Cats" };
	private Integer[] mHeaderPositions = { 0, 6, 11, 37, 38, 60, 77, 89 };
	Typeface tf;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		picScale = BitmapPicUtils.getPicScale(getResources(), R.drawable.j_1);

		imageResId = new int[] { R.drawable.j_5, R.drawable.j_1,
				R.drawable.j_2, R.drawable.j_3, R.drawable.j_4, };

		Context mContext = getActivity();
		// bmArray = new Bitmap[] {
		// BitmapFactory.decodeResource(mContext.getResources(),
		// R.drawable.j_5),
		// BitmapFactory.decodeResource(mContext.getResources(),
		// R.drawable.two),
		// BitmapFactory.decodeResource(mContext.getResources(),
		// R.drawable.three),
		// BitmapFactory.decodeResource(mContext.getResources(),
		// R.drawable.four),
		// BitmapFactory.decodeResource(mContext.getResources(),
		// R.drawable.five) };
		titles = new String[imageResId.length];
		titles[0] = "来自星星的你";
		titles[1] = "女神是怎么炼成的";
		titles[2] = "园长，园长";
		titles[3] = "敢拍的再风情点么";
		titles[4] = "三十二个赞";
		imageViews = new ArrayList<ImageView>();

		for (int i = 0; i < imageResId.length; i++) {
			ImageView imageView = new ImageView(this.getActivity());
			imageView.setImageResource(imageResId[i]);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			imageViews.add(imageView);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		rootView = inflater.inflate(R.layout.pic_listview, container, false);
		initListView(rootView);
		return rootView;
	}

	private void initListView(View view) {
		// TODO Auto-generated method stub
		listView = (ListView) view.findViewById(R.id.list);
		LayoutInflater layoutInflater = getActivity().getLayoutInflater();
		View header = layoutInflater.inflate(R.layout.viewpaper_listimage,
				listView, false);
		dots = new ArrayList<View>();
		dots.add(header.findViewById(R.id.v_dot0));
		dots.add(header.findViewById(R.id.v_dot1));
		dots.add(header.findViewById(R.id.v_dot2));
		dots.add(header.findViewById(R.id.v_dot3));
		dots.add(header.findViewById(R.id.v_dot4));
		tv_title = (TextView) header.findViewById(R.id.tv_title);
		tv_title.setTypeface(tf);
		tv_title.setText(titles[0]);//
		viewPager = (ViewPager) header.findViewById(R.id.vp);
		RelativeLayout.LayoutParams viewPaLayoutParams = new RelativeLayout.LayoutParams(
				MainActivity.screenWidthDip,
				(int) (MainActivity.screenWidthDip * picScale));
		viewPager.setLayoutParams(viewPaLayoutParams);
		setViewPagerScrollSpeed();
		viewPager.setAdapter(new MyPagerAdapter(imageResId, imageViews,
				getActivity(), 1));
		viewPager.setOnPageChangeListener(new MyPageChangeListener());
		adpater = new BigImageAdapter(ListViewFragment.this, urls, imageLoader);
		listView.addHeaderView(header);
		AnimationAdapter animAdapter = new ScaleInAnimationAdapter(adpater);
		animAdapter.setAbsListView(listView);
		animAdapter.setInitialDelayMillis(300);
		listView.setAdapter(animAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, DetailsActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("Key", urls[position % urls.length].getValue());
				intent.putExtras(bundle);
				context.startActivity(intent);
				((Activity) context).overridePendingTransition(R.anim.alpha,
						R.anim.alpha2);
			}
		});
		listView.setOnScrollListener((new PauseOnScrollListener(imageLoader,
				true, true)));
	}

	/**
	 * 设置ViewPager的滑动速度
	 * 
	 * */
	private void setViewPagerScrollSpeed() {
		try {
			Field mScroller = null;
			mScroller = ViewPager.class.getDeclaredField("mScroller");
			mScroller.setAccessible(true);
			FixedSpeedScroller scroller = new FixedSpeedScroller(
					viewPager.getContext());
			mScroller.set(viewPager, scroller);
		} catch (NoSuchFieldException e) {

		} catch (IllegalArgumentException e) {

		} catch (IllegalAccessException e) {

		}
	}

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			viewPager.setCurrentItem(currentItem, true);
		};
	};

	@Override
	public void onStart() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 3, 5,
				TimeUnit.SECONDS);
		super.onStart();
	}

	@Override
	public void onStop() {
		scheduledExecutorService.shutdown();
		super.onStop();
	}

	/**
	 * 
	 * @author Administrator
	 * 
	 */
	private class ScrollTask implements Runnable {

		public void run() {
			synchronized (viewPager) {
				currentItem = (currentItem + 1) % imageViews.size();
				handler.obtainMessage().sendToTarget();
			}
		}

	}

	private class MyPageChangeListener implements OnPageChangeListener {
		private int oldPosition = 0;

		/**
		 * This method will be invoked when a new page becomes selected.
		 * position: Position index of the new selected page.
		 */
		public void onPageSelected(final int position) {
			currentItem = position;

			AnimatorSet set = new AnimatorSet();
			set.play(ObjectAnimator.ofFloat(tv_title, View.ALPHA, 1.0f, 0.0f))
					.with(ObjectAnimator.ofFloat(tv_title, View.SCALE_X, 1.0f,
							0.5f))
					.with(ObjectAnimator.ofFloat(tv_title, View.SCALE_Y, 1.0f,
							0.5f));

			set.setDuration(300);
			set.setInterpolator(new DecelerateInterpolator());
			set.addListener(new AnimatorListener() {

				@Override
				public void onAnimationCancel(Animator arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationEnd(Animator arg0) {
					// TODO Auto-generated method stub
					mCurrentAnimator = null;
					AnimatorSet set = new AnimatorSet();
					tv_title.setText(titles[position]);
					set.play(
							ObjectAnimator.ofFloat(tv_title, View.ALPHA, 0.0f,
									1.0f))
							.with(ObjectAnimator.ofFloat(tv_title,
									View.SCALE_X, 0.5f, 1.0f))
							.with(ObjectAnimator.ofFloat(tv_title,
									View.SCALE_Y, 0.5f, 1.0f));
					set.setDuration(300);
					set.setInterpolator(new DecelerateInterpolator());
					set.start();
				}

				@Override
				public void onAnimationRepeat(Animator arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationStart(Animator arg0) {
					// TODO Auto-generated method stub

				}

			});
			set.start();
			mCurrentAnimator = set;
			dots.get(oldPosition).setBackgroundResource(R.drawable.line_normal);
			dots.get(position).setBackgroundResource(R.drawable.line_focused);
			oldPosition = position;

		}

		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
	}

}
