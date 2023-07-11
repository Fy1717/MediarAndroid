package com.uk.mediar.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uk.mediar.Activities.PostDetailPage;
import com.uk.mediar.Model.Post;
import com.uk.mediar.Model.PostDetail;
import com.uk.mediar.Model.Share;
import com.uk.mediar.R;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{
	private Context context;
	private ArrayList<Post> posts;
	
	public PostAdapter(Context context, ArrayList<Post> posts) {
		this.context = context;
		this.posts = posts;
	}
	
	@NonNull
	@Override
	public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		return new PostViewHolder(LayoutInflater.from(context)
		.inflate(R.layout.post_layout, viewGroup, false));
	}
	
	@Override
	public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, @SuppressLint("RecyclerView") int i) {
		Post post = posts.get(i);
		
		Glide.with(context)
			.load(post.getImageUrl())
			.into(postViewHolder.postImage);

		String postContent = post.getContent().replace("\"", "");
		if (postContent.length() > 25) {
			postContent = postContent.substring(0, 25) + " ...";
		}

		postViewHolder.postTitle.setText(post.getTitle().replace("\"", ""));
		postViewHolder.postContent.setText(postContent);
		postViewHolder.postLikeCount.setText(post.getLikeCount() + " liked");

		postViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				PostDetail selectedPost = PostDetail.getInstance();

				selectedPost.setId(post.getTitle());
				selectedPost.setContent(post.getContent());
				selectedPost.setUserImageUrl(post.getImageUrl());
				selectedPost.setUserName(post.getUsername());
				selectedPost.setImageUrl(post.getImageUrl());
				selectedPost.setPoint(post.getLikeCount() + " liked");

				Intent intent = new Intent(context, PostDetailPage.class);
				context.startActivity(intent);
			}
		});
	}
	
	@Override
	public int getItemCount() {
		if (posts != null) {
			return posts.size();
		}
		return 0;
	}
	
	class PostViewHolder extends RecyclerView.ViewHolder {
		
		ImageView postImage;
		TextView postTitle;
		TextView postContent;
		TextView postLikeCount;

		PostViewHolder(@NonNull View itemView) {
			super(itemView);
			postImage = itemView.findViewById(R.id.imgPost);
			postTitle = itemView.findViewById(R.id.title);
			postContent = itemView.findViewById(R.id.content);
			postLikeCount = itemView.findViewById(R.id.point);
		}
	}
}
