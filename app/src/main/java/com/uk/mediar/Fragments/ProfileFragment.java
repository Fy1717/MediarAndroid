package com.uk.mediar.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.uk.mediar.Adapters.PostAdapter;
import com.uk.mediar.Adapters.StoryAdapter;
import com.uk.mediar.Model.Post;
import com.uk.mediar.Model.Share;
import com.uk.mediar.Model.Story;
import com.uk.mediar.Model.User;
import com.uk.mediar.R;

import java.util.ArrayList;
import java.util.Objects;

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

	private StoryAdapter storyAdapter;
	
	private RecyclerView rvPosts;
	private PostAdapter postAdapter;
	
	private ArrayList<Story> stories;
	private ArrayList<Post> posts;

	static User user = User.getInstance();

	public String profilePicUrl = user.getImage();
	public String images[] = { profilePicUrl,
			profilePicUrl,
			profilePicUrl,
			profilePicUrl,
			profilePicUrl
	};
	
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
		followers_count = view.findViewById(R.id.followers_count);
		followings_count = view.findViewById(R.id.followings_count);
		total_points = view.findViewById(R.id.total_points);
		profile_url = view.findViewById(R.id.profile_url);
		rvPosts = view.findViewById(R.id.rvPosts);
		stories = new ArrayList<>();
		posts = new ArrayList<>();

		setProfileInfos();

		loadProfilePic();
		populateStories();

		storyAdapter = new StoryAdapter(getContext(), stories);
		
		LinearLayoutManager storiesLayoutManager = new LinearLayoutManager(getContext());
		storiesLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		
		populatePosts();
		postAdapter = new PostAdapter(getContext(), posts);
		StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
		rvPosts.setLayoutManager(staggeredGridLayoutManager);
		rvPosts.setAdapter(postAdapter);
		rvPosts.setNestedScrollingEnabled(false);
	}
	
	private void loadProfilePic() {
		if (!isDetached() && getContext() != null) {
			Glide.with(getContext())
				.load(profilePicUrl)
				.into(profilePic);
		}
	}

	private void setProfileInfos() {
		username.setText(user.getUsername());
		name.setText(user.getName());
		posts_count.setText(String.valueOf(user.getPosts().size()));
		followers_count.setText(String.valueOf(user.getFollowers().size()));
		followings_count.setText(String.valueOf(user.getFollowings().size()));
		total_points.setText(String.valueOf((int) user.getTotalPoints()));
		profile_url.setText("www.mediar.com/" + user.getUsername());
	}

	private void populateStories() {
		try {
			for (int i = 0; i < user.getPosts().size(); i++) {
				stories.add(new Story(profilePicUrl, false));
			}
		} catch (Exception e) {}
	}

	private void populatePosts() {
		try {
			for (int i = 0; i < user.getPosts().size(); i++) {
				String storyImage = images[i%5];
				posts.add(new Post(storyImage));
			}
		} catch (Exception e) {}
	}
}