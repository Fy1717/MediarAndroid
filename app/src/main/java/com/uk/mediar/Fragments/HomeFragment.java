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

import com.uk.mediar.Activities.MainActivity;
import com.uk.mediar.Adapters.TimelinePostAdapter;
import com.uk.mediar.Model.TimelinePost;
import com.uk.mediar.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
	
	private RecyclerView rvPosts;
	private TimelinePostAdapter postAdapter;
	private ArrayList<TimelinePost> posts;
	
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
		posts.add(new TimelinePost(MainActivity.images[0], "frkann.17", MainActivity.images[1],370, "No caption", "10 min ago"));
		posts.add(new TimelinePost(MainActivity.images[3], "frkann.17", MainActivity.images[3],244, "And no need caption", "2 months ago"));
		
		rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
		
		postAdapter = new TimelinePostAdapter(getContext(), posts);
		rvPosts.setAdapter(postAdapter);
		
	}
}
