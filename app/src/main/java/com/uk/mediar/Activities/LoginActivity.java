package com.uk.mediar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.uk.mediar.Model.User;
import com.uk.mediar.R;
import com.uk.mediar.Service.Request.Login;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button login;
    View loadingLayout;
    String emailS;
    String passwordS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Window window = getWindow();
        //new FullScreenAdapter(window, Color.parseColor("#f0f1f2"));

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);
        loadingLayout = findViewById(R.id.loading_layout);

        email.requestFocus();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailS = email.getText().toString();
                passwordS = password.getText().toString();

                if(!emailS.equals("") && !passwordS.equals("")) {
                    LoginActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            loadingLayout.setVisibility(View.VISIBLE);
                        }
                    });

                    controlLogin(emailS, passwordS);
                    //new LoginRequest().execute(emailS, passwordS);
                    //new AsyncTask().execute();
                }
            }
        });
    }

    public void controlLogin(String username, String password) {
        User user = User.getInstance();
        user.setUsername(username);
        user.setPassword(password);

        Login model = new ViewModelProvider(this).get(Login.class);

        model.getLoginStatus(user.getUsername(), user.getPassword())
                .observe(this, state -> {
                    Log.i("LOGIN", "STATE : " + state);

                    if (state) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        // TODO: User should be informated for some errors
                    }
                });
    }
}