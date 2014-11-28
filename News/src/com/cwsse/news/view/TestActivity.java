package com.cwsse.news.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.cwsse.news.R;

public class TestActivity extends Activity {
	ViewPager bigpicrotate_vp;
	// 图片ID资源
	int[] imageIDs = new int[] { R.drawable.a, R.drawable.b };
	private ImageView[] mImageViews;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initData();
		setContentView(R.layout.bigpicrotate);
		bigpicrotate_vp = (ViewPager) findViewById(R.id.bigpicrotate_vp);
		BigPicRotate();
	}

	private void initData() {
		// 将图片装载到数组中
		mImageViews = new ImageView[imageIDs.length];
		for (int i = 0; i < imageIDs.length; i++) {
			ImageView imageView = new ImageView(this);
			mImageViews[i] = imageView;
			imageView.setBackgroundResource(imageIDs[i]);
		}
	}

	private void BigPicRotate() {
		VPadapter vp = new VPadapter();
		bigpicrotate_vp.setAdapter(vp);
	}

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
