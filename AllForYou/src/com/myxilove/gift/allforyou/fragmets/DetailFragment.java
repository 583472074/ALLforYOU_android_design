package com.myxilove.gift.allforyou.fragmets;

import com.etsy.android.grid.StaggeredGridView;
import com.flavienlaurent.notboringactionbar.KenBurnsView;
import com.myxilove.gift.allforyou.R;
import com.myxilove.gift.allforyou.activities.BeautifulGirlActivity;
import com.myxilove.gift.allforyou.activities.MainActivity;
import com.myxilove.gift.allforyou.adapter.MyGridPicAdapter;
import com.myxilove.gift.allforyou.utils.BitmapPicUtils;
import com.myxilove.gift.application.BaseApplication;
import com.myxilove.gift.model.CodeClass;
import com.myxilove.gift.model.Constants;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint({ "ValidFragment", "NewApi" })
public class DetailFragment extends Fragment implements View.OnClickListener,
		AbsListView.OnScrollListener {

	private Activity mActivity;
	private View rootView;
	private KenBurnsView detailPicture;
	// private AccelerateDecelerateInterpolator mSmoothInterpolator;
	// private AlphaForegroundColorSpan mAlphaForegroundColorSpan;
	// private SpannableString mSpannableString;
	// private TypedValue mTypedValue = new TypedValue();
	private double picScale;
	private MyGridPicAdapter mAdapter;
	// private ShotsData data;
	private Bundle bundle;
	private Typeface typeface;
	private RelativeLayout relativeLayout;
	private StaggeredGridView mGridView;
	private String value;
	private CodeClass[] listData;
	protected ImageLoader imageLoader = ImageLoader.getInstance();

	public DetailFragment(Activity activity) {
		this.mActivity = activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		bundle = getActivity().getIntent().getExtras();

		picScale = BitmapPicUtils.getPicScale(getResources(), R.drawable.j_1);
		typeface = BaseApplication.englishTypeface;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_shots_detail, container,
				false);
		initView(rootView);
		initMoreShots(rootView);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				relativeLayout.setVisibility(View.GONE);
				mGridView.setVisibility(View.VISIBLE);
			}
		}, 2000);
		return rootView;
	}

	private void initView(View view) {
		// TODO Auto-generated method stub
		detailPicture = (KenBurnsView) view.findViewById(R.id.detail_image);

		RelativeLayout.LayoutParams viewPaLayoutParams = new RelativeLayout.LayoutParams(
				MainActivity.screenWidthDip,
				(int) (MainActivity.screenWidthDip * picScale));
		// viewPaLayoutParams.setLayoutDirection(RelativeLayout.CENTER_IN_PARENT);
		// viewPaLayoutParams.setLayoutDirection(RelativeLayout.CENTER_HORIZONTAL);
		viewPaLayoutParams.addRule(RelativeLayout.BELOW, R.id.detail_avatar);
		detailPicture.setLayoutParams(viewPaLayoutParams);

		ImageView playerAvatar = (ImageView) view
				.findViewById(R.id.detail_avatar);
		playerAvatar.setOnClickListener(this);
		TextView title = (TextView) view.findViewById(R.id.detail_title);
		title.setTypeface(typeface);
		TextView player = (TextView) view.findViewById(R.id.detail_player);
		player.setOnClickListener(this);
		TextView views = (TextView) view.findViewById(R.id.detail_views);
		TextView likes = (TextView) view.findViewById(R.id.detail_likes);
		TextView comments = (TextView) view.findViewById(R.id.detail_commentss);
		TextView emptyText = (TextView) view.findViewById(R.id.empty_text);
		emptyText.setTypeface(typeface);

		ImageButton shotCollect = (ImageButton) view
				.findViewById(R.id.shot_collect);
		ImageButton weibo = (ImageButton) view.findViewById(R.id.weibo);
		ImageButton twitter = (ImageButton) view.findViewById(R.id.twitter);
		shotCollect.setOnClickListener(this);
		weibo.setOnClickListener(this);
		twitter.setOnClickListener(this);

		if (bundle != null) {
			value = bundle.getString("Key");
			if (value.equals("xiuxian")) {
				title.setText("来自星星的你");
				player.setText("都叫兽");
				views.setText("1127");
				likes.setText("868");
				comments.setText("126");
				playerAvatar.setBackgroundResource(R.drawable.title_xiuxian);
				listData = Constants.JXX_IMAGES_CODECLASS;
				detailPicture.setResourceIds(R.drawable.j_5, R.drawable.j_7);
			}

		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.detail_avatar:
			Intent intent = new Intent(mActivity, BeautifulGirlActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("Key", value);
			intent.putExtras(bundle);
			startActivity(intent);
			mActivity.overridePendingTransition(R.anim.alpha, R.anim.alpha2);
			break;

		default:
			break;
		}
	}

	private void initMoreShots(View view) {
		mGridView = (StaggeredGridView) view.findViewById(R.id.more_shots);
		mAdapter = new MyGridPicAdapter(mActivity, listData, value, imageLoader);
		mAdapter.notifyDataSetChanged();
		SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(
				mAdapter);
		swingBottomInAnimationAdapter.setAbsListView(mGridView);
		swingBottomInAnimationAdapter.setInitialDelayMillis(450);

		mGridView.setAdapter(swingBottomInAnimationAdapter);
		mGridView.setOnScrollListener(this);
		mGridView.setVisibility(View.GONE);
		relativeLayout = (RelativeLayout) view
				.findViewById(R.id.gridview_empty);
		relativeLayout.setVisibility(View.VISIBLE);
	}

	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// TODO Auto-generated method stub

	}

}
