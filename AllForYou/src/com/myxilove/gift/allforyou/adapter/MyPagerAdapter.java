package com.myxilove.gift.allforyou.adapter;

import java.util.List;

import com.myxilove.gift.allforyou.R;
import com.myxilove.gift.allforyou.activities.DetailsActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * 
 */
public class MyPagerAdapter extends PagerAdapter {
	// private int[] imageRes;
	private List<ImageView> imageViews;
	private Activity mActivity;
	private int flag;
	private Bitmap[] bmArray;
	private int[] imageId;

	public MyPagerAdapter(int[] imageId, List imageViews, Activity activity,
			int flag) {
		this.imageId = imageId;
		this.imageViews = imageViews;
		this.mActivity = activity;
		this.flag = flag;
		bmArray = new Bitmap[imageId.length];
	};

	@Override
	public int getCount() {
		return imageId.length;
	}

	@Override
	public Object instantiateItem(View arg0, int arg1) {
		final int position = arg1;
		ImageView imageView = imageViews.get(arg1);
		bmArray[arg1] = BitmapFactory.decodeResource(mActivity.getResources(),
				imageId[arg1]);
		//
		// imageLoader.displayImage(imageUrls[position].getKey(),
		// view, options, new SimpleImageLoadingListener() {
		// @Override
		// public void onLoadingStarted(String imageUri, View view) {
		// // holder.progressBar.setProgress(0);
		// // holder.progressBar.setVisibility(View.VISIBLE);
		// }
		//
		// @Override
		// public void onLoadingFailed(String imageUri, View view,
		// FailReason failReason) {
		// // holder.progressBar.setVisibility(View.GONE);
		// }
		//
		// @Override
		// public void onLoadingComplete(String imageUri, View view,
		// Bitmap loadedImage) {
		// // holder.progressBar.setVisibility(View.GONE);
		// }
		// }, new ImageLoadingProgressListener() {
		// @Override
		// public void onProgressUpdate(String imageUri, View view,
		// int current, int total) {
		// // holder.progressBar.setProgress(Math.round(100.0f *
		// // current / total));
		// }
		// });
		imageView.setImageBitmap(bmArray[arg1]);
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mActivity, DetailsActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("Key", "xiuxian");
				intent.putExtras(bundle);
				mActivity.startActivity(intent);
				mActivity
						.overridePendingTransition(R.anim.alpha, R.anim.alpha2);
			}
		});

		((ViewPager) arg0).addView(imageViews.get(arg1));

		return imageViews.get(arg1);
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		ImageView image = (ImageView) arg2;
		image.setImageBitmap(null);
		if (bmArray[arg1] != null) {
			bmArray[arg1].recycle();
		}
		((ViewPager) arg0).removeView(image);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

}