package com.example.navik;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class MemoryTestActivity extends AppCompatActivity {

    private static final int LETTER_COUNT = 10;
    private static final long DISPLAY_INTERVAL_MS = 3000L;

    private TextView testTitle;
    private TextView instructionText;
    private TextView counterText;
    private TextView letterText;
    private ImageView btnBack;
    private Button btnStart;

    private View inputSection;
    private EditText answerInput;
    private Button btnSubmit;

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final List<Character> sequence = new ArrayList<>();
    private int currentIndex = 0;
    private boolean isShowingSequence = false;

    private final Runnable showNextLetterRunnable = new Runnable() {
        @Override
        public void run() {
            if (!isShowingSequence) {
                return;
            }

            if (currentIndex >= sequence.size()) {
                finishSequenceAndAsk();
                return;
            }

            char letter = sequence.get(currentIndex);
            letterText.setText(String.valueOf(letter));
            counterText.setText(String.format(Locale.getDefault(), "%d/%d", currentIndex + 1, LETTER_COUNT));

            currentIndex++;
            handler.postDelayed(this, DISPLAY_INTERVAL_MS);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_test);

        bindViews();
        setupUi();
    }

    private void bindViews() {
        btnBack = findViewById(R.id.btnBack);
        testTitle = findViewById(R.id.testTitle);
        instructionText = findViewById(R.id.instructionText);
        counterText = findViewById(R.id.counterText);
        letterText = findViewById(R.id.letterText);
        btnStart = findViewById(R.id.btnStart);

        inputSection = findViewById(R.id.inputSection);
        answerInput = findViewById(R.id.answerInput);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    private void setupUi() {
        testTitle.setText("Memory Test");
        instructionText.setText("You will see 10 letters, one by one. Memorize the exact order.\n\nEach letter changes every 3 seconds. After the last letter, enter the order.");

        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        inputSection.setVisibility(View.GONE);
        letterText.setText("-");
        counterText.setText(String.format(Locale.getDefault(), "0/%d", LETTER_COUNT));

        btnStart.setOnClickListener(v -> startSequence());
        btnSubmit.setOnClickListener(v -> submitAnswer());
    }

    private void startSequence() {
        if (isShowingSequence) {
            return;
        }

        sequence.clear();
        sequence.addAll(generateRandomUniqueLetters(LETTER_COUNT));

        currentIndex = 0;
        isShowingSequence = true;

        btnStart.setEnabled(false);
        inputSection.setVisibility(View.GONE);
        answerInput.setText("");

        handler.removeCallbacks(showNextLetterRunnable);
        handler.post(showNextLetterRunnable);
    }

    private void finishSequenceAndAsk() {
        isShowingSequence = false;
        handler.removeCallbacks(showNextLetterRunnable);

        letterText.setText("?");
        instructionText.setText("Now enter the 10 letters in the exact order.\nExample: A B C D E F G H I J");
        inputSection.setVisibility(View.VISIBLE);
        answerInput.requestFocus();
    }

    private void submitAnswer() {
        if (isShowingSequence) {
            return;
        }

        String raw = answerInput.getText() != null ? answerInput.getText().toString() : "";
        String cleaned = raw.toUpperCase(Locale.getDefault()).replaceAll("[^A-Z]", "");

        if (cleaned.length() != LETTER_COUNT) {
            Toast.makeText(this, "Please enter exactly 10 letters (A-Z).", Toast.LENGTH_SHORT).show();
            return;
        }

        int score = 0;
        for (int i = 0; i < LETTER_COUNT; i++) {
            char guessed = cleaned.charAt(i);
            if (guessed == sequence.get(i)) {
                score++;
            }
        }

        TestProgressManager.markCompleted(this, TestProgressManager.TEST_MEMORY);

        Intent intent = new Intent(this, TestResultActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("total", LETTER_COUNT);
        intent.putExtra("testName", "Memory Test");
        startActivity(intent);
        finish();
    }

    private List<Character> generateRandomUniqueLetters(int count) {
        List<Character> letters = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            letters.add(c);
        }
        Collections.shuffle(letters);
        return new ArrayList<>(letters.subList(0, Math.min(count, letters.size())));
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(showNextLetterRunnable);
        super.onDestroy();
    }
}
