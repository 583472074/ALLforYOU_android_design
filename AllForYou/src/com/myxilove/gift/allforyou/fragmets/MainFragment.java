package com.myxilove.gift.allforyou.fragmets;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.etsy.android.grid.StaggeredGridView;
import com.myxilove.gift.allforyou.R;
import com.myxilove.gift.allforyou.activities.MainActivity;
import com.myxilove.gift.allforyou.adapter.MyOwnPicAdapter;
import com.myxilove.gift.allforyou.adapter.MyPagerAdapter;
import com.myxilove.gift.allforyou.utils.BitmapPicUtils;
import com.myxilove.gift.allforyou.utils.FixedSpeedScroller;
import com.myxilove.gift.application.BaseApplication;
import com.myxilove.gift.factory.SampleData;
import com.myxilove.gift.model.CodeClass;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

@SuppressLint("ValidFragment")
public class MainFragment extends Fragment implements
		AbsListView.OnItemClickListener {
	// private static final String TAG = "StaggeredGridActivity";
	public static final String SAVED_DATA_KEY = "SAVED_DATA";
	private StaggeredGridView mGridView;
	private boolean mHasRequestedMore;
	private MyOwnPicAdapter mAdapter;
	private ArrayList<String> mData;
	private Context mContext;
	private ViewPager viewPager;
	private String[] titles;
	private List<View> dots;
	private List<ImageView> imageViews;
	private TextView tv_title;
	private int currentItem = 0;
	private ScheduledExecutorService scheduledExecutorService;
	private static double picScale;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	public CodeClass[] str;
	public int flag;
	private int[] imageResId;

	// private Bitmap[] bmArray;

	public MainFragment(Context context, CodeClass[] str, int flag) {
		this.mContext = context;
		this.str = str;
		this.flag = flag;
	}

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			viewPager.setCurrentItem(currentItem, true);
		};
	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		picScale = BitmapPicUtils.getPicScale(getResources(), R.drawable.j_1);

		if (flag == 1) {
			imageResId = new int[] { R.drawable.j_1, R.drawable.j_2,
					R.drawable.j_3, R.drawable.j_4, R.drawable.j_5,
					R.drawable.j_6, R.drawable.j_7 };

			// bmArray = new Bitmap[] {
			// BitmapFactory.decodeResource(mContext.getResources(),
			// R.drawable.one),
			// BitmapFactory.decodeResource(mContext.getResources(),
			// R.drawable.two),
			// BitmapFactory.decodeResource(mContext.getResources(),
			// R.drawable.three),
			// BitmapFactory.decodeResource(mContext.getResources(),
			// R.drawable.four),
			// BitmapFactory.decodeResource(mContext.getResources(),
			// R.drawable.five),
			// BitmapFactory.decodeResource(mContext.getResources(),
			// R.drawable.six),
			// BitmapFactory.decodeResource(mContext.getResources(),
			// R.drawable.seven)

			// };
			titles = new String[imageResId.length];
			titles[0] = "谢谢你们一直都在！";
			titles[1] = "女神是怎么炼成的";
			titles[2] = "园长，园长";
			titles[3] = "敢拍的再风情点么";
			titles[4] = "三十二个赞";
			titles[5] = "很久不见";
			titles[6] = "帅气逼人的九爷";

		} else if (flag == 2) {
			imageResId = new int[] { R.drawable.j_1, R.drawable.j_2,
					R.drawable.j_3, R.drawable.j_4, R.drawable.j_5,
					R.drawable.j_6, R.drawable.j_7 };
			//
			// bmArray = new Bitmap[] {
			// BitmapFactory.decodeResource(mContext.getResources(),
			// R.drawable.j_1),
			// BitmapFactory.decodeResource(mContext.getResources(),
			// R.drawable.j_2),
			// BitmapFactory.decodeResource(mContext.getResources(),
			// R.drawable.j_3),
			// BitmapFactory.decodeResource(mContext.getResources(),
			// R.drawable.j_4),
			// BitmapFactory.decodeResource(mContext.getResources(),
			// R.drawable.j_5),
			// BitmapFactory.decodeResource(mContext.getResources(),
			// R.drawable.j_6),
			// BitmapFactory.decodeResource(mContext.getResources(),
			// R.drawable.j_7) };

			titles = new String[imageResId.length];
			titles[0] = "来自星星的你！";
			titles[1] = "总做着一个很悲伤的梦，梦见你很爱我";
			titles[2] = "啤酒和炸鸡";
			titles[3] = "独角兽，快叫千颂伊回家";
			titles[4] = "离开前，一定要再见你一面";
			titles[5] = "该发生的事情还是会发生的，地球人管这个叫命运。";
			titles[6] = "没有爱情故事怎么能以幸福结局";
		}
		imageViews = new ArrayList<ImageView>();

		for (int i = 0; i < imageResId.length; i++) {
			ImageView imageView = new ImageView(this.getActivity());
			// imageView.setImageResource(imageResId[i]);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			imageViews.add(imageView);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.main_frg_show, container,
				false);

		mGridView = (StaggeredGridView) rootView.findViewById(R.id.grid_view);
		initGridView();
		// mGridView.setSelector(android.R.color.darker_gray);
		// do we have saved data?
		if (savedInstanceState != null) {
			mData = savedInstanceState.getStringArrayList(SAVED_DATA_KEY);
		}
		if (mData == null) {
			mData = SampleData.generateSampleData();
		}

		mAdapter = new MyOwnPicAdapter(MainFragment.this, mContext, str,
				imageLoader);
		mAdapter.notifyDataSetChanged();
		
		SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(mAdapter);
		swingBottomInAnimationAdapter.setAbsListView(mGridView);
		swingBottomInAnimationAdapter.setInitialDelayMillis(450);
		
		mGridView.setAdapter(swingBottomInAnimationAdapter);
		mGridView.setOnScrollListener(new PauseOnScrollListener(imageLoader,
				true, true));
		mGridView.setOnItemClickListener(this);
		return rootView;
	}

	private void initGridView() {
		// TODO Auto-generated method stub
		LayoutInflater layoutInflater = getActivity().getLayoutInflater();
		View header = layoutInflater.inflate(R.layout.viewpaper_image,
				mGridView, false);
		dots = new ArrayList<View>();
		dots.add(header.findViewById(R.id.v_dot0));
		dots.add(header.findViewById(R.id.v_dot1));
		dots.add(header.findViewById(R.id.v_dot2));
		dots.add(header.findViewById(R.id.v_dot3));
		dots.add(header.findViewById(R.id.v_dot4));
		dots.add(header.findViewById(R.id.v_dot5));
		dots.add(header.findViewById(R.id.v_dot6));
		tv_title = (TextView) header.findViewById(R.id.tv_title);
		tv_title.setTypeface(BaseApplication.chineseTypeface);
		tv_title.setText(titles[0]);//
		viewPager = (ViewPager) header.findViewById(R.id.vp);
		RelativeLayout.LayoutParams viewPaLayoutParams = new RelativeLayout.LayoutParams(
				MainActivity.screenWidthDip,
				(int) (MainActivity.screenWidthDip * picScale));
		viewPager.setLayoutParams(viewPaLayoutParams);
		setViewPagerScrollSpeed();
		viewPager.setAdapter(new MyPagerAdapter(imageResId, imageViews,
				getActivity(), flag));
		viewPager.setOnPageChangeListener(new MyPageChangeListener());
		mGridView.addHeaderView(header);
	
	}

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

			Animation animation = new AlphaAnimation(1.0f, 0);
			animation.setDuration(300);
			animation.setInterpolator(new DecelerateInterpolator());
			animation.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationEnd(Animation animation) {
					// TODO Auto-generated method stub
					tv_title.setText(titles[position]);
					Animation animation1 = new AlphaAnimation(0, 1.0f);
					animation1.setDuration(300);
					animation1.setInterpolator(new AccelerateInterpolator());
					tv_title.startAnimation(animation1);
				}
			});
			tv_title.startAnimation(animation);

			dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
			dots.get(position).setBackgroundResource(R.drawable.dot_focused);
			oldPosition = position;

		}

		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
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

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

	// @Override
	// public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void onScrollStateChanged(AbsListView arg0, int arg1) {
	// // TODO Auto-generated method stub
	//
	// }
}
