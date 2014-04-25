package com.myxilove.gift.allforyou.activities;

import com.astuetz.PagerSlidingTabStrip;
import com.myxilove.gift.allforyou.R;
import com.myxilove.gift.allforyou.adapter.MyOwnAdapter;
import com.myxilove.gift.allforyou.fragmets.DrawerFragment;
import com.myxilove.gift.allforyou.utils.ActionSheet;
import com.myxilove.gift.allforyou.utils.ActivityStackControlUtil;
import com.myxilove.gift.allforyou.utils.ActionSheet.OnActionSheetSelected;
import com.myxilove.gift.application.BaseApplication;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity implements
		OnActionSheetSelected, OnCancelListener {
	private ViewPager contentPager;
	private DrawerLayout mDrawerLayout;

	private ActionBarDrawerToggle mActionBarDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private FrameLayout mFrameLayout;
	private String[] mPlanetTitles;
	private MyOwnAdapter mAdapter;
	private PagerSlidingTabStrip tabs;
	public static float density;
	public static float xdpi;
	public static float ydpi;
	public static float screenWidth;
	public static float screenHeight;
	public static float densityDPI;
	public static int screenWidthDip;
	public static int screenHeightDip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityStackControlUtil.add(this);
		getDisplayDp();
		setContentView(R.layout.activity_main);
		setPager();
		setActionBarStyle();
		mTitle = mDrawerTitle = getTitle();
		mPlanetTitles = getResources().getStringArray(R.array.planets_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.content_drawer);
		mFrameLayout = (FrameLayout) findViewById(R.id.left_drawer);
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.left_drawer,
						new DrawerFragment(MainActivity.this)).commit();
		mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerClosed(drawerView);
				getActionBar().setTitle(mTitle);
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerOpened(drawerView);
				getActionBar().setTitle(mDrawerTitle);
			}
		};
		mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

		// if (savedInstanceState == null) {
		// selectItem(0);
		// }

	}

	private void setActionBarStyle() {
		// TODO Auto-generated method stub
		this.getActionBar().setTitle("AllForYou");
		getActionBar().setBackgroundDrawable(
				this.getBaseContext().getResources()
						.getDrawable(R.drawable.actionbar_back));
		getActionBar().setIcon(R.drawable.ic_launcher);
		getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		int titleId = Resources.getSystem().getIdentifier("action_bar_title",
				"id", "android");
		TextView textView = (TextView) findViewById(titleId);
		Typeface typeface = Typeface.createFromAsset(getAssets(),
				"font/Wendy.ttf");
		textView.setTypeface(typeface);
		textView.setTextColor(0xFFdfdfdf);
		textView.setTextSize(32);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
	}

	private void setPager() {
		// TODO Auto-generated method stub
		contentPager = (ViewPager) findViewById(R.id.content_pager);
		mAdapter = new MyOwnAdapter(getSupportFragmentManager(),
				MainActivity.this);
		contentPager.setAdapter(mAdapter);
		tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		tabs.setViewPager(contentPager);
	}

	// private void selectItem(int i) {
	// // TODO Auto-generated method stub
	// // Fragment
	// }

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }

	// @Override
	// public boolean onPrepareOptionsMenu(Menu menu) {
	// // TODO Auto-generated method stub
	// return super.onPrepareOptionsMenu(menu);
	// }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// TODO Auto-generated method stub
		mActionBarDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		mActionBarDrawerToggle.onConfigurationChanged(newConfig);
	}

	private void getDisplayDp() {
		DisplayMetrics dm = new DisplayMetrics();
		dm = getResources().getDisplayMetrics();
		dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
		densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
		xdpi = dm.xdpi;
		ydpi = dm.ydpi;
		screenWidthDip = dm.widthPixels; // 屏幕宽（dip，如：320dip）
		screenHeightDip = dm.heightPixels; // 屏幕宽（dip，如：533dip）
		screenWidth = (int) (dm.widthPixels * density + 0.5f); // 屏幕宽（px，如：480px）
		screenHeight = (int) (dm.heightPixels * density + 0.5f); // 屏幕高（px，如：800px）
	};

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	public DrawerLayout getmDrawerLayout() {
		return mDrawerLayout;
	}

	public void setmDrawerLayout(DrawerLayout mDrawerLayout) {
		this.mDrawerLayout = mDrawerLayout;
	}

	@Override
	public void onCancel(DialogInterface arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(int whichButton) {
		// TODO Auto-generated method stub
		switch (whichButton) {
		case 0:
			ActivityStackControlUtil.finishProgram();
			break;

		case 1:
			// showToast("点击了取消");
			break;

		default:
			break;
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();

		ActionSheet.showSheet(MainActivity.this, MainActivity.this,
				MainActivity.this);
	}

}
