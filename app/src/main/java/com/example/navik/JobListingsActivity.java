package com.example.navik;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class JobListingsActivity extends AppCompatActivity {

    private ImageView btnBack;
    private CardView cardInternshala, cardLinkedIn, cardIndeed, cardNaukri, cardAngelList;
    private LinearLayout navHome, navFiles, navSkills, navCareer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_listings);

        initializeViews();
        setupClickListeners();
        setupNavigation();
    }

    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);

        cardInternshala = findViewById(R.id.cardInternshala);
        cardLinkedIn = findViewById(R.id.cardLinkedIn);
        cardIndeed = findViewById(R.id.cardIndeed);
        cardNaukri = findViewById(R.id.cardNaukri);
        cardAngelList = findViewById(R.id.cardAngelList);

        navHome = findViewById(R.id.navHome);
        navFiles = findViewById(R.id.navFiles);
        navSkills = findViewById(R.id.navSkills);
        navCareer = findViewById(R.id.navCareer);

        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }
    }

    private void setupClickListeners() {
        if (cardInternshala != null) {
            cardInternshala.setOnClickListener(v ->
                openWebsite("https://internshala.com/internships/")
            );
        }

        if (cardLinkedIn != null) {
            cardLinkedIn.setOnClickListener(v ->
                openWebsite("https://www.linkedin.com/jobs/")
            );
        }

        if (cardIndeed != null) {
            cardIndeed.setOnClickListener(v ->
                openWebsite("https://www.indeed.com/jobs?q=student+internship")
            );
        }

        if (cardNaukri != null) {
            cardNaukri.setOnClickListener(v ->
                openWebsite("https://www.naukri.com/fresher-jobs")
            );
        }

        if (cardAngelList != null) {
            cardAngelList.setOnClickListener(v ->
                openWebsite("https://wellfound.com/jobs")
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
            navSkills.setOnClickListener(v ->
                startActivity(new Intent(this, SkillPlatformActivity.class))
            );
        }

        if (navCareer != null) {
            navCareer.setOnClickListener(v ->
                startActivity(new Intent(this, CareerExplorationActivity.class))
            );
        }
    }
}
