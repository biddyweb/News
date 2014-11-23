package com.cwsse.news;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cwsse.news.adapter.NewsFragmentPagerAdapter;
import com.cwsse.news.bean.NewsClassify;
import com.cwsse.news.config.Constants;
import com.cwsse.news.fragment.NewsFragment;
import com.cwsse.news.util.Utils;
import com.cwsse.news.view.ColumnHorizontalScrollView;
import com.cwsse.news.view.SlidingMenuMenager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class MainActivity extends FragmentActivity {
	@ViewInject(R.id.main_rl_column)
	private RelativeLayout main_rl_column;
	@ViewInject(R.id.main_mColumnHorizontalScrollView)
	private ColumnHorizontalScrollView mColumnHorizontalScrollView;
	@ViewInject(R.id.main_shade_left)
	public ImageView main_shade_left;
	@ViewInject(R.id.main_shade_right)
	public ImageView main_shade_right;

	@ViewInject(R.id.main_ll_tabtitle)
	LinearLayout main_ll_tabtitle;

	@ViewInject(R.id.main_ll_more)
	LinearLayout main_ll_more;
	@ViewInject(R.id.main_btn_more_columns)
	private ImageView main_btn_more_columns;

	@ViewInject(R.id.main_vp_content)
	private ViewPager main_vp_content;

	protected SlidingMenu slidingmenu;
	/** 屏幕宽度 */
	private int mScreenWidth = 0;
	/** Item宽度 */
	private int mItemWidth = 0;
	/** 当前选中的栏目 */
	private int columnSelectIndex = 0;
	/** 新闻分类列表 */
	private ArrayList<NewsClassify> newsClassify = new ArrayList<NewsClassify>();
	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mScreenWidth = Utils.getWindowsWidth(this);
		ViewUtils.inject(this);
		slidingmenu = SlidingMenuMenager.getInstance().initSlidingMenu(this);
		mItemWidth = mScreenWidth / 7;// 一个Item宽度为屏幕的1/7
		initView();

	}

	/**
	 * 
	 */
	private void initView() {
		initColumnData();
		initTabColumn();
		initFragment();

	}

	/** 获取Column栏目 数据 */
	private void initColumnData() {
		newsClassify = Constants.getData();
	}

	/**
	 * 初始化Column栏目项
	 * */
	private void initTabColumn() {
		main_ll_tabtitle.removeAllViews();
		int count = newsClassify.size();
		mColumnHorizontalScrollView.setParam(this, mScreenWidth, main_ll_tabtitle, main_shade_left, main_shade_right, main_ll_more, main_btn_more_columns);
		for (int i = 0; i < count; i++) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth, LayoutParams.WRAP_CONTENT);
			params.leftMargin = 5;
			params.rightMargin = 5;
			TextView columnTextView = new TextView(this);
			columnTextView.setTextAppearance(this, R.style.top_category_scroll_view_item_text);
			columnTextView.setBackgroundResource(R.drawable.radio_buttong_bg);
			columnTextView.setGravity(Gravity.CENTER);
			columnTextView.setPadding(5, 5, 5, 5);
			columnTextView.setId(i);
			columnTextView.setText(newsClassify.get(i).getTitle());
			columnTextView.setTextColor(getResources().getColorStateList(R.color.top_category_scroll_text_color_day));
			if (columnSelectIndex == i) {
				columnTextView.setSelected(true);
			}
			columnTextView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					for (int i = 0; i < main_ll_tabtitle.getChildCount(); i++) {
						View localView = main_ll_tabtitle.getChildAt(i);
						if (localView != v)
							localView.setSelected(false);
						else {
							localView.setSelected(true);
							main_vp_content.setCurrentItem(i);
						}
					}
				}
			});
			main_ll_tabtitle.addView(columnTextView, i, params);
		}
	}

	/**
	 * ViewPager切换监听方法
	 * */
	public OnPageChangeListener pageListener = new OnPageChangeListener() {

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int position) {
			main_vp_content.setCurrentItem(position);
			selectTab(position);
		}
	};
	/** 
	 *  选择的Column里面的Tab
	 * */
	private void selectTab(int tab_postion) {
		columnSelectIndex = tab_postion;
		for (int i = 0; i < main_ll_tabtitle.getChildCount(); i++) {
			View checkView = main_ll_tabtitle.getChildAt(tab_postion);
			int k = checkView.getMeasuredWidth();
			int l = checkView.getLeft();
			int i2 = l + k / 2 - mScreenWidth / 2;
			// rg_nav_content.getParent()).smoothScrollTo(i2, 0);
			mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
			// mColumnHorizontalScrollView.smoothScrollTo((position - 2) *
			// mItemWidth , 0);
		}
		//判断是否选中
		for (int j = 0; j <  main_ll_tabtitle.getChildCount(); j++) {
			View checkView = main_ll_tabtitle.getChildAt(j);
			boolean ischeck;
			if (j == tab_postion) {
				ischeck = true;
			} else {
				ischeck = false;
			}
			checkView.setSelected(ischeck);
		}
	}

	/**
	 * 初始化Fragment
	 * */
	private void initFragment() {
		int count = newsClassify.size();
		for (int i = 0; i < count; i++) {
			NewsFragment newfragment = new NewsFragment();
			fragments.add(newfragment);
		}
		NewsFragmentPagerAdapter mAdapetr = new NewsFragmentPagerAdapter(getSupportFragmentManager(), fragments);
		main_vp_content.setAdapter(mAdapetr);
		main_vp_content.setOnPageChangeListener(pageListener);
	}
}
