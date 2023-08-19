package com.uk.mediar.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.uk.mediar.Adapters.FollowersAdapter;
import com.uk.mediar.Adapters.FollowingsAdapter;
import com.uk.mediar.Adapters.PostAdapter;
import com.uk.mediar.Model.Post;
import com.uk.mediar.Model.User;
import com.uk.mediar.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class ProfileFragment extends Fragment {
	
	private CircleImageView profilePic;
	private TextView username, profile_url, name, followings_count, followers_count, posts_count, total_points;
	private View posts_count_area, total_points_area, followers_count_area, followings_count_area;

	private RecyclerView rvPosts;
	private RecyclerView rvFollowings, rvFollowers;
	private PostAdapter postAdapter;
	private FollowersAdapter followersAdapter;
	private FollowingsAdapter followingsAdapter;
	private ArrayList<Post> posts;
	private ArrayList<User> followers, followings;

	static User user = User.getInstance();


	public String profilePicUrl = user.getImage();
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.profile_layout, container, false);
	}
	
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		profilePic = view.findViewById(R.id.profile_image);
		username = view.findViewById(R.id.username);
		name = view.findViewById(R.id.name);

		posts_count = view.findViewById(R.id.posts_count);
		posts_count_area = view.findViewById(R.id.posts_count_area);

		followers_count = view.findViewById(R.id.followers_count);
		followers_count_area = view.findViewById(R.id.followers_count_area);

		followings_count = view.findViewById(R.id.followings_count);
		followings_count_area = view.findViewById(R.id.followings_count_area);

		total_points = view.findViewById(R.id.total_points);
		profile_url = view.findViewById(R.id.profile_url);

		rvPosts = view.findViewById(R.id.rvPosts);
		posts = new ArrayList<>();

		rvFollowers = view.findViewById(R.id.rvFollowersProfile);
		followers = new ArrayList<>();

		rvFollowings = view.findViewById(R.id.rvFollowingsProfile);
		followings = new ArrayList<>();

		setProfileInfos();

		loadProfilePic();

		LinearLayoutManager storiesLayoutManager = new LinearLayoutManager(getContext());
		storiesLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		
		populatePosts();
		postAdapter = new PostAdapter(getContext(), posts);
		StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
		rvPosts.setLayoutManager(staggeredGridLayoutManager);
		rvPosts.setAdapter(postAdapter);
		rvPosts.setNestedScrollingEnabled(false);

		populateFollowers();
		followersAdapter = new FollowersAdapter(getContext(), followers);
		StaggeredGridLayoutManager staggeredGridLayoutManager2 = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);
		rvFollowers.setLayoutManager(staggeredGridLayoutManager2);
		rvFollowers.setAdapter(followersAdapter);
		rvFollowers.setNestedScrollingEnabled(false);

		populateFollowings();
		followingsAdapter = new FollowingsAdapter(getContext(), followings);
		StaggeredGridLayoutManager staggeredGridLayoutManager3 = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);
		rvFollowings.setLayoutManager(staggeredGridLayoutManager3);
		rvFollowings.setAdapter(followingsAdapter);
		rvFollowings.setNestedScrollingEnabled(false);
	}
	
	private void loadProfilePic() {
		if (!isDetached() && getContext() != null) {
			Glide.with(getContext())
				.load(profilePicUrl)
				.into(profilePic);
		}
	}

	private void setProfileInfos() {
		username.setText("@" + user.getUsername().replaceAll("\"",""));
		name.setText(user.getName().replaceAll("\"",""));
		posts_count.setText(String.valueOf(user.getPosts().size()));
		followers_count.setText(String.valueOf(user.getFollowers().size()));
		followings_count.setText(String.valueOf(user.getFollowings().size()));
		total_points.setText(String.valueOf((int) user.getTotalPoints()));
		profile_url.setText("www.mediar.com/" + user.getUsername().replace("\"", ""));

		posts_count_area.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				rvPosts.setVisibility(View.VISIBLE);
				rvFollowers.setVisibility(View.GONE);
				rvFollowings.setVisibility(View.GONE);

				posts_count_area.setBackgroundResource(R.drawable.white_area_modal);
				followers_count_area.setBackgroundResource(R.color.mediar_extra_light_gray);
				followings_count_area.setBackgroundResource(R.color.mediar_extra_light_gray);
			}
		});

		followers_count_area.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				rvPosts.setVisibility(View.GONE);
				rvFollowers.setVisibility(View.VISIBLE);
				rvFollowings.setVisibility(View.GONE);

				followers_count_area.setBackgroundResource(R.drawable.white_area_modal);
				posts_count_area.setBackgroundResource(R.color.mediar_extra_light_gray);
				followings_count_area.setBackgroundResource(R.color.mediar_extra_light_gray);
			}
		});

		followings_count_area.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				rvPosts.setVisibility(View.GONE);
				rvFollowers.setVisibility(View.GONE);
				rvFollowings.setVisibility(View.VISIBLE);

				followings_count_area.setBackgroundResource(R.drawable.white_area_modal);
				followers_count_area.setBackgroundResource(R.color.mediar_extra_light_gray);
				posts_count_area.setBackgroundResource(R.color.mediar_extra_light_gray);
			}
		});
	}

	private void populatePosts() {
		try {
			int totalLike = 0;

			for (int i = 0; i < user.getPosts().size(); i++) {

				JsonObject post = (JsonObject) user.getPosts().get(i);

				System.out.println("POST : " + post);

				System.out.println("Post Content : " + post.get("Content"));

				posts.add(new Post(profilePicUrl, post.get("Content").toString().substring(0, 10), post.get("Content").toString(), post.get("Point").getAsInt(), user.getUsername()));

				totalLike += post.get("Point").getAsInt();
			}

			user.setTotalPoints(totalLike);
			total_points.setText(String.valueOf((int) user.getTotalPoints()));
		} catch (Exception e) {
			System.out.println("ERROR : " + e.getLocalizedMessage());
		}
	}

	private void populateFollowers() {
		try {
			for (int i = 0; i < user.getFollowers().size(); i++) {

				JsonObject followerUser = (JsonObject) user.getFollowers().get(i);

				System.out.println("Following User  : " + followerUser.get("Name"));

				User follower = new User();
				follower.setName(String.valueOf(followerUser.get("Name")));
				follower.setUsername(String.valueOf(followerUser.get("Username")));
				follower.setImage(String.valueOf(followerUser.get("Image")));

				followers.add(follower);
			}
		} catch (Exception e) {
			System.out.println("ERROR : " + e.getLocalizedMessage());
		}
	}

	private void populateFollowings() {
		try {
			for (int i = 0; i < user.getFollowings().size(); i++) {

				JsonObject followingUser = (JsonObject) user.getFollowings().get(i);

				System.out.println("Following User  : " + followingUser.get("Name"));

				User following = new User();
				following.setName(String.valueOf(followingUser.get("Name")));
				following.setUsername(String.valueOf(followingUser.get("Username")));
				following.setImage(String.valueOf(followingUser.get("Image")));

				followings.add(following);
			}
		} catch (Exception e) {
			System.out.println("ERROR : " + e.getLocalizedMessage());
		}
	}
}