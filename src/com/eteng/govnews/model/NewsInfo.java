package com.eteng.govnews.model;

public class NewsInfo {

	/**
	 * 新闻ID
	 */
	private String newsId;
	/**
	 * 新闻标题
	 */
	private String newsTitle;
	/**
	 * 新闻内容
	 */
	private String newsContent;
	/**
	 * 新闻发布日期
	 */
	private String newsDate;
	/**
	 * 新闻类型
	 */
	private String newsCategoty;
	
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public String getNewsDate() {
		return newsDate;
	}
	public void setNewsDate(String newsDate) {
		this.newsDate = newsDate;
	}
	public String getNewsCategoty() {
		return newsCategoty;
	}
	public void setNewsCategoty(String newsCategoty) {
		this.newsCategoty = newsCategoty;
	}
}
