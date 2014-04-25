package com.myxilove.gift.factory;

import java.util.ArrayList;
import java.util.List;

import com.myxilove.gift.allforyou.R;
import com.myxilove.gift.model.DrawerModel;

public class DrawerFactory {
	private List<DrawerModel> list;

	public DrawerFactory() {
		list = new ArrayList<DrawerModel>();
	}

	public List<DrawerModel> getDrawerList() {
		list.add(new DrawerModel(R.drawable.ic_views, "Views", "26"));
		list.add(new DrawerModel(R.drawable.ic_likes, "Likes", "38"));
		list.add(new DrawerModel(R.drawable.ic_comment, "Comments", "8"));
//		list.add(new DrawerModel(R.drawable.ic_views, "时光轴", "0"));
		return list;

	}
}
