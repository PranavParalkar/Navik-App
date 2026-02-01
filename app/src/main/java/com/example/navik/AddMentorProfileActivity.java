package com.example.navik;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class AddMentorProfileActivity extends AppCompatActivity {
    
    private ImageView btnBack;
    private TextInputEditText etName, etEmail, etPhone, etExpertise, etExperience;
    private TextInputEditText etCompany, etBio, etWebsite, etLinkedIn, etAvailability;
    private Button btnSubmitProfile;
    private LinearLayout navHome, navFiles, navBooks, navProfile;
    private DatabaseHelper databaseHelper;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mentor_profile);
        
        databaseHelper = new DatabaseHelper(this);
        initializeViews();
        setupClickListeners();
        setupNavigation();
    }
    
    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        
        // Personal Information
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        
        // Professional Information
        etExpertise = findViewById(R.id.etExpertise);
        etExperience = findViewById(R.id.etExperience);
        etCompany = findViewById(R.id.etCompany);
        etBio = findViewById(R.id.etBio);
        
        // Online Presence
        etWebsite = findViewById(R.id.etWebsite);
        etLinkedIn = findViewById(R.id.etLinkedIn);
        
        // Availability
        etAvailability = findViewById(R.id.etAvailability);
        
        btnSubmitProfile = findViewById(R.id.btnSubmitProfile);
        
        navHome = findViewById(R.id.navHome);
        navFiles = findViewById(R.id.navFiles);
        navBooks = findViewById(R.id.navBooks);
        navProfile = findViewById(R.id.navProfile);
    }
    
    private void setupClickListeners() {
        btnBack.setOnClickListener(v -> finish());
        
        btnSubmitProfile.setOnClickListener(v -> submitMentorProfile());
    }
    
    private void submitMentorProfile() {
        // Get all input values
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String expertise = etExpertise.getText().toString().trim();
        String experience = etExperience.getText().toString().trim();
        String company = etCompany.getText().toString().trim();
        String bio = etBio.getText().toString().trim();
        String website = etWebsite.getText().toString().trim();
        String linkedIn = etLinkedIn.getText().toString().trim();
        String availability = etAvailability.getText().toString().trim();
        
        // Validate required fields
        if (name.isEmpty() || email.isEmpty() || expertise.isEmpty() || 
            experience.isEmpty() || bio.isEmpty() || availability.isEmpty()) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Create mentor profile description
        String description = bio + " • " + experience + " years experience at " + company;
        String availabilityText = "⭐ New Mentor • " + availability;
        
        // Create mentor object
        Mentor mentor = new Mentor(
            name,
            description,
            availabilityText,
            R.drawable.ic_launcher_foreground,
            website.isEmpty() ? linkedIn : website,
            linkedIn
        );
        
        // Save to database
        boolean success = databaseHelper.addMentor(mentor, email, phone);
        
        if (success) {
            Toast.makeText(this, "Mentor profile created successfully!", Toast.LENGTH_LONG).show();
            
            // Navigate to browse mentors to see the new profile
            Intent intent = new Intent(this, BrowseMentorsActivity.class);
            intent.putExtra("showNewProfile", true);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Failed to create profile. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
    
    private void setupNavigation() {
        navHome.setOnClickListener(v -> {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
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
}