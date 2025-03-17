package com.example.news_aggregator.model;

import jakarta.persistence.*;

@Entity // this means the class corresponds to a table in database
public class UserPreference {

    @Id // primary key for this entity ante table ani
    @GeneratedValue(strategy = GenerationType.IDENTITY) // means that the database will generate the ID automatically using an auto-increment column.
    private Long id;

    private String userId;
    private String preferredCategories; // comma separated list of categories.

    public UserPreference() { }

    public UserPreference(String userId, String preferredCategories){
        this.userId = userId;
        this.preferredCategories = preferredCategories;
    }

    // Getters and Setters
    public Long getId(){
        return id;
    }

    public void setId(){
        this.id = id;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(){
        this.userId= userId;
    }
    public String getPreferredCategories() {
        return preferredCategories;
    }

    public void setPreferredCategories(String preferredCategories) {
        this.preferredCategories = preferredCategories;
    }

}
