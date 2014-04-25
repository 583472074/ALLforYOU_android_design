package com.myxilove.gift.allforyou.activities;

import com.myxilove.gift.allforyou.R;
import com.myxilove.gift.allforyou.fragmets.BeautifulImageViewFragment;
import com.myxilove.gift.allforyou.utils.ActivityStackControlUtil;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;

public class BeautifulImageViewActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActivityStackControlUtil.add(this);
		setContentView(R.layout.beautiful_image_activity);
		getSupportFragmentManager()
				.beginTransaction()
				.replace(
						R.id.image_list_container,
						new BeautifulImageViewFragment(
								BeautifulImageViewActivity.this)).commit();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			this.finish(); // finish当前activity
			overridePendingTransition(R.anim.trans, R.anim.trans2);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
