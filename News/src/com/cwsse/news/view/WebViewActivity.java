package com.cwsse.news.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cwsse.news.R;
import com.cwsse.news.config.Constants;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class WebViewActivity extends Activity {
	String url;
	@ViewInject(R.id.webview)
	private WebView webview;
	private WebSettings webSet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		url = getIntent().getStringExtra(Constants.URL);
		ViewUtils.inject(this);
		setWebView();
		webview.loadUrl(url);
	}

	private void setWebView() {
		webSet = webview.getSettings();
		/** 支持缩放 */
		webSet.setSupportZoom(true);
		/** 设置默认的缩放级别 */
		webSet.setDefaultZoom(ZoomDensity.MEDIUM);
		/** 是否支持javaScript */
		webSet.setJavaScriptEnabled(true);
		/** 是否支持插件，如flashPlayer \ activityX */
		webSet.setPluginsEnabled(true);
		webview.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			@Override
			/**
			 * 加载新页面的时候回调此方法 
			 */
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
			}

			@Override
			/**
			 * 页面加载完成的时候，回调此方法 
			 */
			public void onPageFinished(WebView view, String url) {
			}
		});
		webview.setWebChromeClient(new WebChromeClient());
	}
}
