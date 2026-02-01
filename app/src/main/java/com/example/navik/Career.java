package com.example.navik;

public class Career {
    private String title;
    private String description;
    private String degreePreferred;
    private String salary;
    private int imageResource;
    private String learnMoreUrl;
    
    public Career(String title, String description, String degreePreferred, 
                  String salary, int imageResource) {
        this.title = title;
        this.description = description;
        this.degreePreferred = degreePreferred;
        this.salary = salary;
        this.imageResource = imageResource;
        this.learnMoreUrl = "";
    }
    
    public Career(String title, String description, String degreePreferred, 
                  String salary, int imageResource, String learnMoreUrl) {
        this.title = title;
        this.description = description;
        this.degreePreferred = degreePreferred;
        this.salary = salary;
        this.imageResource = imageResource;
        this.learnMoreUrl = learnMoreUrl;
    }
    
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getDegreePreferred() { return degreePreferred; }
    public String getSalary() { return salary; }
    public int getImageResource() { return imageResource; }
    public String getLearnMoreUrl() { return learnMoreUrl; }
    public boolean hasLearnMoreUrl() { return learnMoreUrl != null && !learnMoreUrl.isEmpty(); }
}
