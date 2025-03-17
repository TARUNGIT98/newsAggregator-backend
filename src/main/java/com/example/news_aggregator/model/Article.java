package com.example.news_aggregator.model;

public class Article {
    private String title;
    private String description;
    private String url;
    private String summary;  // New field for the generated summary

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
}
