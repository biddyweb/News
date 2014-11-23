package com.cwsse.news.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.cwsse.news.R;
import com.cwsse.news.adapter.NewsAdapter;
import com.cwsse.news.bean.NewsEntity;
import com.cwsse.news.config.Constants;

public class NewsFragment extends Fragment {
	Activity activity;
	ArrayList<NewsEntity> newsList = new ArrayList<NewsEntity>();
	ListView mListView;
	NewsAdapter mAdapter;
	ImageView detail_loading;
	private ImageView news_iv_bigpic;
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
		news_iv_bigpic = (ImageView) view.findViewById(R.id.news_iv_bigpic);
		mListView = (ListView) view.findViewById(R.id.mListView);
		// List<String> ls = new ArrayList<String>();
		// ls.add("ljjljl");
		// ls.add("ljjljl");
		// ls.add("ljjljl");
		// ls.add("ljjljl");
		// ls.add("ljjljl");
		// ArrayAdapter<String> a = new ArrayAdapter<String>(getActivity(),
		// android.R.layout.simple_spinner_item, ls);
		// mListView.setAdapter(a);
		mAdapter = new NewsAdapter(activity, newsList);
		mListView.setAdapter(mAdapter);
		return view;
	}

	private void initData() {
		newsList = Constants.getNewsList();
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
