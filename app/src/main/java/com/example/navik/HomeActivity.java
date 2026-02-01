package com.example.navik;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {
    
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView menuIcon;
    private TextView userName, drawerUserName, drawerUserEmail;
    private CardView cardCareerExploration, cardMentorCommunity, cardReport, cardRoadmap;
    private FloatingActionButton chatButton;
    private LinearLayout navHome, navFiles, navBooks, navProfile;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        initializeViews();
        loadUserData();
        setupClickListeners();
        setupDrawer();
    }
    
    private void initializeViews() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        menuIcon = findViewById(R.id.menuIcon);
        userName = findViewById(R.id.userName);
        
        View headerView = navigationView.getHeaderView(0);
        drawerUserName = headerView.findViewById(R.id.drawerUserName);
        drawerUserEmail = headerView.findViewById(R.id.drawerUserEmail);
        
        cardCareerExploration = findViewById(R.id.cardCareerExploration);
        cardMentorCommunity = findViewById(R.id.cardMentorCommunity);
        cardReport = findViewById(R.id.cardReport);
        cardRoadmap = findViewById(R.id.cardRoadmap);
        
        chatButton = findViewById(R.id.chatButton);
        
        navHome = findViewById(R.id.navHome);
        navFiles = findViewById(R.id.navFiles);
        navBooks = findViewById(R.id.navBooks);
        navProfile = findViewById(R.id.navProfile);
    }
    
    private void loadUserData() {
        SharedPreferences prefs = getSharedPreferences("NavikPrefs", MODE_PRIVATE);
        String email = prefs.getString("userEmail", "User");
        String displayName = email.split("@")[0];
        userName.setText(displayName);
        drawerUserName.setText(displayName);
        drawerUserEmail.setText(email);
    }
    
    private void setupDrawer() {
        menuIcon.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            
            if (id == R.id.nav_home) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else if (id == R.id.nav_job_listing) {
                startActivity(new Intent(this, CareerExplorationActivity.class));
            } else if (id == R.id.nav_scholarship) {
                startActivity(new Intent(this, BooksActivity.class));
            } else if (id == R.id.nav_discussion) {
                startActivity(new Intent(this, ChatbotActivity.class));
            } else if (id == R.id.nav_settings) {
                startActivity(new Intent(this, ProfileActivity.class));
            } else if (id == R.id.nav_help) {
                startActivity(new Intent(this, ChatbotActivity.class));
            } else if (id == R.id.nav_logout) {
                showLogoutDialog();
            }
            
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }
    
    private void showLogoutDialog() {
        new AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes", (dialog, which) -> {
                SharedPreferences prefs = getSharedPreferences("NavikPrefs", MODE_PRIVATE);
                prefs.edit().clear().apply();
                
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            })
            .setNegativeButton("Cancel", null)
            .show();
    }
    
    private void setupClickListeners() {
        cardCareerExploration.setOnClickListener(v -> 
            startActivity(new Intent(this, CareerExplorationActivity.class))
        );
        
        cardMentorCommunity.setOnClickListener(v -> 
            startActivity(new Intent(this, MentorActivity.class))
        );
        
        cardReport.setOnClickListener(v -> 
            startActivity(new Intent(this, ReportActivity.class))
        );
        
        cardRoadmap.setOnClickListener(v -> 
            startActivity(new Intent(this, RoadmapActivity.class))
        );
        
        chatButton.setOnClickListener(v -> 
            startActivity(new Intent(this, ChatbotActivity.class))
        );
        
        navHome.setOnClickListener(v -> {
            // Already on home
        });
        
        navFiles.setOnClickListener(v -> 
            startActivity(new Intent(this, TestsActivity.class))
        );
        
        navBooks.setOnClickListener(v -> 
            startActivity(new Intent(this, BooksActivity.class))
        );
        
        navProfile.setOnClickListener(v -> 
            startActivity(new Intent(this, ProfileActivity.class))
        );
    }
    
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
