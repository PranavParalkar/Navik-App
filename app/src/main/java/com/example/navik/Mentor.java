package com.example.navik;

public class Mentor {
    private String name;
    private String description;
    private String availability;
    private int imageResource;
    private String websiteUrl;
    private String linkedInUrl;
    
    public Mentor(String name, String description, String availability, int imageResource) {
        this.name = name;
        this.description = description;
        this.availability = availability;
        this.imageResource = imageResource;
        this.websiteUrl = "";
        this.linkedInUrl = "";
    }
    
    public Mentor(String name, String description, String availability, int imageResource, 
                  String websiteUrl, String linkedInUrl) {
        this.name = name;
        this.description = description;
        this.availability = availability;
        this.imageResource = imageResource;
        this.websiteUrl = websiteUrl;
        this.linkedInUrl = linkedInUrl;
    }
    
    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getAvailability() { return availability; }
    public int getImageResource() { return imageResource; }
    public String getWebsiteUrl() { return websiteUrl; }
    public String getLinkedInUrl() { return linkedInUrl; }
    
    public boolean hasWebsite() { return websiteUrl != null && !websiteUrl.isEmpty(); }
    public boolean hasLinkedIn() { return linkedInUrl != null && !linkedInUrl.isEmpty(); }
}
