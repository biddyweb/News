package com.cwsse.news.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cwsse.news.R;
import com.cwsse.news.bean.NewsEntity;
import com.cwsse.news.config.Constants;
import com.cwsse.news.util.Options;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class NewsAdapter extends BaseAdapter {
	ArrayList<NewsEntity> newsList;
	Activity activity;
	LayoutInflater inflater = null;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;

	public NewsAdapter(Activity activity, ArrayList<NewsEntity> newsList) {
		this.activity = activity;
		this.newsList = newsList;
		inflater = LayoutInflater.from(activity);
		options = Options.getListOptions();
	}

	@Override
	public int getCount() {
		return newsList == null ? 0 : newsList.size();
	}

	@Override
	public NewsEntity getItem(int position) {
		if (newsList != null && newsList.size() != 0) {
			return newsList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder mHolder;
		View view = convertView;
		if (view == null) {
			view = inflater.inflate(R.layout.list_item, null);
			mHolder = new ViewHolder();
			mHolder.item_layout = (RelativeLayout) view.findViewById(R.id.item_layout);
			mHolder.item_title = (TextView) view.findViewById(R.id.item_title);
			mHolder.item_source = (TextView) view.findViewById(R.id.item_source);
			mHolder.list_item_local = (TextView) view.findViewById(R.id.list_item_local);
			mHolder.comment_count = (TextView) view.findViewById(R.id.comment_count);
			mHolder.publish_time = (TextView) view.findViewById(R.id.publish_time);
			mHolder.right_image = (ImageView) view.findViewById(R.id.right_image);
			mHolder.item_image_layout = (LinearLayout) view.findViewById(R.id.item_image_layout);
			mHolder.item_image_0 = (ImageView) view.findViewById(R.id.item_image_0);
			mHolder.item_image_1 = (ImageView) view.findViewById(R.id.item_image_1);
			mHolder.item_image_2 = (ImageView) view.findViewById(R.id.item_image_2);
			mHolder.large_image = (ImageView) view.findViewById(R.id.large_image);
			mHolder.right_padding_view = (View) view.findViewById(R.id.right_padding_view);
			view.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) view.getTag();
		}
		// 获取position对应的数据
		NewsEntity news = getItem(position);
		mHolder.item_title.setText(news.getTitle());
		mHolder.item_source.setText(news.getSource());
		mHolder.comment_count.setText("评论" + news.getCommentNum());
		mHolder.publish_time.setText(news.getPublishTime() + "小时前");
		List<String> imgUrlList = news.getPicList();
		// mHolder.popicon.setVisibility(View.VISIBLE);
		// mHolder.comment_count.setVisibility(View.VISIBLE);
		// mHolder.right_padding_view.setVisibility(View.VISIBLE);
		if (imgUrlList != null && imgUrlList.size() != 0) {
			if (imgUrlList.size() == 1) {
				mHolder.item_image_layout.setVisibility(View.GONE);
				// 是否是大图
				if (news.getIsLarge()) {
					mHolder.large_image.setVisibility(View.VISIBLE);
					mHolder.right_image.setVisibility(View.GONE);
					imageLoader.displayImage(imgUrlList.get(0), mHolder.large_image, options);
					// mHolder.popicon.setVisibility(View.GONE);
					// mHolder.comment_count.setVisibility(View.GONE);
					// mHolder.right_padding_view.setVisibility(View.GONE);
				} else {
					mHolder.large_image.setVisibility(View.GONE);
					mHolder.right_image.setVisibility(View.VISIBLE);
					String url = imgUrlList.get(0);
					ImageView iv = mHolder.right_image;

					imageLoader.displayImage(url, iv, options);
				}
			} else {
				mHolder.large_image.setVisibility(View.GONE);
				mHolder.right_image.setVisibility(View.GONE);
				mHolder.item_image_layout.setVisibility(View.VISIBLE);
				imageLoader.displayImage(imgUrlList.get(0), mHolder.item_image_0, options);
				imageLoader.displayImage(imgUrlList.get(1), mHolder.item_image_1, options);
				imageLoader.displayImage(imgUrlList.get(2), mHolder.item_image_2, options);
			}
		} else {
			// mHolder.right_image.setVisibility(View.GONE);
			// mHolder.item_image_layout.setVisibility(View.GONE);
		}
		// 判断该新闻是否是特殊标记的，推广等，为空就是新闻
		if (!TextUtils.isEmpty(news.getLocal())) {
			mHolder.list_item_local.setVisibility(View.VISIBLE);
			mHolder.list_item_local.setText(news.getLocal());
		} else {
			mHolder.list_item_local.setVisibility(View.GONE);
		}
		// 判断该新闻是否已读
		if (!news.getReadStatus()) {
			mHolder.item_layout.setSelected(true);
		} else {
			mHolder.item_layout.setSelected(false);
		}
		return view;
	}

	static class ViewHolder {
		RelativeLayout item_layout;
		// title
		TextView item_title;
		// 图片源
		TextView item_source;
		// 类似推广之类的标签
		TextView list_item_local;
		// 评论数量
		TextView comment_count;
		// 发布时间
		TextView publish_time;
		// 右边图片
		ImageView right_image;
		// 3张图片布局
		LinearLayout item_image_layout; // 3张图片时候的布局
		ImageView item_image_0;
		ImageView item_image_1;
		ImageView item_image_2;
		// 大图的图片的话布局
		ImageView large_image;
		View right_padding_view;
	}

}
