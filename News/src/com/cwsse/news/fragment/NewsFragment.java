package com.cwsse.news.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cwsse.news.R;
import com.cwsse.news.adapter.NewsAdapter;
import com.cwsse.news.bean.NewsEntity;
import com.cwsse.news.config.Constants;
import com.cwsse.news.view.WebViewActivity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class NewsFragment extends Fragment {
	Activity activity;
	ArrayList<NewsEntity> newsList = new ArrayList<NewsEntity>();
	ListView mListView;
	NewsAdapter mAdapter;
	ImageView detail_loading;
	private PullToRefreshListView pull_refresh_list;
	public final static int SET_NEWSLIST = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		initData();
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		this.activity = activity;
		super.onAttach(activity);
	}

	/** 此方法意思为fragment是否可见 ,可见时候加载数据 */
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		// if (isVisibleToUser) {
		// // fragment可见时加载数据
		// if (newsList != null && newsList.size() != 0) {
		// handler.obtainMessage(SET_NEWSLIST).sendToTarget();
		// } else {
		// new Thread(new Runnable() {
		// @Override
		// public void run() {
		// // TODO Auto-generated method stub
		// try {
		// Thread.sleep(2);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// handler.obtainMessage(SET_NEWSLIST).sendToTarget();
		// }
		// }).start();
		// }
		// } else {
		// // fragment不可见时不执行操作
		// }
		super.setUserVisibleHint(isVisibleToUser);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.news_fragment, null);
		pull_refresh_list = (PullToRefreshListView) view.findViewById(R.id.pull_refresh_list);
		mListView = pull_refresh_list.getRefreshableView();
		mAdapter = new NewsAdapter(activity, newsList);
		BigPicRotate();
		mListView.setAdapter(mAdapter);
		setListener();
		return view;
	}

	private void initData() {
		newsList = Constants.getNewsList();
	}

	private void BigPicRotate() {
		LayoutInflater from = LayoutInflater.from(activity);
		View v = from.inflate(R.layout.bigpicrotate, null);
		mListView.addHeaderView(v);
	}

	private void setListener() {
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterview, View view, int i, long l) {
				String url = newsList.get(i).getUrl();
				Intent intent = new Intent();
				intent.putExtra(Constants.URL, url);
				intent.setClass(activity, WebViewActivity.class);
				activity.startActivity(intent);
			}
		});
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case SET_NEWSLIST:
				detail_loading.setVisibility(View.GONE);
				mAdapter = new NewsAdapter(activity, newsList);
				mListView.setAdapter(mAdapter);
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
	};
}
