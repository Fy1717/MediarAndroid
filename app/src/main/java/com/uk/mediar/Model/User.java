package com.uk.mediar.Model;

import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.util.ArrayList;

public class User {
    private static String token;
    private static String email;
    private static String username;
    private static String name;
    private static String password;
    private static String image;
    private static JsonArray starredShares;
    private static JsonArray followings;
    private static JsonArray followers;
    private static JsonArray posts;
    private static JsonArray starredPosts;
    private static double totalPoints;

    private static User user;

    public User() {
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

    public static JsonArray getStarredShares() {
        return starredShares;
    }

    public static void setStarredShares(JsonArray starredShares) {
        User.starredShares = starredShares;
    }

    public static JsonArray getFollowings() {
        return followings;
    }

    public static void setFollowings(JsonArray followings) {
        User.followings = followings;
    }

    public static JsonArray getFollowers() {
        return followers;
    }

    public static void setFollowers(JsonArray followers) {
        User.followers = followers;
    }

    public static JsonArray getPosts() {
        return posts;
    }

    public static void setPosts(JsonArray posts) {
        User.posts = posts;
    }

    public static JsonArray getStarredPosts() {
        return starredPosts;
    }

    public static void setStarredPosts(JsonArray starredPosts) {
        User.starredPosts = starredPosts;
    }

    public static double getTotalPoints() {
        return totalPoints;
    }

    public static void setTotalPoints(double totalPoints) {
        User.totalPoints = totalPoints;
    }
}
