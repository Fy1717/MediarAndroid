package com.uk.mediar.Model;

import org.json.JSONArray;

import java.util.ArrayList;

public class User {
    private static String token;
    private static String email;
    private static String username;
    private static String name;
    private static String password;
    private static String image;
    private static ArrayList starredShares;
    private static ArrayList followings;
    private static ArrayList followers;
    private static ArrayList<Share> posts;
    private static ArrayList starredPosts;



    private static User user;

    private User() {
        //Constructor have to be in the model
    }

    public static User getInstance() {
        if (user == null) {
            user = new User();
        }

        return user;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        User.token = token;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        User.email = email;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        User.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }

    public static String getImage() {
        return image;
    }

    public static void setImage(String image) {
        User.image = image;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        User.name = name;
    }

    public static ArrayList<Share> getStarredShares() {
        return starredShares;
    }

    public static void setStarredShares(ArrayList<Share> starredShares) {
        User.starredShares = starredShares;
    }

    public static ArrayList getFollowings() {
        return followings;
    }

    public static void setFollowings(ArrayList followings) {
        User.followings = followings;
    }

    public static ArrayList getFollowers() {
        return followers;
    }

    public static void setFollowers(ArrayList followers) {
        User.followers = followers;
    }

    public static ArrayList<Share> getPosts() {
        return posts;
    }

    public static void setPosts(ArrayList<Share> posts) {
        User.posts = posts;
    }

    public static ArrayList getStarredPosts() {
        return starredPosts;
    }

    public static void setStarredPosts(ArrayList starredPosts) {
        User.starredPosts = starredPosts;
    }
}
