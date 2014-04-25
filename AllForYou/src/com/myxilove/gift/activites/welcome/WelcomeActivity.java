package com.myxilove.gift.activites.welcome;

import com.myxilove.gift.allforyou.R;
import com.myxilove.gift.allforyou.activities.LoginActivity;
import com.myxilove.gift.allforyou.utils.ActivityStackControlUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

public class WelcomeActivity extends FragmentActivity {
	boolean isFirst = false;
	ImageView iamgeView;
	Bitmap bm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityStackControlUtil.add(this);
		bm = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.welcome);
		setContentView(R.layout.activity_welcome);
		iamgeView = (ImageView) findViewById(R.id.imageview);

		iamgeView.setImageBitmap(bm);
		init();
	}

	private void init() {

		SharedPreferences preferences = getSharedPreferences("first_pref",
				MODE_PRIVATE);
		isFirst = preferences.getBoolean("isFirst", true);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
//				if (!isFirst) {
					goGuide();
//				} else {
//					goHome();
//				}
			}
		}, 2000);
	}

	private void goHome() {
		Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
		startActivity(intent);
		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);
		this.finish();
	}

	private void goGuide() {
		Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);
		startActivity(intent);
		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);
		this.finish();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (bm != null) {
			bm.recycle();
		}
		super.onDestroy();
	}
}
