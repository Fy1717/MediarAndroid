package com.uk.mediar.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.uk.mediar.Model.User;
import com.uk.mediar.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.UserViewHolder>{
	private Context context;
	private ArrayList<User> users;

	public FollowersAdapter(Context context, ArrayList<User> users) {
		this.context = context;
		this.users = users;
	}
	
	@NonNull
	@Override
	public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		return new UserViewHolder(LayoutInflater.from(context)
		.inflate(R.layout.user_rv_layout, viewGroup, false));
	}
	
	@Override
	public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, @SuppressLint("RecyclerView") int i) {
		User user = users.get(i);

		System.out.println("XXXX : " + user);
		System.out.println("YYYY : " + user.getImage());
		System.out.println("ZZZZ : " + user.getUsername().replace("\"", ""));
		System.out.println(".... : " + user.getName().replace("\"", ""));


		Glide.with(context)
			.load(user.getImage().replace("\"", ""))
			.into(userViewHolder.userImage);

		userViewHolder.userNickname.setText(user.getUsername().replace("\"", ""));
		userViewHolder.userName.setText(user.getName().replace("\"", ""));


		userViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				/*PostDetail selectedPost = PostDetail.getInstance();

				selectedPost.setId(post.getTitle());
				selectedPost.setContent(post.getContent());
				selectedPost.setUserImageUrl(post.getImageUrl());
				selectedPost.setUserName(post.getUsername());
				selectedPost.setImageUrl(post.getImageUrl());
				selectedPost.setPoint(post.getLikeCount() + " liked");

				Intent intent = new Intent(context, PostDetailPage.class);
				context.startActivity(intent);*/
			}
		});
	}
	
	@Override
	public int getItemCount() {
		if (users != null) {
			return users.size();
		}
		return 0;
	}
	
	class UserViewHolder extends RecyclerView.ViewHolder {

		CircleImageView userImage;
		TextView userNickname;
		TextView userName;

		UserViewHolder(@NonNull View itemView) {
			super(itemView);

			userImage = itemView.findViewById(R.id.imgUser);
			userNickname = itemView.findViewById(R.id.username);
			userName = itemView.findViewById(R.id.name);
		}
	}
}
