package com.myxilove.gift.allforyou.activities;

import android.app.ActionBar;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.MenuItem;
import android.widget.TextView;

import com.myxilove.gift.allforyou.R;
import com.myxilove.gift.allforyou.fragmets.GirlFragment;
import com.myxilove.gift.allforyou.utils.ActivityStackControlUtil;
import com.myxilove.gift.application.BaseApplication;
import com.myxilove.swipebacklayout.lib.SwipeBackLayout;
import com.myxilove.swipebacklayout.lib.app.SwipeBackActivity;

public class BeautifulGirlActivity extends SwipeBackActivity {
	protected void onCreate(android.os.Bundle savedInstanceState) {
		setContentView(R.layout.beautiful_girl_activity);
		ActivityStackControlUtil.add(this);
		super.onCreate(savedInstanceState);
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.girl_container,
						new GirlFragment(BeautifulGirlActivity.this)).commit();
		setActionBarStyle();
	};

	private void setActionBarStyle() {
		this.getActionBar().setTitle("dribbble");
		getActionBar().setIcon(R.drawable.ic_launcher);
		getActionBar().setBackgroundDrawable(
				this.getBaseContext().getResources()
						.getDrawable(R.drawable.actionbar_back));
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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			this.finish();
			overridePendingTransition(android.R.anim.slide_in_left,
					android.R.anim.slide_out_right);
		}
		return super.onOptionsItemSelected(item);
	}
}
