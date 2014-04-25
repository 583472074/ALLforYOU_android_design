package com.myxilove.gift.allforyou.fragmets;

import java.util.List;

import com.myxilove.gift.allforyou.R;
import com.myxilove.gift.allforyou.activities.BeautifulGirlActivity;
import com.myxilove.gift.allforyou.adapter.CommentsAdapter;
import com.myxilove.gift.application.BaseApplication;
import com.myxilove.gift.factory.CommentFactory;
import com.myxilove.gift.model.CommentModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Novelance on 1/28/14.
 */
@SuppressLint("ValidFragment")
public class CommentsFragment extends Fragment {

	// private ShotsData data;
	private ListView mListView;
	private CommentsAdapter mAdapter;
	private Activity a;
	private List<CommentModel> list;

	public CommentsFragment(Activity a) {
		this.a = a;
		this.list = CommentFactory.getComment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_comments, container,
				false);
		TextView commentsLabel = (TextView) rootView
				.findViewById(R.id.comments_label);
		commentsLabel.setTypeface(BaseApplication.englishTypeface);
		mAdapter = new CommentsAdapter(a, list);
		mListView = (ListView) rootView.findViewById(R.id.comments_listview);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				CommentModel commentModel = list.get(arg2);
				Intent intent = new Intent(a, BeautifulGirlActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("Key", commentModel.player);
				intent.putExtras(bundle);
				startActivity(intent);
				a.overridePendingTransition(R.anim.alpha, R.anim.alpha2);
			}
		});
		mAdapter.notifyDataSetChanged();
		return rootView;
	}
}
