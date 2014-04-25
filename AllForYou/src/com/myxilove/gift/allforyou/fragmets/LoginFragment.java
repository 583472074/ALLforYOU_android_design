package com.myxilove.gift.allforyou.fragmets;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapCircleThumbnail;
import com.iangclifton.android.floatlabel.FloatLabel;
import com.myxilove.gift.allforyou.R;
import com.myxilove.gift.allforyou.activities.MainActivity;
import com.todddavies.components.progressbar.ProgressWheel;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginFragment extends Fragment {

	private View rootView;
	private LinearLayout linear;
	private FloatLabel usrName, passWd;
	private BootstrapButton loginBt;
	private BootstrapCircleThumbnail image;
	private Animation alphaAnimation, aAnimation;
	private RelativeLayout imageRelativeLayout;
	private Animator mCurrentAnimator;
	private ProgressWheel progress;
	private RelativeLayout relativeLayoutAll;
	private static final int GO_HOME = 3;
	private static final int INIT_ANIMATION = 1;
	private static final int GO_BACK = 2;
	private TextView forgotPswdTv;
	private ImageView newUserImage;
	private Shader shader;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ShapeDrawable bg = new ShapeDrawable(new RectShape());
		int[] pixels = new int[] { 0xFF8E8E8E, 0xFF8E8E8E, 0xFF8E8E8E,
				0xFF8E8E8E, 0xFF8E8E8E, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF };
		Bitmap bm = Bitmap.createBitmap(pixels, 8, 1, Bitmap.Config.ARGB_8888);
		shader = new BitmapShader(bm, Shader.TileMode.REPEAT,
				Shader.TileMode.REPEAT);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.login_fragment, container, false);

		relativeLayoutAll = (RelativeLayout) rootView
				.findViewById(R.id.relativeLayout_all);

		initAnimation();
		initEditText();
		initProgressBar();
		initLoginBt();
		mHandler.sendEmptyMessageDelayed(INIT_ANIMATION, 200);
		return rootView;
	}

	private void initAnimation() {
		// TODO Auto-generated method stub
		alphaAnimation = new AlphaAnimation(0.5f, 1.0f);
		alphaAnimation.setDuration(400);
	}

	private void startInitAnimator() {
		// TODO Auto-generated method stub
		AnimatorSet set = new AnimatorSet();
		linear.setVisibility(View.VISIBLE);
		set.play(ObjectAnimator.ofFloat(linear, View.Y, 700, 400));
		set.setDuration(1000);
		set.setInterpolator(new DecelerateInterpolator());
		set.start();
	}

	private void initLoginBt() {
		// TODO Auto-generated method stub
		loginBt = (BootstrapButton) rootView.findViewById(R.id.login);
		loginBt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				InputMethodManager imm = (InputMethodManager) getActivity()
						.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

				startLoginAnimation();

			}
		});

		forgotPswdTv = (TextView) rootView.findViewById(R.id.forgot_pawd);
		forgotPswdTv.setText(Html.fromHtml("<u>" + "忘记密码?" + "</u>"));
		newUserImage = (ImageView) rootView.findViewById(R.id.new_user);
	}

	protected void startLoginAnimation() {
		// TODO Auto-generated method stub

		AnimatorSet set = new AnimatorSet();
		set.play(ObjectAnimator.ofFloat(imageRelativeLayout, View.Y, 0, 450))
				.with(ObjectAnimator.ofFloat(linear, View.ALPHA, 1.0f, 0.0f))
				.with(ObjectAnimator.ofFloat(loginBt, View.ALPHA, 1.0f, 0.0f))
				.with(ObjectAnimator.ofFloat(forgotPswdTv, View.ALPHA, 1.0f,
						0.0f))
				.with(ObjectAnimator.ofFloat(newUserImage, View.ALPHA, 1.0f,
						0.0f));

		set.setDuration(1000);
		set.setInterpolator(new DecelerateInterpolator());
		set.addListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				mCurrentAnimator = null;
				progress.setRimShader(shader);
				progress.spin();
				progress.setVisibility(View.VISIBLE);
				String passWdStr = passWd.getEditText().getText() + "";
				String userStr = usrName.getEditText().getText() + "";
				if (userStr.equals("jiuye1992") && passWdStr.equals("1992")) {
					mHandler.sendEmptyMessageDelayed(GO_HOME, 2000);
				} else if (userStr.equals("xiuxian1988")
						&& passWdStr.equals("1988")) {
					mHandler.sendEmptyMessageDelayed(GO_HOME, 2000);
				} else {
					mHandler.sendEmptyMessageDelayed(GO_BACK, 2000);
				}

			}

			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				mCurrentAnimator = null;
			}
		});
		set.start();
		mCurrentAnimator = set;
	}

	private void initProgressBar() {
		// TODO Auto-generated method stub
		imageRelativeLayout = (RelativeLayout) rootView
				.findViewById(R.id.image_relative);
		progress = (ProgressWheel) rootView.findViewById(R.id.progressBar);
		image = (BootstrapCircleThumbnail) rootView
				.findViewById(R.id.imageview);

	}

	private void initEditText() {
		// TODO Auto-generated method stub
		linear = (LinearLayout) rootView.findViewById(R.id.linear);
		usrName = (FloatLabel) rootView.findViewById(R.id.usrname);
		passWd = (FloatLabel) rootView.findViewById(R.id.passwd);
		usrName.getEditText().addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				// int a = isStrongPassword(newPswdEt.getText() + "");
				// setPassWordView(a);

				String usrNameStr = usrName.getEditText().getText() + "";
				// if(usrNameStr)
				if (usrNameStr.equals("xiuxian1988")) {
					image.setImage(R.drawable.title_xiuxian);
					image.startAnimation(alphaAnimation);
				} else {
					image.setImage(R.drawable.title_icon);
				}

			}
		});// 输入框注册事件
	}

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GO_HOME:
				goHome();
				break;
			case GO_BACK:
				goBack();
				break;
			case INIT_ANIMATION:
				startInitAnimator();
				break;
			}
		}
	};

	protected void goHome() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getActivity(), MainActivity.class);
		String usrNameStr = usrName.getEditText().getText() + "";
		if (usrNameStr.equals("jiuye1992")) {
			intent.putExtra("Key", "jiuye");
		} else {
			intent.putExtra("Key", "xiuxian");
		}
		startActivity(intent);
		getActivity().overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);
		getActivity().finish();
	}

	protected void goBack() {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), "密码不正确！登入失败", 0).show();
		progress.setVisibility(View.GONE);
		progress.stopSpinning();

		startBackAnimation();

	}

	private void startBackAnimation() {
		// TODO Auto-generated method stub

		AnimatorSet set = new AnimatorSet();
		set.play(ObjectAnimator.ofFloat(imageRelativeLayout, View.Y, 450, 0))
				.with(ObjectAnimator.ofFloat(linear, View.ALPHA, 0f, 1.0f))
				.with(ObjectAnimator
						.ofFloat(newUserImage, View.ALPHA, 0f, 1.0f))
				.with(ObjectAnimator
						.ofFloat(forgotPswdTv, View.ALPHA, 0f, 1.0f))
				.with(ObjectAnimator.ofFloat(loginBt, View.ALPHA, 0f, 1.0f));

		set.setDuration(1000);
		set.setInterpolator(new DecelerateInterpolator());
		set.addListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				mCurrentAnimator = null;
				// progress.setVisibility(View.VISIBLE);
				// String passWdStr = passWd.getEditText().getText() + "";
				// if (passWdStr.equals("1992")) {
				// mHandler.sendEmptyMessageDelayed(GO_HOME, 2000);
				// } else {
				// mHandler.sendEmptyMessageDelayed(GO_BACK, 2000);
				// }

			}

			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				mCurrentAnimator = null;
			}
		});
		set.start();
		mCurrentAnimator = set;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
