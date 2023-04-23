package com.uk.mediar.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.uk.mediar.Fragments.HomeFragment;
import com.uk.mediar.Fragments.NotificationsFragment;
import com.uk.mediar.Fragments.ProfileFragment;
import com.uk.mediar.Fragments.SearchFragment;
import com.uk.mediar.Model.User;
import com.uk.mediar.R;
import com.uk.mediar.Service.ApiModel.ErrorHandlerModel;
import com.uk.mediar.Service.Request.Login;
import com.uk.mediar.Service.Request.Logout;

public class MainActivity extends AppCompatActivity {
	public static String CurrentFragmentName = "HOME";
	public static Menu CurrentMenu;

	ErrorHandlerModel errorHandlerModel = ErrorHandlerModel.getInstance();
	User user = User.getInstance();

	Toolbar toolbar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		initialize();
	}

	private void initialize() {
		BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
		
		loadFragment(new HomeFragment());
		
		bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
				
				switch (menuItem.getItemId()) {
					case R.id.home:
						CurrentFragmentName = "HOME";

						return loadFragment(new HomeFragment());
					case R.id.search:
						CurrentFragmentName = "SEARCH";

						return loadFragment(new SearchFragment());
					case R.id.notifications:
						CurrentFragmentName = "NOTIFICATION";

						return loadFragment(new NotificationsFragment());
					case R.id.profile:
						CurrentFragmentName = "PROFILE";

						return loadFragment(new ProfileFragment());
				}
				
				return false;
			}
		});
	}
	
	
	private boolean loadFragment(Fragment fragment) {
		if (fragment != null) {
			FragmentManager fm  = getSupportFragmentManager();
			fm.beginTransaction()
				.replace(R.id.container, fragment)
				.commit();
			return true;
		}
		return false;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		CurrentMenu = menu;

		MenuInflater inflater = getMenuInflater();

		//inflater.inflate(R.menu.messages_menu, menu);
		inflater.inflate(R.menu.profile_menu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.logout:
				System.out.println("LOGOUT CLICKED");
				errorHandlerModel.setLogoutErrorMessage(null);

				controlLogout();

				return true;

			default:
				return super.onOptionsItemSelected(item);
		}
	}

	public void controlLogout() {
		Intent intent = new Intent(MainActivity.this, LoginActivity.class);
		startActivity(intent);

		Logout model = new ViewModelProvider(this).get(Logout.class);

		model.getLogoutStatus(user.getToken())
				.observe(this, state -> {
					Log.i("LOGOUT", "STATE : " + state);

					user.setToken(null);
					user.setUsername(null);
					user.setPassword(null);
					user.setEmail(null);
					user.setImage(null);

					if (state == false) {
						if (errorHandlerModel.getLogoutErrorMessage() != null && !errorHandlerModel.getLogoutErrorMessage().equals("")) {
							Toast.makeText(MainActivity.this, errorHandlerModel.getLogoutErrorMessage(), Toast.LENGTH_LONG).show();
						}
					}
				});
	}
}
