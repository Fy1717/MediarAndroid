package com.uk.mediar.Model;

public class User {
    private static String token;
    private static String email;
    private static String username;
    private static String password;

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
}
