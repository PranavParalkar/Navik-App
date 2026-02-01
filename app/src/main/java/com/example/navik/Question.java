package com.example.navik;

public class Question {
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private int correctAnswer; // 0=A, 1=B, 2=C, 3=D
    
    public Question(String question, String optionA, String optionB, String optionC, String optionD, int correctAnswer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }
    
    public String getQuestion() { return question; }
    public String getOptionA() { return optionA; }
    public String getOptionB() { return optionB; }
    public String getOptionC() { return optionC; }
    public String getOptionD() { return optionD; }
    public int getCorrectAnswer() { return correctAnswer; }
}
