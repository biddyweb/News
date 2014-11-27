package com.cwsse.news.bean;

import java.io.Serializable;
import java.util.List;

public class NewsEntity implements Serializable {
	/** 新闻类别 ID */
	private Integer newsCategoryId;
	/** 新闻类型 */
	private String newsCategory;
	/** 新闻ID */
	private Integer newsId;
	/** 标题 */
	private String title;
	/** 新闻源 */
	private String source;
	/** 发布时间 */
	private Long publishTime;
	/** 特殊标签，如广告推广之类的 ，可以为空 */
	private String local;
	/** 图片列表字符串 */
	private String picListString;
	/** 图片 列表 */
	private List<String> picList;
	/** 图片类型是否为大图 */
	private Boolean isLarge;
	/** 阅读状态 ，读过的话显示灰色背景 */
	private Boolean readStatus;

	/** url */
	private String url;

	public Integer getNewsCategoryId() {
		return newsCategoryId;
	}

	public void setNewsCategoryId(Integer newsCategoryId) {
		this.newsCategoryId = newsCategoryId;
	}

	public String getNewsCategory() {
		return newsCategory;
	}

	public void setNewsCategory(String newsCategory) {
		this.newsCategory = newsCategory;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Long getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Long publishTime) {
		this.publishTime = publishTime;
	}

	public String getPicListString() {
		return picListString;
	}

	public void setPicListString(String picListString) {
		this.picListString = picListString;
	}

	public List<String> getPicList() {
		return picList;
	}

	public void setPicList(List<String> picList) {
		this.picList = picList;
	}

	public Boolean getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(Boolean readStatus) {
		this.readStatus = readStatus;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Boolean getIsLarge() {
		return isLarge;
	}

	public void setIsLarge(Boolean isLarge) {
		this.isLarge = isLarge;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
