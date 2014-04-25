package com.myxilove.gift.allforyou.adapter;

import com.myxilove.gift.allforyou.R;
import com.myxilove.gift.allforyou.activities.DetailsActivity;
import com.myxilove.gift.allforyou.fragmets.MainFragment;
import com.myxilove.gift.model.CodeClass;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyOwnPicAdapter extends BaseAdapter {
	private static String[] strArray = new String[] {
			"Great minds have purpose, others have wishes.",
			"Hope is a good thing, maybe the best of things, and no good thing ever dies.",
			"Everyone deserves not just to survive, but to live.--Steve McQueen",
			"Today I‘ll do what others won’t, so tomorrow I can accomplish what others can’t.",
			"It is our choices that show what we truly are, far more than our abilities." };
	private static String[] colorArray = new String[] { "#FF467283",
			"#FF997283", "#FF008B45", "#FF338B45" };
	private Context context;
	private LayoutInflater mInflater;
	private int[] imageIds = { R.drawable.j_1, R.drawable.j_2, R.drawable.j_3,
			R.drawable.j_4 };
	CodeClass[] imageUrls;
	private MainFragment fg;
	private ImageLoader imageLoader;
	DisplayImageOptions options;

	public MyOwnPicAdapter(MainFragment fg, Context context,
			CodeClass[] codeclass, ImageLoader imageLoader) {

		this.context = context;

		this.fg = fg;
		// fg.setmPlaceHolderBitmap(R.drawable.pci_bg);
		// this.listData = PicModel.getInstance();
		this.mInflater = LayoutInflater.from(context);
		this.imageUrls = codeclass;

		this.imageLoader = imageLoader;
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.pci_bg)
				.showImageForEmptyUri(R.drawable.pci_bg_bad)
				.showImageOnFail(R.drawable.pci_bg_bad).cacheInMemory(true)
				.cacheOnDisc(true).considerExifParams(false)
				.displayer(new FadeInBitmapDisplayer(400))
				.bitmapConfig(Bitmap.Config.ALPHA_8).build();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imageUrls.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		PicModelHodler holder = null;

		if (convertView == null) {
			holder = new PicModelHodler();
			convertView = mInflater.inflate(R.layout.own_pic_layout, null);
			holder.imageView = (ImageView) convertView.findViewById(R.id.image);
			holder.textViewShow = (TextView) convertView
					.findViewById(R.id.textview_show);
			convertView.setTag(holder);
		} else {
			holder = (PicModelHodler) convertView.getTag();
		}
		final int po = position % imageUrls.length;
		// holder.imageView.setImageDrawable(context.getResources().getDrawable(
		// listData.get(po).getKey()));
		// holder.imageView.setImageBitmap(listData.get(po).getKey());

		imageLoader.displayImage(imageUrls[position].getKey(),
				holder.imageView, options, new SimpleImageLoadingListener() {
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
		// fg.loadBitmap(resId, holder.imageView) ;
		holder.textViewShow.setText(strArray[position % strArray.length]);
		holder.textViewShow.setTextColor(Color.parseColor(colorArray[position
				% colorArray.length]));

		holder.imageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, DetailsActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("Key", imageUrls[po].getValue());
				intent.putExtras(bundle);
				context.startActivity(intent);
				((Activity) context).overridePendingTransition(R.anim.alpha,
						R.anim.alpha2);
			}
		});
		return convertView;
	}

	private final class PicModelHodler {
		public ImageView imageView;
		public TextView textViewShow;
	}
}
