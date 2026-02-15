package com.example.navik;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.textfield.TextInputEditText;

public class AddMentorProfileActivity extends AppCompatActivity {
    
    private ImageView btnBack;
    private TextInputEditText etName, etEmail, etPhone;
    private AutoCompleteTextView dropdownExpertise, dropdownExperience, dropdownAvailability, dropdownMentoringMode;
    private TextInputEditText etCompany, etBio, etWebsite, etLinkedIn;
    private Button btnSubmitProfile;
    private LinearLayout navHome, navFiles, navSkills, navCareer;
    private CardView bottomNav;
    private DatabaseHelper databaseHelper;
    private boolean isPreLogin;

    // Dropdown options
    private static final String[] EXPERTISE_OPTIONS = {
            "Software Engineering", "Data Science & AI", "Web Development",
            "Mobile App Development", "Cloud Computing & DevOps",
            "Cybersecurity", "UI/UX Design", "Product Management",
            "Machine Learning", "Blockchain & Web3",
            "Database Administration", "Game Development",
            "Digital Marketing", "Business Analytics", "Other"
    };

    private static final String[] EXPERIENCE_OPTIONS = {
            "0 - 1 years", "1 - 3 years", "3 - 5 years",
            "5 - 8 years", "8 - 12 years", "12 - 15 years", "15+ years"
    };

    private static final String[] AVAILABILITY_OPTIONS = {
            "Weekdays: Morning (8 AM - 12 PM)",
            "Weekdays: Afternoon (12 PM - 5 PM)",
            "Weekdays: Evening (5 PM - 9 PM)",
            "Weekends: Morning (8 AM - 12 PM)",
            "Weekends: Afternoon (12 PM - 5 PM)",
            "Weekends: Evening (5 PM - 9 PM)",
            "Flexible / Anytime",
            "By Appointment Only"
    };

    private static final String[] MENTORING_MODE_OPTIONS = {
            "Online (Video Call)", "Online (Chat Only)",
            "In-Person", "Hybrid (Online + In-Person)", "No Preference"
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mentor_profile);
        
        isPreLogin = getIntent().getBooleanExtra("preLogin", false);
        databaseHelper = new DatabaseHelper(this);
        initializeViews();
        setupDropdowns();
        setupClickListeners();
        
        // Hide bottom nav in pre-login mode (user isn't logged in yet)
        if (isPreLogin) {
            if (bottomNav != null) {
                bottomNav.setVisibility(View.GONE);
            }
        } else {
            setupNavigation();
        }
    }
    
    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        
        // Personal Information
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        
        // Professional Information (dropdowns)
        dropdownExpertise = findViewById(R.id.dropdownExpertise);
        dropdownExperience = findViewById(R.id.dropdownExperience);
        etCompany = findViewById(R.id.etCompany);
        etBio = findViewById(R.id.etBio);
        
        // Online Presence
        etWebsite = findViewById(R.id.etWebsite);
        etLinkedIn = findViewById(R.id.etLinkedIn);
        
        // Availability (dropdowns)
        dropdownAvailability = findViewById(R.id.dropdownAvailability);
        dropdownMentoringMode = findViewById(R.id.dropdownMentoringMode);
        
        btnSubmitProfile = findViewById(R.id.btnSubmitProfile);
        
        bottomNav = findViewById(R.id.bottomNav);
        navHome = findViewById(R.id.navHome);
        navFiles = findViewById(R.id.navFiles);
        navSkills = findViewById(R.id.navSkills);
        navCareer = findViewById(R.id.navCareer);
    }

    private void setupDropdowns() {
        ArrayAdapter<String> expertiseAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line, EXPERTISE_OPTIONS);
        dropdownExpertise.setAdapter(expertiseAdapter);

        ArrayAdapter<String> experienceAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line, EXPERIENCE_OPTIONS);
        dropdownExperience.setAdapter(experienceAdapter);

        ArrayAdapter<String> availabilityAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line, AVAILABILITY_OPTIONS);
        dropdownAvailability.setAdapter(availabilityAdapter);

        ArrayAdapter<String> modeAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line, MENTORING_MODE_OPTIONS);
        dropdownMentoringMode.setAdapter(modeAdapter);
    }
    
    private void setupClickListeners() {
        btnBack.setOnClickListener(v -> {
            if (isPreLogin) {
                // Go back to role selection
                startActivity(new Intent(this, RoleSelectionActivity.class));
                finish();
            } else {
                finish();
            }
        });
        
        btnSubmitProfile.setOnClickListener(v -> submitMentorProfile());
    }
    
    private void submitMentorProfile() {
        // Get all input values
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String expertise = dropdownExpertise.getText().toString().trim();
        String experience = dropdownExperience.getText().toString().trim();
        String company = etCompany.getText().toString().trim();
        String bio = etBio.getText().toString().trim();
        String website = etWebsite.getText().toString().trim();
        String linkedIn = etLinkedIn.getText().toString().trim();
        String availability = dropdownAvailability.getText().toString().trim();
        String mentoringMode = dropdownMentoringMode.getText().toString().trim();
        
        // Validate required fields
        if (name.isEmpty() || email.isEmpty() || expertise.isEmpty() || 
            experience.isEmpty() || bio.isEmpty() || availability.isEmpty()) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (isPreLogin) {
            // Save mentor data temporarily in SharedPreferences
            SharedPreferences prefs = getSharedPreferences("NavikPrefs", MODE_PRIVATE);
            prefs.edit()
                .putBoolean("pendingMentorProfile", true)
                .putString("pendingMentor_name", name)
                .putString("pendingMentor_email", email)
                .putString("pendingMentor_phone", phone)
                .putString("pendingMentor_expertise", expertise)
                .putString("pendingMentor_experience", experience)
                .putString("pendingMentor_company", company)
                .putString("pendingMentor_bio", bio)
                .putString("pendingMentor_website", website)
                .putString("pendingMentor_linkedIn", linkedIn)
                .putString("pendingMentor_availability", availability)
                .putString("pendingMentor_mentoringMode", mentoringMode)
                .apply();
            
            Toast.makeText(this, "Profile saved! Now create your account.", Toast.LENGTH_SHORT).show();
            
            // Go to Login/SignUp
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            // Already logged in — save directly to database
            String description = bio + " • " + experience + " years experience at " + company;
            String availabilityText = "⭐ New Mentor • " + availability;
            
            Mentor mentor = new Mentor(
                name, description, availabilityText,
                R.drawable.ic_launcher_foreground,
                website.isEmpty() ? linkedIn : website,
                linkedIn
            );
            
            boolean success = databaseHelper.addMentor(mentor, email, phone);
            
            if (success) {
                Toast.makeText(this, "Mentor profile created successfully!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, BrowseMentorsActivity.class);
                intent.putExtra("showNewProfile", true);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Failed to create profile. Please try again.", Toast.LENGTH_SHORT).show();
            }
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
        
        navSkills.setOnClickListener(v -> 
            startActivity(new Intent(this, SkillPlatformActivity.class))
        );
        
        navCareer.setOnClickListener(v -> 
            startActivity(new Intent(this, CareerExplorationActivity.class))
        );
    }
}