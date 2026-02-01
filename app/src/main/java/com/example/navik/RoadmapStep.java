package com.example.navik;

public class RoadmapStep {
    private int stepNumber;
    private String title;
    private String description;
    private String exams;
    private String branches;
    private String duration;
    
    public RoadmapStep(int stepNumber, String title, String description, 
                      String exams, String branches, String duration) {
        this.stepNumber = stepNumber;
        this.title = title;
        this.description = description;
        this.exams = exams;
        this.branches = branches;
        this.duration = duration;
    }
    
    public int getStepNumber() { return stepNumber; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getExams() { return exams; }
    public String getBranches() { return branches; }
    public String getDuration() { return duration; }
}
