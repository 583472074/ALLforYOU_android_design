package com.myxilove.gift.allforyou.activities;

import com.myxilove.gift.allforyou.R;
import com.myxilove.gift.allforyou.utils.ActivityStackControlUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class EmptyActivity extends Activity {
	private String key;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ActivityStackControlUtil.add(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.empty_activity);
		key = getIntent().getStringExtra("Key");
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(EmptyActivity.this,
						MainActivity.class);
				intent.putExtra("Key", key);
				startActivity(intent);
				EmptyActivity.this.overridePendingTransition(
						android.R.anim.fade_in, android.R.anim.fade_out);
				EmptyActivity.this.finish();
			}
		}, 1000);
	}
}
