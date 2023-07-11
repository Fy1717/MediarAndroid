package com.uk.mediar.Model;

public class PostDetail {
    public static PostDetail postDetail = new PostDetail();

    public String Content;
    public String Id;
    public String Point;
    public String ImageUrl;

    public String UserName;

    public String UserImageUrl;



    public static PostDetail getInstance() {
        if (postDetail == null) {
            postDetail = new PostDetail();
        }

        return postDetail;
    }


    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPoint() {
        return Point;
    }

    public void setPoint(String point) {
        Point = point;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserImageUrl() {
        return UserImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        UserImageUrl = userImageUrl;
    }
}
