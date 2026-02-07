package com.example.navik;

public class Student {
    private String name;
    private String interest;
    private String education;
    private String lookingFor;
    private int imageResource;

    public Student(String name, String interest, String education, String lookingFor, int imageResource) {
        this.name = name;
        this.interest = interest;
        this.education = education;
        this.lookingFor = lookingFor;
        this.imageResource = imageResource;
    }

    public String getName() { return name; }
    public String getInterest() { return interest; }
    public String getEducation() { return education; }
    public String getLookingFor() { return lookingFor; }
    public int getImageResource() { return imageResource; }
}
