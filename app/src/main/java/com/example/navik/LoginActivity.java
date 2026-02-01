package com.example.navik;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {
    
    private static final int RC_SIGN_IN = 9001;
    
    private TextView tabLogin, tabSignUp, forgotPassword;
    private LinearLayout loginForm, signUpForm;
    private EditText loginEmail, loginPassword;
    private EditText signUpEmail, signUpPassword, signUpConfirmPassword;
    private Button btnLogin, btnSignUp;
    private LinearLayout btnPhone, btnGoogle, btnFacebook;
    private SharedPreferences prefs;
    private boolean isLoginTab = true;
    
    // Firebase
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        prefs = getSharedPreferences("NavikPrefs", MODE_PRIVATE);
        
        if (prefs.getBoolean("isLoggedIn", false)) {
            navigateToHome();
            return;
        }
        
        setContentView(R.layout.activity_login);
        
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        
        // Configure Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        
        initializeViews();
        setupTabSwitching();
        setupClickListeners();
    }
    
    private void initializeViews() {
        tabLogin = findViewById(R.id.tabLogin);
        tabSignUp = findViewById(R.id.tabSignUp);
        loginForm = findViewById(R.id.loginForm);
        signUpForm = findViewById(R.id.signUpForm);
        
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        forgotPassword = findViewById(R.id.forgotPassword);
        btnLogin = findViewById(R.id.btnLogin);
        
        signUpEmail = findViewById(R.id.signUpEmail);
        signUpPassword = findViewById(R.id.signUpPassword);
        signUpConfirmPassword = findViewById(R.id.signUpConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        
        btnPhone = findViewById(R.id.btnPhone);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnFacebook = findViewById(R.id.btnFacebook);
    }
    
    private void setupTabSwitching() {
        tabLogin.setOnClickListener(v -> switchToLogin());
        tabSignUp.setOnClickListener(v -> switchToSignUp());
    }
    
    private void switchToLogin() {
        if (!isLoginTab) {
            isLoginTab = true;
            tabLogin.setBackgroundResource(R.drawable.bg_tab_selected);
            tabLogin.setTextColor(ContextCompat.getColor(this, R.color.white));
            tabSignUp.setBackgroundResource(android.R.color.transparent);
            tabSignUp.setTextColor(ContextCompat.getColor(this, R.color.blue_primary));
            
            loginForm.setVisibility(View.VISIBLE);
            signUpForm.setVisibility(View.GONE);
        }
    }
    
    private void switchToSignUp() {
        if (isLoginTab) {
            isLoginTab = false;
            tabSignUp.setBackgroundResource(R.drawable.bg_tab_selected);
            tabSignUp.setTextColor(ContextCompat.getColor(this, R.color.white));
            tabLogin.setBackgroundResource(android.R.color.transparent);
            tabLogin.setTextColor(ContextCompat.getColor(this, R.color.blue_primary));
            
            loginForm.setVisibility(View.GONE);
            signUpForm.setVisibility(View.VISIBLE);
        }
    }
    
    private void setupClickListeners() {
        btnLogin.setOnClickListener(v -> handleLogin());
        btnSignUp.setOnClickListener(v -> handleSignUp());
        forgotPassword.setOnClickListener(v -> handleForgotPassword());
        
        btnPhone.setOnClickListener(v -> Toast.makeText(this, "Phone login coming soon", Toast.LENGTH_SHORT).show());
        btnGoogle.setOnClickListener(v -> handleGoogleSignIn());
        btnFacebook.setOnClickListener(v -> Toast.makeText(this, "Facebook login coming soon", Toast.LENGTH_SHORT).show());
    }
    
    private void handleLogin() {
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();
        
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        
        prefs.edit()
            .putBoolean("isLoggedIn", true)
            .putString("userEmail", email)
            .apply();
        
        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
        navigateToHome();
    }
    
    private void handleSignUp() {
        String email = signUpEmail.getText().toString().trim();
        String password = signUpPassword.getText().toString().trim();
        String confirmPassword = signUpConfirmPassword.getText().toString().trim();
        
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }
        
        prefs.edit()
            .putBoolean("isLoggedIn", true)
            .putString("userEmail", email)
            .apply();
        
        Toast.makeText(this, "Sign up successful!", Toast.LENGTH_SHORT).show();
        navigateToHome();
    }
    
    private void handleForgotPassword() {
        Toast.makeText(this, "Password reset coming soon", Toast.LENGTH_SHORT).show();
    }
    
    private void handleGoogleSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Toast.makeText(this, "Google sign in failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            prefs.edit()
                                    .putBoolean("isLoggedIn", true)
                                    .putString("userEmail", user.getEmail())
                                    .putString("userName", user.getDisplayName())
                                    .apply();
                            
                            Toast.makeText(this, "Welcome " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                            navigateToHome();
                        }
                    } else {
                        Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    
    private void navigateToHome() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}
