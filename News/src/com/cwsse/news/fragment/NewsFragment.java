package com.cwsse.news.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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
import com.cwsse.news.view.TestActivity;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class NewsFragment extends Fragment {
	Activity activity;
	ArrayList<NewsEntity> newsList = new ArrayList<NewsEntity>();
	ListView mListView;
	NewsAdapter mAdapter;
	ImageView detail_loading;
	private PullToRefreshListView pull_refresh_list;
	public final static int SET_NEWSLIST = 0;
	View bigpicrotate;
	ViewPager bigpicrotate_vp;
	// 图片ID资源
	int[] imageIDs = new int[] { R.drawable.b, R.drawable.a };
	private ImageView[] mImageViews;

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
		// 将图片装载到数组中
		mImageViews = new ImageView[imageIDs.length];
		for (int i = 0; i < imageIDs.length; i++) {
			ImageView imageView = new ImageView(activity);
			mImageViews[i] = imageView;
			imageView.setBackgroundResource(imageIDs[i]);
		}
	}

	private void BigPicRotate() {
		LayoutInflater from = LayoutInflater.from(activity);
		bigpicrotate = from.inflate(R.layout.bigpicrotate, null);
		bigpicrotate_vp = (ViewPager) bigpicrotate.findViewById(R.id.bigpicrotate_vp);
		VPadapter vp = new VPadapter();
		bigpicrotate_vp.setAdapter(vp);
		mListView.addHeaderView(bigpicrotate);
	}

	private void setListener() {
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterview, View view, int i, long l) {
				String url = newsList.get(i).getUrl();
				Intent intent = new Intent();
				intent.putExtra(Constants.URL, url);
				intent.setClass(activity, TestActivity.class);
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

	private class VPadapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imageIDs.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		/**
		 * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
		 */
		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager) container).addView(mImageViews[position]);
			return mImageViews[position];
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(mImageViews[position]);
		}
	}

}
