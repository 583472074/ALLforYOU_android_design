package com.myxilove.gift.allforyou.adapter;

import java.util.List;

import com.myxilove.gift.allforyou.R;
import com.myxilove.gift.application.BaseApplication;
import com.myxilove.gift.factory.DrawerFactory;
import com.myxilove.gift.model.DrawerModel;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerAdapter extends BaseAdapter {

	private Activity activity;
	private List<DrawerModel> listData;
	private LayoutInflater mInflater;
	private Typeface typeface, chineseTypeface;

	public DrawerAdapter(Activity activity) {
		this.activity = activity;
		listData = new DrawerFactory().getDrawerList();
		this.mInflater = LayoutInflater.from(activity);
		typeface = BaseApplication.englishTypeface;
		chineseTypeface = BaseApplication.chineseTypeface;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listData.size();
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
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater
					.inflate(R.layout.item_drawer, parent, false);
			holder = new ViewHolder();
			holder.titlePic = (ImageView) convertView.findViewById(R.id.image);
			holder.text = (TextView) convertView.findViewById(R.id.text);
			holder.numbBg = (ImageView) convertView
					.findViewById(R.id.relativeLayout_image);
			holder.numb = (TextView) convertView
					.findViewById(R.id.relativeLayout_text);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		DrawerModel model = listData.get(position);
		holder.titlePic.setBackgroundResource(model.drawbleResource);
		holder.text.setText(model.title);
		if (model.title.equals("时光轴")) {
			holder.text.setTypeface(chineseTypeface);
		} else {
			holder.text.setTypeface(typeface);
		}

		if (model.numb != null && !model.numb.equals("")) {
			int numb = Integer.parseInt(model.numb);
			holder.numb.setText(model.numb);
			if (numb >= 30) {
				holder.numb.setBackgroundResource(R.drawable.numb_bg_2);
			} else if (numb > 0 && numb < 30) {
				holder.numb.setBackgroundResource(R.drawable.numb_bg_1);
			} else if (numb <= 0) {
				holder.numb.setBackgroundColor(Color.TRANSPARENT);
				holder.numb.setText("");
			}
		} else {
			holder.numb.setText("");
		}
		return convertView;
	}

	class ViewHolder {
		public ImageView titlePic;
		public TextView text;
		public ImageView numbBg;
		public TextView numb;

	}
}
