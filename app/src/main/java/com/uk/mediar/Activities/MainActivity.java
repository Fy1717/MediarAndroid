package com.uk.mediar.Activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.uk.mediar.Fragments.HomeFragment;
import com.uk.mediar.Fragments.NotificationsFragment;
import com.uk.mediar.Fragments.ProfileFragment;
import com.uk.mediar.Fragments.SearchFragment;
import com.uk.mediar.R;

public class MainActivity extends AppCompatActivity {
	
	//Random image urls below
	public static final String profilePicUrl = "https://media.licdn.com/dms/image/C4D03AQE_xWcyzomadw/profile-displayphoto-shrink_800_800/0/1649255113460?e=2147483647&v=beta&t=vaLRGCe6Dcuql4J8wEjj9v1iXgknmWQfsL40pgToAGc";
	public static final String images[] = {profilePicUrl,
		"https://media.licdn.com/dms/image/C4D03AQE_xWcyzomadw/profile-displayphoto-shrink_800_800/0/1649255113460?e=2147483647&v=beta&t=vaLRGCe6Dcuql4J8wEjj9v1iXgknmWQfsL40pgToAGc",
		"https://media.licdn.com/dms/image/C4D03AQE_xWcyzomadw/profile-displayphoto-shrink_800_800/0/1649255113460?e=2147483647&v=beta&t=vaLRGCe6Dcuql4J8wEjj9v1iXgknmWQfsL40pgToAGc",
		"https://media.licdn.com/dms/image/C4D03AQE_xWcyzomadw/profile-displayphoto-shrink_800_800/0/1649255113460?e=2147483647&v=beta&t=vaLRGCe6Dcuql4J8wEjj9v1iXgknmWQfsL40pgToAGc",
		"https://media.licdn.com/dms/image/C4D03AQE_xWcyzomadw/profile-displayphoto-shrink_800_800/0/1649255113460?e=2147483647&v=beta&t=vaLRGCe6Dcuql4J8wEjj9v1iXgknmWQfsL40pgToAGc"
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initialize();
	}
	
	
	private void initialize() {
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		
		BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
		
		loadFragment(new HomeFragment());               //Default is home fragment
		
		bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
				
				switch (menuItem.getItemId()) {
					case R.id.home:
						return loadFragment(new HomeFragment());
					case R.id.search:
						return loadFragment(new SearchFragment());
					case R.id.notifications:
						return loadFragment(new NotificationsFragment());
					case R.id.profile:
						return loadFragment(new ProfileFragment());
				}
				
				return false;
			}
		});
	}
	
	
	private boolean loadFragment(Fragment fragment){
		
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
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}
}
