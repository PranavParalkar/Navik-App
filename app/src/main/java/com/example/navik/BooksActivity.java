package com.example.navik;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class BooksActivity extends AppCompatActivity {
    
    private ImageView btnBack;
    private CardView cardReport, cardRoadmap;
    private CardView cardCoursera, cardKhanAcademy, cardLinkedInLearning, cardIndeedCareerGuide;
    private CardView cardFreeCodeCamp, cardDuolingo;
    private CardView cardHBRArticle, cardForbesArticle, cardMediumArticle, cardTEDTalk;
    private FrameLayout chatButton;
    private LinearLayout navHome, navFiles, navBooks, navProfile;

    //Pranav is Real
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        
        initializeViews();
        setupClickListeners();
        setupNavigation();
    }
    
    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        cardReport = findViewById(R.id.cardReport);
        cardRoadmap = findViewById(R.id.cardRoadmap);
        chatButton = findViewById(R.id.chatButton);
        
        // External resource cards
        cardCoursera = findViewById(R.id.cardCoursera);
        cardKhanAcademy = findViewById(R.id.cardKhanAcademy);
        cardLinkedInLearning = findViewById(R.id.cardLinkedInLearning);
        cardIndeedCareerGuide = findViewById(R.id.cardIndeedCareerGuide);
        cardFreeCodeCamp = findViewById(R.id.cardFreeCodeCamp);
        cardDuolingo = findViewById(R.id.cardDuolingo);
        
        // Article cards
        cardHBRArticle = findViewById(R.id.cardHBRArticle);
        cardForbesArticle = findViewById(R.id.cardForbesArticle);
        cardMediumArticle = findViewById(R.id.cardMediumArticle);
        cardTEDTalk = findViewById(R.id.cardTEDTalk);
        
        navHome = findViewById(R.id.navHome);
        navFiles = findViewById(R.id.navFiles);
        navBooks = findViewById(R.id.navBooks);
        navProfile = findViewById(R.id.navProfile);
        
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }
    }
    
    private void setupClickListeners() {
        if (cardReport != null) {
            cardReport.setOnClickListener(v -> 
                startActivity(new Intent(this, ReportActivity.class))
            );
        }
        
        if (cardRoadmap != null) {
            cardRoadmap.setOnClickListener(v -> 
                startActivity(new Intent(this, RoadmapActivity.class))
            );
        }
        
        if (chatButton != null) {
            chatButton.setOnClickListener(v -> 
                startActivity(new Intent(this, ChatbotActivity.class))
            );
        }
        
        // External resource links
        if (cardCoursera != null) {
            cardCoursera.setOnClickListener(v -> 
                openWebsite("https://www.coursera.org/browse/personal-development/personal-development")
            );
        }
        
        if (cardKhanAcademy != null) {
            cardKhanAcademy.setOnClickListener(v -> 
                openWebsite("https://www.khanacademy.org/college-careers-more")
            );
        }
        
        if (cardLinkedInLearning != null) {
            cardLinkedInLearning.setOnClickListener(v -> 
                openWebsite("https://www.linkedin.com/learning/topics/career-development")
            );
        }
        
        if (cardIndeedCareerGuide != null) {
            cardIndeedCareerGuide.setOnClickListener(v -> 
                openWebsite("https://www.indeed.com/career-advice")
            );
        }
        
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
        
        // Article click listeners
        if (cardHBRArticle != null) {
            cardHBRArticle.setOnClickListener(v -> 
                openWebsite("https://hbr.org/topic/subject/career-planning")
            );
        }
        
        if (cardForbesArticle != null) {
            cardForbesArticle.setOnClickListener(v -> 
                openWebsite("https://www.forbes.com/sites/forbescoachescouncil/2023/01/30/how-to-build-a-successful-career-in-tech/")
            );
        }
        
        if (cardMediumArticle != null) {
            cardMediumArticle.setOnClickListener(v -> 
                openWebsite("https://medium.com/topic/career-advice")
            );
        }
        
        if (cardTEDTalk != null) {
            cardTEDTalk.setOnClickListener(v -> 
                openWebsite("https://www.ted.com/topics/career")
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
        
        if (navBooks != null) {
            navBooks.setOnClickListener(v -> {
                // Already on books page
            });
        }
        
        if (navProfile != null) {
            navProfile.setOnClickListener(v -> 
                startActivity(new Intent(this, ProfileActivity.class))
            );
        }
    }
}
