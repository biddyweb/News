package com.cwsse.news.view;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cwsse.news.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * 自定义SlidingMenu 测拉菜单类
 * */
public class SlidingMenuMenager implements OnClickListener {
	private static SlidingMenuMenager instance = new SlidingMenuMenager();

	private SlidingMenuMenager() {
	}

	public static SlidingMenuMenager getInstance() {
		return instance;
	}

	private  Activity activity;
	SlidingMenu localSlidingMenu;
	private TextView night_mode_text;
	private RelativeLayout setting_btn;

	public SlidingMenu initSlidingMenu(Activity activity) {
		this.activity = activity;
		localSlidingMenu = new SlidingMenu(activity);
		localSlidingMenu.setMode(SlidingMenu.LEFT_RIGHT);// 设置左右滑菜单
		localSlidingMenu.setTouchModeAbove(SlidingMenu.SLIDING_WINDOW);// 设置要使菜单滑动，触碰屏幕的范围
		// localSlidingMenu.setTouchModeBehind(SlidingMenu.RIGHT);
		localSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);// 设置阴影图片的宽度
		localSlidingMenu.setShadowDrawable(R.drawable.shadow);// 设置阴影图片
		localSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);// SlidingMenu划出时主页面显示的剩余宽度
		localSlidingMenu.setFadeDegree(0.35F);// SlidingMenu滑动时的渐变程度
		localSlidingMenu.attachToActivity(activity, SlidingMenu.RIGHT);// 使SlidingMenu附加在Activity右边
		// localSlidingMenu.setBehindWidthRes(R.dimen.left_drawer_avatar_size);//设置SlidingMenu菜单的宽度
		localSlidingMenu.setMenu(R.layout.left_drawer_fragment);// 设置menu的布局文件
		// localSlidingMenu.toggle();//动态判断自动关闭或开启SlidingMenu
		localSlidingMenu.setSecondaryMenu(R.layout.right_drawer_fragment);
		localSlidingMenu.setSecondaryShadowDrawable(R.drawable.shadowright);
		localSlidingMenu.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
			public void onOpened() {

			}
		});

		initView();
		return localSlidingMenu;
	}

	private void initView() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.setting_btn:

			break;

		default:
			break;
		}
	}
}
