package com.example.navik;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.splashscreen.SplashScreen;

import com.google.firebase.auth.FirebaseAuth;

public class RoleSelectionActivity extends AppCompatActivity {

    public static final String EXTRA_ROLE = "selectedRole";

    private CardView cardStartAsMentor;
    private CardView cardStartAsUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);

        // If already logged in AND Firebase has an active user, skip to Home
        SharedPreferences prefs = getSharedPreferences("NavikPrefs", MODE_PRIVATE);
        boolean prefLoggedIn = prefs.getBoolean("isLoggedIn", false);
        boolean firebaseLoggedIn = FirebaseAuth.getInstance().getCurrentUser() != null;

        if (prefLoggedIn && firebaseLoggedIn) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
            return;
        }

        // If prefs say logged in but Firebase disagrees, clear stale session data
        if (prefLoggedIn && !firebaseLoggedIn) {
            prefs.edit()
                    .putBoolean("isLoggedIn", false)
                    .remove("userEmail")
                    .remove("userName")
                    .apply();
        }

        // If a role was already selected previously, skip role selection
        if (UserRoleManager.hasRole(this)) {
            String savedRole = UserRoleManager.getRole(this);
            if (UserRoleManager.ROLE_MENTOR.equals(savedRole)) {
                // Only redirect to mentor form if they already filled it but haven't logged in
                // yet
                boolean hasPendingProfile = prefs.getBoolean("pendingMentorProfile", false);
                if (hasPendingProfile) {
                    // Profile filled, take them to login
                    startActivity(new Intent(this, LoginActivity.class));
                } else {
                    // Profile not filled yet, show the form
                    Intent intent = new Intent(this, AddMentorProfileActivity.class);
                    intent.putExtra("preLogin", true);
                    startActivity(intent);
                }
            } else {
                startActivity(new Intent(this, LoginActivity.class));
            }
            finish();
            return;
        }

        setContentView(R.layout.activity_role_selection);

        cardStartAsMentor = findViewById(R.id.cardStartAsMentor);
        cardStartAsUser = findViewById(R.id.cardStartAsUser);

        // "Join as User" → save role, go to Login/SignUp
        cardStartAsUser.setOnClickListener(v -> {
            UserRoleManager.setRole(this, UserRoleManager.ROLE_USER);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        // "Join as Mentor" → go to Mentor Profile form first (role saved only on form
        // submit)
        cardStartAsMentor.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddMentorProfileActivity.class);
            intent.putExtra("preLogin", true);
            startActivity(intent);
            // Don't finish — user can press back to return here
        });
    }
}
