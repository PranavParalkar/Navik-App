package com.example.navik;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class SkillPlatformActivity extends AppCompatActivity {
    
    private ImageView btnBack;
    private EditText searchInput;
    private LinearLayout catProgramming, catDesign, catBusiness, catDataScience, catCommunication, catLanguages;
    private CardView cardCoursera, cardKhanAcademy, cardLinkedInLearning, cardYouTube;
    private CardView cardGoogleBooks, cardGutenberg, cardOpenLibrary;
    private CardView cardFreeCodeCamp, cardDuolingo, cardCodecademy, cardSkillshare;
    private LinearLayout navHome, navFiles, navSkills, navCareer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_platform);
        
        initializeViews();
        setupClickListeners();
        setupNavigation();
    }
    
    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        searchInput = findViewById(R.id.searchInput);
        
        // Category cards
        catProgramming = findViewById(R.id.catProgramming);
        catDesign = findViewById(R.id.catDesign);
        catBusiness = findViewById(R.id.catBusiness);
        catDataScience = findViewById(R.id.catDataScience);
        catCommunication = findViewById(R.id.catCommunication);
        catLanguages = findViewById(R.id.catLanguages);
        
        // Video resource cards
        cardCoursera = findViewById(R.id.cardCoursera);
        cardKhanAcademy = findViewById(R.id.cardKhanAcademy);
        cardLinkedInLearning = findViewById(R.id.cardLinkedInLearning);
        cardYouTube = findViewById(R.id.cardYouTube);
        
        // eBook cards
        cardGoogleBooks = findViewById(R.id.cardGoogleBooks);
        cardGutenberg = findViewById(R.id.cardGutenberg);
        cardOpenLibrary = findViewById(R.id.cardOpenLibrary);
        
        // Practice cards
        cardFreeCodeCamp = findViewById(R.id.cardFreeCodeCamp);
        cardDuolingo = findViewById(R.id.cardDuolingo);
        cardCodecademy = findViewById(R.id.cardCodecademy);
        cardSkillshare = findViewById(R.id.cardSkillshare);
        
        // Navigation
        navHome = findViewById(R.id.navHome);
        navFiles = findViewById(R.id.navFiles);
        navSkills = findViewById(R.id.navSkills);
        navCareer = findViewById(R.id.navCareer);
        
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }
    }
    
    private void setupClickListeners() {
        // Category click listeners - open relevant search on Coursera
        if (catProgramming != null) {
            catProgramming.setOnClickListener(v -> 
                openWebsite("https://www.coursera.org/browse/computer-science")
            );
        }
        
        if (catDesign != null) {
            catDesign.setOnClickListener(v -> 
                openWebsite("https://www.coursera.org/browse/arts-and-humanities")
            );
        }
        
        if (catBusiness != null) {
            catBusiness.setOnClickListener(v -> 
                openWebsite("https://www.coursera.org/browse/business")
            );
        }
        
        if (catDataScience != null) {
            catDataScience.setOnClickListener(v -> 
                openWebsite("https://www.coursera.org/browse/data-science")
            );
        }
        
        if (catCommunication != null) {
            catCommunication.setOnClickListener(v -> 
                openWebsite("https://www.coursera.org/browse/personal-development")
            );
        }
        
        if (catLanguages != null) {
            catLanguages.setOnClickListener(v -> 
                openWebsite("https://www.duolingo.com/")
            );
        }
        
        // Video resource links
        if (cardCoursera != null) {
            cardCoursera.setOnClickListener(v -> 
                openWebsite("https://www.coursera.org/")
            );
        }
        
        if (cardKhanAcademy != null) {
            cardKhanAcademy.setOnClickListener(v -> 
                openWebsite("https://www.khanacademy.org/")
            );
        }
        
        if (cardLinkedInLearning != null) {
            cardLinkedInLearning.setOnClickListener(v -> 
                openWebsite("https://www.linkedin.com/learning/")
            );
        }
        
        if (cardYouTube != null) {
            cardYouTube.setOnClickListener(v -> 
                openWebsite("https://www.youtube.com/learning")
            );
        }
        
        // eBook links
        if (cardGoogleBooks != null) {
            cardGoogleBooks.setOnClickListener(v -> 
                openWebsite("https://books.google.com/")
            );
        }
        
        if (cardGutenberg != null) {
            cardGutenberg.setOnClickListener(v -> 
                openWebsite("https://www.gutenberg.org/")
            );
        }
        
        if (cardOpenLibrary != null) {
            cardOpenLibrary.setOnClickListener(v -> 
                openWebsite("https://openlibrary.org/")
            );
        }
        
        // Practice links
        if (cardFreeCodeCamp != null) {
            cardFreeCodeCamp.setOnClickListener(v -> 
                openWebsite("https://www.freecodecamp.org/")
            );
        }
        
        if (cardDuolingo != null) {
            cardDuolingo.setOnClickListener(v -> 
                openWebsite("https://www.duolingo.com/")
            );
        }
        
        if (cardCodecademy != null) {
            cardCodecademy.setOnClickListener(v -> 
                openWebsite("https://www.codecademy.com/")
            );
        }
        
        if (cardSkillshare != null) {
            cardSkillshare.setOnClickListener(v -> 
                openWebsite("https://www.skillshare.com/")
            );
        }
    }
    
    private void openWebsite(String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Unable to open website", Toast.LENGTH_SHORT).show();
        }
    }
    
    private void setupNavigation() {
        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            });
        }
        
        if (navFiles != null) {
            navFiles.setOnClickListener(v -> 
                startActivity(new Intent(this, TestsActivity.class))
            );
        }
        
        if (navSkills != null) {
            navSkills.setOnClickListener(v -> {
                // Already on skills page
            });
        }
        
        if (navCareer != null) {
            navCareer.setOnClickListener(v -> 
                startActivity(new Intent(this, CareerExplorationActivity.class))
            );
        }
    }
}
