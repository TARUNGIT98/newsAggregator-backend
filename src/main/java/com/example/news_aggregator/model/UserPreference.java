package com.example.news_aggregator.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_preference", uniqueConstraints = @UniqueConstraint(columnNames = "userId"))
public class UserPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String preferredCategories;

    public UserPreference() { }

    public UserPreference(String userId, String preferredCategories){
        this.userId = userId;
        this.preferredCategories = preferredCategories;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getPreferredCategories() {
        return preferredCategories;
    }
    public void setPreferredCategories(String preferredCategories) {
        this.preferredCategories = preferredCategories;
    }
}
