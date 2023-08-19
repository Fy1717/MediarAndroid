package com.uk.mediar.Model;

public class TimelinePost {
	private String profilePic;
	private String name;
	private String imageUrl;
	private int likes;
	private String content;
	private String date;

	private int id;
	
	public TimelinePost(int id, String profilePic, String name, String imageUrl, int likes, String content, String date) {
		this.id = id;
		this.profilePic = profilePic;
		this.name = name;
		this.imageUrl = imageUrl;
		this.likes = likes;
		this.content = content;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getProfilePic() {
		return profilePic;
	}
	
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public int getLikes() {
		return likes;
	}
	
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

}
