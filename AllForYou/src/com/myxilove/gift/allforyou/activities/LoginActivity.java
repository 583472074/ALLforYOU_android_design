package com.myxilove.gift.allforyou.activities;

import com.myxilove.gift.allforyou.R;
import com.myxilove.gift.allforyou.fragmets.LoginFragment;
import com.myxilove.gift.allforyou.utils.ActivityStackControlUtil;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class LoginActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		ActivityStackControlUtil.add(this);
		super.onCreate(arg0);

		setContentView(R.layout.login_activity);

		getSupportFragmentManager().beginTransaction()
				.replace(R.id.login, new LoginFragment()).commit();
	}
}
