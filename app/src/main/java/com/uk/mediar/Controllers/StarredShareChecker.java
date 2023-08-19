package com.uk.mediar.Controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.uk.mediar.Model.User;

public class StarredShareChecker {
    public static JsonArray starredShareArray = User.getInstance().getStarredShares();

    public static Boolean postInMyStarred(int postId) {
        for (int i = 0; i < starredShareArray.size(); i++) {
            try {
                JsonObject starredShare = (JsonObject) starredShareArray.get(i);

                //System.out.println("POST ID : " + postId);
                //System.out.println("Starred ID : " + Integer.valueOf(String.valueOf(starredShare.get("id"))));

                if (postId == Integer.parseInt(String.valueOf(starredShare.get("id")))) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();

                return false;
            }
        }

        return false;
    }
}
