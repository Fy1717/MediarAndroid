package com.uk.mediar.Adapters;

import static com.uk.mediar.Service.ApiModel.ErrorHandlerModel.errorHandlerModel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.uk.mediar.Activities.PostDetailPage;
import com.uk.mediar.Controllers.StarredShareChecker;
import com.uk.mediar.Model.PostDetail;
import com.uk.mediar.Model.TimelinePost;
import com.uk.mediar.Model.User;
import com.uk.mediar.R;
import com.uk.mediar.Service.Request.StarPost;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TimelinePostAdapter extends RecyclerView.Adapter<TimelinePostAdapter.PostViewHolder> {
	
	private Context context;
	private ArrayList<TimelinePost> posts;
	private JsonArray starredShares;
	User user = User.getInstance();

	
	public TimelinePostAdapter(Context context, ArrayList<TimelinePost> posts) {
		this.context = context;
		this.posts = posts;
		this.starredShares = user.getStarredShares();
	}
	
	@NonNull
	@Override
	public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		return new PostViewHolder(LayoutInflater.from(context)
			.inflate(R.layout.home_post_layout, viewGroup, false));
	}
	
	@Override
	public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int i) {
		try {
			TimelinePost post = posts.get(i);

			Glide.with(context)
					.load(post.getProfilePic())
					.into(postViewHolder.profilePic);

			Glide.with(context)
					.load(post.getImageUrl())
					.into(postViewHolder.imgPost);

			postViewHolder.tvName.setText(post.getName().replace("\"", ""));
			postViewHolder.tvLikes.setText(post.getLikes() + " likes");
			postViewHolder.tvContent.setText(post.getContent().replace("\"", "").substring(0, 10) + "...");
			postViewHolder.tvCaption.setText("Category: Software");
			postViewHolder.tvDate.setText(post.getDate());

			Boolean isInStarreds = StarredShareChecker.postInMyStarred(post.getId());
			System.out.println("POST ID : " + post.getId() + " - LIKED ? : " + isInStarreds);

			if (isInStarreds) {
				postViewHolder.imageViewLike.setBackgroundResource(R.drawable.liked);
			}

			postViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					PostDetail selectedPost = PostDetail.getInstance();

					selectedPost.setId(String.valueOf(post.getId()));
					selectedPost.setContent(post.getContent().replace("\"", ""));
					selectedPost.setPoint(post.getLikes() + " likes");
					selectedPost.setImageUrl(post.getImageUrl().replace("\"", ""));
					selectedPost.setUserImageUrl(post.getProfilePic().replace("\"", ""));
					selectedPost.setUserName(post.getName().replace("\"", ""));

					Intent intent = new Intent(context, PostDetailPage.class);
					context.startActivity(intent);
				}
			});

			postViewHolder.imageViewLike.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					controlStarPost(postViewHolder.imageViewLike, post.getId(), true, post);
					postViewHolder.tvLikes.setText(post.getLikes() + " likes");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();

			System.out.println("Exception e : " + e.getMessage());
		}
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
		ImageView imgPost, imageViewLike;
		
		PostViewHolder(@NonNull View itemView) {
			super(itemView);
			profilePic = itemView.findViewById(R.id.profile_image);
			tvName = itemView.findViewById(R.id.tvName);
			imgPost = itemView.findViewById(R.id.imgPost);
			imageViewLike = itemView.findViewById(R.id.imageViewLike);
			tvLikes = itemView.findViewById(R.id.tvLikes);
			tvCaption = itemView.findViewById(R.id.tvCaption);
			tvContent = itemView.findViewById(R.id.tvContent);
			tvDate = itemView.findViewById(R.id.tvDate);
			
		}
	}

	public void controlStarPost(ImageView imageViewLike, int postId, Boolean star, TimelinePost currentPost) {
		try {
			StarPost model = new ViewModelProvider((ViewModelStoreOwner) context).get(StarPost.class);

			System.out.println("TOKEN : " + user.getToken());
			System.out.println("POST-ID : " + String.valueOf(postId));
			System.out.println("STAR : " + String.valueOf(star));

			model.getStarPostStatus(user.getToken(), String.valueOf(postId), star)
					.observe((LifecycleOwner) context, state -> {
						Log.i("STAR", "STATE : " + state);

						System.out.println("STATE : " + state);

						if (state) {
							if (star) {
								currentPost.setLikes(currentPost.getLikes() + 1);
								imageViewLike.setBackgroundResource(R.drawable.liked);
							} else {
								imageViewLike.setBackgroundResource(R.drawable.ic_action_like_border);
							}

						} else {
							if (!star) {
								currentPost.setLikes(currentPost.getLikes() - 1);
								imageViewLike.setBackgroundResource(R.drawable.ic_action_like_border);
							} else {
								imageViewLike.setBackgroundResource(R.drawable.liked);
							}

							if (errorHandlerModel.getStarPostErrorMessage() != null && !errorHandlerModel.getLoginErrorMessage().equals("")) {
								Toast.makeText(context, errorHandlerModel.getLoginErrorMessage(), Toast.LENGTH_LONG).show();
							}
						}
					});
		} catch (Exception e) {
			e.printStackTrace();

			System.out.println("Exception : " + e.getMessage());
		}
	}
}