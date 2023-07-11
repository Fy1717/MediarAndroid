package com.uk.mediar.Model;

public class Post {
	private String imageUrl;
	private String title;
	private String content;
	private int likeCount;

	private String username;

	
	public Post(String imageUrl, String title, String content, int likeCount, String username) {
		this.imageUrl = imageUrl;
		this.title = title;
		this.content = content;
		this.likeCount = likeCount;
		this.username = username;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
