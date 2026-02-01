package com.example.navik;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    
    private ImageView btnBack;
    private TextView userName;
    private LinearLayout menuProgress, menuDownloads, menuLanguage, menuRoadmap, menuDisplay, menuLogout;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        
        initializeViews();
        loadUserData();
        setupClickListeners();
    }
    
    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        userName = findViewById(R.id.userName);
        
        menuProgress = findViewById(R.id.menuProgress);
        menuDownloads = findViewById(R.id.menuDownloads);
        menuLanguage = findViewById(R.id.menuLanguage);
        menuRoadmap = findViewById(R.id.menuRoadmap);
        menuDisplay = findViewById(R.id.menuDisplay);
        menuLogout = findViewById(R.id.menuLogout);
    }
    
    private void loadUserData() {
        SharedPreferences prefs = getSharedPreferences("NavikPrefs", MODE_PRIVATE);
        String email = prefs.getString("userEmail", "Admin");
        String displayName = email.split("@")[0];
        userName.setText("Hi\n" + displayName);
    }
    
    private void setupClickListeners() {
        btnBack.setOnClickListener(v -> finish());
        
        menuProgress.setOnClickListener(v -> 
            startActivity(new Intent(this, ReportActivity.class))
        );
        
        menuDownloads.setOnClickListener(v -> 
            Toast.makeText(this, "Downloads feature coming soon", Toast.LENGTH_SHORT).show()
        );
        
        menuLanguage.setOnClickListener(v -> 
            showLanguageDialog()
        );
        
        menuRoadmap.setOnClickListener(v -> 
            startActivity(new Intent(this, RoadmapActivity.class))
        );
        
        menuDisplay.setOnClickListener(v -> 
            Toast.makeText(this, "Display settings coming soon", Toast.LENGTH_SHORT).show()
        );
        
        menuLogout.setOnClickListener(v -> 
            showLogoutDialog()
        );
    }
    
    private void showLanguageDialog() {
        String[] languages = {"English", "Hindi", "Spanish", "French"};
        
        new AlertDialog.Builder(this)
            .setTitle("Select Language")
            .setItems(languages, (dialog, which) -> {
                Toast.makeText(this, "Language: " + languages[which], Toast.LENGTH_SHORT).show();
            })
            .show();
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
}
