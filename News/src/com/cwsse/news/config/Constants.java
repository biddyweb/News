package com.cwsse.news.config;

import java.util.ArrayList;
import java.util.List;

import com.cwsse.news.bean.NewsClassify;
import com.cwsse.news.bean.NewsEntity;

public class Constants {
	public static final String URL = "url";

	public static ArrayList<NewsClassify> getData() {
		ArrayList<NewsClassify> newsClassify = new ArrayList<NewsClassify>();
		NewsClassify classify = new NewsClassify();
		classify.setId(0);
		classify.setTitle("推荐");
		newsClassify.add(classify);
		classify = new NewsClassify();
		classify.setId(1);
		classify.setTitle("热点");
		newsClassify.add(classify);
		classify = new NewsClassify();
		classify.setId(2);
		classify.setTitle("数码");
		newsClassify.add(classify);
		classify = new NewsClassify();
		classify.setId(3);
		classify.setTitle("杭州");
		newsClassify.add(classify);
		classify = new NewsClassify();
		classify.setId(4);
		classify.setTitle("社会");
		newsClassify.add(classify);
		classify = new NewsClassify();
		classify.setId(5);
		classify.setTitle("娱乐");
		newsClassify.add(classify);
		classify = new NewsClassify();
		classify.setId(6);
		classify.setTitle("科技");
		newsClassify.add(classify);
		classify = new NewsClassify();
		classify.setId(7);
		classify.setTitle("汽车");
		newsClassify.add(classify);
		return newsClassify;
	}

	public static ArrayList<NewsEntity> getNewsList() {
		ArrayList<NewsEntity> newsList = new ArrayList<NewsEntity>();
		String url = "http://r3.sinaimg.cn/2/2014/0417/a7/6/92478595/580x1000x75x0.jpg";
		String url0 = "http://imgt2.bdstatic.com/it/u=3269155243,2604389213&fm=21&gp=0.jpg";
		String url1 = "http://infopic.gtimg.com/qq_news/digi/pics/102/102066/102066094_400_640.jpg";
		String url2 = "http://infopic.gtimg.com/qq_news/digi/pics/102/102066/102066096_400_640.jpg";
		String url3 = "http://infopic.gtimg.com/qq_news/digi/pics/102/102066/102066099_400_640.jpg";
		for (int i = 0; i < 10; i++) {
			NewsEntity news = new NewsEntity();
			news.setNewsCategory("推荐");
			news.setNewsCategoryId(1);
			news.setNewsId(i);
			news.setTitle("可以用谷歌眼镜做的10件酷事：导航、玩游戏");
			List<String> url_list = new ArrayList<String>();
			if (i % 2 == 1) {
				url_list.add(url1);
				url_list.add(url2);
				url_list.add(url3);
			} else {
				news.setTitle("AA用车:智能短租租车平台");
				url_list.add(url);
			}
			news.setPicList(url_list);
			news.setPublishTime(Long.valueOf(i));
			news.setReadStatus(false);
			news.setSource("丛义明");
			if (i == 4) {
				news.setTitle("部落战争强势回归");
				news.setLocal("推广");
				news.setIsLarge(true);
				url_list.clear();
				url_list.add(url0);
			} else {
				news.setIsLarge(false);
			}
			news.setUrl("http://m.baidu.com/");
			newsList.add(news);
		}
		return newsList;
	}
}
