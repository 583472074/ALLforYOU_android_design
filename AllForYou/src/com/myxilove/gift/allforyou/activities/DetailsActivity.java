package com.myxilove.gift.allforyou.activities;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.myxilove.gift.allforyou.R;
import com.myxilove.gift.allforyou.fragmets.CommentsFragment;
import com.myxilove.gift.allforyou.fragmets.DetailFragment;
import com.myxilove.gift.allforyou.utils.ActivityStackControlUtil;
import com.myxilove.swipebacklayout.lib.SwipeBackLayout;
import com.myxilove.swipebacklayout.lib.app.SwipeBackActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.WindowManager;

public class DetailsActivity extends SwipeBackActivity {
	private SwipeBackLayout mSwipeBackLayout;
	private SlidingMenu sm;
	private Fragment commentFragmet, detailFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.detail_view_main);
		super.onCreate(savedInstanceState);
		ActivityStackControlUtil.add(this);
		mSwipeBackLayout = getSwipeBackLayout();
		mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
		commentFragmet = new CommentsFragment(DetailsActivity.this);
		detailFragment = new DetailFragment(DetailsActivity.this);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.container, detailFragment).commit();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.comments_list_container, commentFragmet).commit();
		initSlidingMenu();
	}

	private void initSlidingMenu() {
		WindowManager manager = getWindowManager();
		Display display = manager.getDefaultDisplay();
		sm = new SlidingMenu(DetailsActivity.this);
		sm.setMode(SlidingMenu.RIGHT);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		sm.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		sm.setBehindOffset(display.getWidth() * 1 / 4);
		sm.setShadowDrawable(R.drawable.sidebar_shadow_right);
		sm.setShadowWidth(30);
		sm.setMenu(R.layout.menu_layout);
		sm.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
			@Override
			public void onOpened() {
				mSwipeBackLayout.setEnableGesture(false);
			}
		});
		sm.setOnClosedListener(new SlidingMenu.OnClosedListener() {
			@Override
			public void onClosed() {
				mSwipeBackLayout.setEnableGesture(true);
			}
		});
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub

		super.onDestroy();
	}

}
