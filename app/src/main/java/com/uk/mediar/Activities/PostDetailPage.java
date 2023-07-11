package com.uk.mediar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.uk.mediar.Model.PostDetail;
import com.uk.mediar.Model.User;
import com.uk.mediar.R;

public class PostDetailPage extends AppCompatActivity {

    TextView username, content, likes, caption;
    ImageView userProfileImageView;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        try {
            PostDetail postDetail = PostDetail.getInstance();

            username = findViewById(R.id.username);
            content = findViewById(R.id.tvContent);
            userProfileImageView = findViewById(R.id.profile_image);
            likes = findViewById(R.id.tvLikes);
            caption = findViewById(R.id.tvCaption);
            backButton = findViewById(R.id.back_button);

            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });

            System.out.println("point : " + postDetail.getPoint().replace("\"", ""));

            username.setText(postDetail.getUserName().replace("\"", ""));
            content.setText(postDetail.getContent().replace("\"", ""));
            likes.setText(postDetail.getPoint().replace("\"", ""));
            caption.setText("Software");

            Picasso.get().load(postDetail.getUserImageUrl()).into(userProfileImageView);
        } catch (Exception e) {
            System.out.println("ERROR IN DETAIL PAGE : " + e.getMessage());
        }
    }
}