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
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.textfield.TextInputEditText;

public class AddMentorProfileActivity extends AppCompatActivity {

    private ImageView btnBack;
    private TextView tvTitle, tvSubtitle;
    private View progress1, progress2, progress3, progress4;
    private LinearLayout step1, step2, step3, step4;
    private Button btnPrevStep, btnNextStep;

    private TextInputEditText etName, etEmail, etPhone;
    private AutoCompleteTextView dropdownExpertise, dropdownExperience, dropdownAvailability, dropdownMentoringMode;
    private TextInputEditText etCompany, etBio, etWebsite, etLinkedIn;
    private LinearLayout navHome, navFiles, navSkills, navCareer;
    private CardView bottomNav;
    private DatabaseHelper databaseHelper;
    private boolean isPreLogin;

    private int currentStep = 1;
    private static final int TOTAL_STEPS = 4;

    // Step titles and subtitles
    private static final String[] STEP_TITLES = {
            "Personal Info", "Professional Details", "Online Presence", "Availability"
    };
    private static final String[] STEP_SUBTITLES = {
            "Step 1 of 4", "Step 2 of 4", "Step 3 of 4", "Step 4 of 4"
    };

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
        updateStepUI();

        // Hide bottom nav in pre-login mode
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
        tvTitle = findViewById(R.id.tvTitle);
        tvSubtitle = findViewById(R.id.tvSubtitle);

        // Progress indicators
        progress1 = findViewById(R.id.progress1);
        progress2 = findViewById(R.id.progress2);
        progress3 = findViewById(R.id.progress3);
        progress4 = findViewById(R.id.progress4);

        // Step containers
        step1 = findViewById(R.id.step1);
        step2 = findViewById(R.id.step2);
        step3 = findViewById(R.id.step3);
        step4 = findViewById(R.id.step4);

        // Navigation buttons
        btnPrevStep = findViewById(R.id.btnPrevStep);
        btnNextStep = findViewById(R.id.btnNextStep);

        // Personal Information
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);

        // Professional Information
        dropdownExpertise = findViewById(R.id.dropdownExpertise);
        dropdownExperience = findViewById(R.id.dropdownExperience);
        etCompany = findViewById(R.id.etCompany);
        etBio = findViewById(R.id.etBio);

        // Online Presence
        etWebsite = findViewById(R.id.etWebsite);
        etLinkedIn = findViewById(R.id.etLinkedIn);

        // Availability
        dropdownAvailability = findViewById(R.id.dropdownAvailability);
        dropdownMentoringMode = findViewById(R.id.dropdownMentoringMode);

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
            if (currentStep > 1) {
                goToPreviousStep();
            } else if (isPreLogin) {
                startActivity(new Intent(this, RoleSelectionActivity.class));
                finish();
            } else {
                finish();
            }
        });

        btnNextStep.setOnClickListener(v -> {
            if (currentStep < TOTAL_STEPS) {
                if (validateCurrentStep()) {
                    goToNextStep();
                }
            } else {
                submitMentorProfile();
            }
        });

        btnPrevStep.setOnClickListener(v -> goToPreviousStep());
    }

    private void goToNextStep() {
        currentStep++;
        updateStepUI();
    }

    private void goToPreviousStep() {
        currentStep--;
        updateStepUI();
    }

    private void updateStepUI() {
        // Update title and subtitle
        tvTitle.setText(STEP_TITLES[currentStep - 1]);
        tvSubtitle.setText(STEP_SUBTITLES[currentStep - 1]);

        // Update progress bar
        View[] progressViews = { progress1, progress2, progress3, progress4 };
        for (int i = 0; i < progressViews.length; i++) {
            if (i < currentStep) {
                progressViews[i].setBackgroundResource(R.drawable.bg_progress_active);
            } else {
                progressViews[i].setBackgroundResource(R.drawable.bg_progress_inactive);
            }
        }

        // Show/hide step containers
        step1.setVisibility(currentStep == 1 ? View.VISIBLE : View.GONE);
        step2.setVisibility(currentStep == 2 ? View.VISIBLE : View.GONE);
        step3.setVisibility(currentStep == 3 ? View.VISIBLE : View.GONE);
        step4.setVisibility(currentStep == 4 ? View.VISIBLE : View.GONE);

        // Show/hide back button
        btnPrevStep.setVisibility(currentStep > 1 ? View.VISIBLE : View.GONE);

        // Update next button text
        if (currentStep == TOTAL_STEPS) {
            btnNextStep.setText("Create Profile  ✓");
        } else {
            btnNextStep.setText("Next →");
        }

        // On step 1 with no back button, make next button full width
        if (currentStep == 1) {
            btnNextStep.setLayoutParams(new LinearLayout.LayoutParams(
                    0, dpToPx(52), 2f));
            ((LinearLayout.LayoutParams) btnNextStep.getLayoutParams()).setMarginStart(0);
        } else {
            btnNextStep.setLayoutParams(new LinearLayout.LayoutParams(
                    0, dpToPx(52), 1f));
            ((LinearLayout.LayoutParams) btnNextStep.getLayoutParams()).setMarginStart(dpToPx(8));
        }
    }

    private boolean validateCurrentStep() {
        switch (currentStep) {
            case 1:
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                if (name.isEmpty()) {
                    etName.setError("Name is required");
                    etName.requestFocus();
                    return false;
                }
                if (email.isEmpty()) {
                    etEmail.setError("Email is required");
                    etEmail.requestFocus();
                    return false;
                }
                return true;

            case 2:
                String expertise = dropdownExpertise.getText().toString().trim();
                String experience = dropdownExperience.getText().toString().trim();
                String bio = etBio.getText().toString().trim();
                if (expertise.isEmpty()) {
                    Toast.makeText(this, "Please select your area of expertise", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (experience.isEmpty()) {
                    Toast.makeText(this, "Please select your experience level", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (bio.isEmpty()) {
                    etBio.setError("Bio is required");
                    etBio.requestFocus();
                    return false;
                }
                return true;

            case 3:
                // Online presence is optional
                return true;

            case 4:
                String availability = dropdownAvailability.getText().toString().trim();
                if (availability.isEmpty()) {
                    Toast.makeText(this, "Please select your availability", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;

            default:
                return true;
        }
    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
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

        // Final validation
        if (name.isEmpty() || email.isEmpty() || expertise.isEmpty() ||
                experience.isEmpty() || bio.isEmpty() || availability.isEmpty()) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isPreLogin) {
            // Save the mentor role now that the form is actually submitted
            UserRoleManager.setRole(this, UserRoleManager.ROLE_MENTOR);

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
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            String description = bio + " • " + experience + " years experience at " + company;
            String availabilityText = "⭐ New Mentor • " + availability;

            Mentor mentor = new Mentor(
                    name, description, availabilityText,
                    R.drawable.ic_launcher_foreground,
                    website.isEmpty() ? linkedIn : website,
                    linkedIn);

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

        navFiles.setOnClickListener(v -> startActivity(new Intent(this, TestsActivity.class)));

        navSkills.setOnClickListener(v -> startActivity(new Intent(this, SkillPlatformActivity.class)));

        navCareer.setOnClickListener(v -> startActivity(new Intent(this, CareerExplorationActivity.class)));
    }

    @Override
    public void onBackPressed() {
        if (currentStep > 1) {
            goToPreviousStep();
        } else {
            super.onBackPressed();
        }
    }
}