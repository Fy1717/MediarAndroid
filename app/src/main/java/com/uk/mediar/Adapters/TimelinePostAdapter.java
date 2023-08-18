package com.uk.mediar.Adapters;

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
import com.uk.mediar.Model.PostDetail;
import com.uk.mediar.Model.TimelinePost;
import com.uk.mediar.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TimelinePostAdapter extends RecyclerView.Adapter<TimelinePostAdapter.PostViewHolder> {
	
	private Context context;
	private ArrayList<TimelinePost> posts;
	
	public TimelinePostAdapter(Context context, ArrayList<TimelinePost> posts) {
		this.context = context;
		this.posts = posts;
	}
	
	@NonNull
	@Override
	public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		return new PostViewHolder(LayoutInflater.from(context)
			.inflate(R.layout.home_post_layout, viewGroup, false));
	}
	
	@Override
	public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int i) {
		TimelinePost post = posts.get(i);
		
		Glide.with(context)
			.load(post.getProfilePic())
			.into(postViewHolder.profilePic);
		

		Glide.with(context)
			.load(post.getImageUrl())
			.into(postViewHolder.imgPost);
		
		postViewHolder.tvName.setText(post.getName().replace("\"", ""));
		postViewHolder.tvLikes.setText(post.getLikes() + " likes");
		postViewHolder.tvContent.setText(post.getContent().replace("\"", ""));
		postViewHolder.tvCaption.setText("Category: Software");
		postViewHolder.tvDate.setText(post.getDate());

		postViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				PostDetail selectedPost = PostDetail.getInstance();

				selectedPost.setId(post.getName().replace("\"", ""));
				selectedPost.setContent(post.getContent().replace("\"", "").substring(0, 10) + "...");
				selectedPost.setPoint(post.getLikes() + " likes");
				selectedPost.setImageUrl(post.getImageUrl().replace("\"", ""));
				selectedPost.setUserImageUrl(post.getProfilePic().replace("\"", ""));
				selectedPost.setUserName(post.getName().replace("\"", ""));

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
		CircleImageView profilePic;
		TextView tvName, tvLikes, tvCaption, tvDate, tvContent;
		ImageView imgPost;
		
		PostViewHolder(@NonNull View itemView) {
			super(itemView);
			profilePic = itemView.findViewById(R.id.profile_image);
			tvName = itemView.findViewById(R.id.tvName);
			imgPost = itemView.findViewById(R.id.imgPost);
			tvLikes = itemView.findViewById(R.id.tvLikes);
			tvCaption = itemView.findViewById(R.id.tvCaption);
			tvContent = itemView.findViewById(R.id.tvContent);
			tvDate = itemView.findViewById(R.id.tvDate);
			
		}
	}
}