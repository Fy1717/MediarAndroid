package com.uk.mediar.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.uk.mediar.Activities.MainActivity;
import com.uk.mediar.Adapters.TimelinePostAdapter;
import com.uk.mediar.Model.Share;
import com.uk.mediar.Model.TimelinePost;
import com.uk.mediar.Model.User;
import com.uk.mediar.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
	
	private RecyclerView rvPosts;
	private TimelinePostAdapter postAdapter;
	private ArrayList<TimelinePost> posts;

	User user = User.getInstance();
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.home_layout, container, false);
	}
	
	
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		
		posts = new ArrayList<>();
		rvPosts = view.findViewById(R.id.rvPosts);
		
		//Populating posts

		System.out.println("USER FOLLOWINGS : " + user.getFollowings().size() + user.getFollowings());

		for (int i = 0; i < user.getFollowings().size(); i++) {
			System.out.println("Following User: " + user.getFollowings().get(i) + user.getFollowings().get(i));

			JsonObject followingUser = user.getFollowings().get(i).getAsJsonObject();

			System.out.println("FollowingUserShares : " + followingUser.get("Shares"));

			JsonArray followingUserShares = followingUser.get("Shares").getAsJsonArray();

			for (int j = 0; j < followingUserShares.size(); j++) {
				JsonObject followingUserShare = followingUserShares.get(j).getAsJsonObject();

				System.out.println("Following User Share : " + followingUserShare);

				posts.add(new TimelinePost(followingUser.get("Image").toString().replace("\"", ""), followingUser.get("Username").toString(), followingUser.get("Image").toString().replace("\"", ""), followingUserShare.get("point").getAsInt(), followingUserShare.get("content").toString(), "10 min ago"));
			}
		}

		//posts.add(new TimelinePost(user.getImage(), user.getUsername(), user.getImage(),370, "No caption", "10 min ago"));
		//posts.add(new TimelinePost(user.getImage(), user.getUsername(), user.getImage(),244, "And no need caption", "2 months ago"));
		
		rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
		
		postAdapter = new TimelinePostAdapter(getContext(), posts);
		rvPosts.setAdapter(postAdapter);
	}
}
