package com.myxilove.gift.allforyou.adapter;


import com.myxilove.gift.allforyou.R;
import com.myxilove.gift.model.CodeClass;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.polites.android.GestureImageView;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView.ScaleType;

public class ImageViewAdpater extends PagerAdapter {

	private Activity a;
	private CodeClass[] list;
	private int count;
	private ImageLoader imageLoader;
	DisplayImageOptions options;

	public ImageViewAdpater(Activity a, CodeClass[] list,
			ImageLoader imageLoader) {
		this.a = a;
		this.list = list;
		this.imageLoader = imageLoader;
		count = list != null ? list.length : 0;

		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.pci_bg)
				.showImageForEmptyUri(R.drawable.pci_bg_bad)
				.showImageOnFail(R.drawable.pci_bg_bad).cacheInMemory(true)
				.cacheOnDisc(true).considerExifParams(false)
				.displayer(new FadeInBitmapDisplayer(400))
				.bitmapConfig(Bitmap.Config.RGB_565).build();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public Object instantiateItem(View arg0, int arg1) {
		GestureImageView view = new GestureImageView(a);

		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		view.setLayoutParams(params);
		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(v.getContext())
						.setMessage("Clicked!")
						.setTitle("onClickTest")
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();
									}
								}).create().show();
			}
		});
		// view.setImageResource(list.get(arg1).getKey());
		// view.setImageBitmap(list[arg1).getKey());
		imageLoader.displayImage(list[arg1].getKey(), view, options,
				new SimpleImageLoadingListener() {
					@Override
					public void onLoadingStarted(String imageUri, View view) {
						// holder.progressBar.setProgress(0);
						// holder.progressBar.setVisibility(View.VISIBLE);
					}

					@Override
					public void onLoadingFailed(String imageUri, View view,
							FailReason failReason) {
						// holder.progressBar.setVisibility(View.GONE);
					}

					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						// holder.progressBar.setVisibility(View.GONE);
					}
				}, new ImageLoadingProgressListener() {
					@Override
					public void onProgressUpdate(String imageUri, View view,
							int current, int total) {
						// holder.progressBar.setProgress(Math.round(100.0f *
						// current / total));
					}
				});
		view.setScaleType(ScaleType.CENTER_CROP);

		((ViewPager) arg0).addView(view);

		return view;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView((View) arg2);
	}
}
